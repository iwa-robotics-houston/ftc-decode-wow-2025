
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

/*
 * This OpMode illustrates how to use the Limelight3A Vision Sensor.
 *
 * @see <a href="https://limelightvision.io/">Limelight</a>
 *
 * Notes on configuration:
 *
 *   The device presents itself, when plugged into a USB port on a Control Hub as an ethernet
 *   interface.  A DHCP server running on the Limelight automatically assigns the Control Hub an
 *   ip address for the new ethernet interface.
 *
 *   Since the Limelight is plugged into a USB port, it will be listed on the top level configuration
 *   activity along with the Control Hub Portal and other USB devices such as webcams.  Typically
 *   serial numbers are displayed below the device's names.  In the case of the Limelight device, the
 *   Control Hub's assigned ip address for that ethernet interface is used as the "serial number".
 *
 *   Tapping the Limelight's name, transitions to a new screen where the user can rename the Limelight
 *   and specify the Limelight's ip address.  Users should take care not to confuse the ip address of
 *   the Limelight itself, which can be configured through the Limelight settings page via a web browser,
 *   and the ip address the Limelight device assigned the Control Hub and which is displayed in small text
 *   below the name of the Limelight on the top level configuration screen.
 */
@TeleOp(name = "Sensor: LimelightV1", group = "Robot")
//@Disabled
public class LimelightV1 extends LinearOpMode {

    private Limelight3A limelight;
    private DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive; // Example motors for a drivetrain
    private RobotAlignmentPIDController pidController;

    // Define initial PID constants (tune these values later)
    private final double kP = 0.05; // Start with a small Kp
    private final double kI = 0.0;
    private final double kD = 0.0;


    @Override
    public void runOpMode() throws InterruptedException
    {

        // Hardware map motors (replace with your motor names)
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");

        // We set the left motors in reverse which is needed for drive trains where the left
        // motors are opposite to the right ones.
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);


        // Initialize Limelight hardware object
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // Initialize PID controller
        pidController = new RobotAlignmentPIDController(kP, kI, kD);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.
         */
        limelight.start();

        telemetry.addData(">", "Robot Ready.  Press Play.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            LLStatus status = limelight.getStatus();
            telemetry.addData("Name", "%s",
                    status.getName());
            telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                    status.getTemp(), status.getCpu(),(int)status.getFps());
            telemetry.addData("Pipeline", "Index: %d, Type: %s",
                    status.getPipelineIndex(), status.getPipelineType());

            LLResult result = limelight.getLatestResult();
            if (result.isValid()) {
                // Access general information
                Pose3D botpose = result.getBotpose();
                double captureLatency = result.getCaptureLatency();
                double targetingLatency = result.getTargetingLatency();
                double parseLatency = result.getParseLatency();
                telemetry.addData("LL Latency", captureLatency + targetingLatency);
                telemetry.addData("Parse Latency", parseLatency);
                telemetry.addData("PythonOutput", java.util.Arrays.toString(result.getPythonOutput()));

                // How far away from looking a tag are we?
                telemetry.addData("tx", result.getTx());
                telemetry.addData("txnc", result.getTxNC());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("tync", result.getTyNC());

                //Where is the robot? 3D Localization
                telemetry.addData("Botpose", botpose.toString());



                // Access detector results

                List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
                for (LLResultTypes.DetectorResult dr : detectorResults) {
                    telemetry.addData("Detector", "Class: %s, Area: %.2f", dr.getClassName(), dr.getTargetArea());
                }


                // Access fiducial (AprilTag) results
                List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                for (LLResultTypes.FiducialResult fr : fiducialResults) {
                    telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
                }

            } else {
                telemetry.addData("Limelight", "No data available");
            }

            telemetry.update();

            // Get the horizontal offset (tx) from the Limelight
            double tx = limelight.getLatestResult().getTx();
            boolean hasTarget = limelight.getLatestResult().isValid(); // Check if target is valid
            boolean adjust = false;
            double targetZero = 0.0;

            if (hasTarget) {
                // The target angle is 0.0 (center of the screen)
                double motorPower = pidController.calculate(0.0, tx);

                // Use the output to control motors
                // Adjust motor logic based on robot setup (e.g., tank drive, swerve)
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);

            } else {
                // Stop motors or implement a search pattern if no target is found
                //Turn one way, turn the other way, stop.
                frontRightDrive.setPower(0);
                frontLeftDrive.setPower(-0.2);
                backRightDrive.setPower(0);
                backLeftDrive.setPower(-0.2);

                sleep(100);
            }


            // Add telemetry for tuning and debugging
            telemetry.addData("Target X Offset (tx)", tx);
            telemetry.addData("Front Left Motor Power", frontLeftDrive.getPower());
            telemetry.addData("Front Right Motor Power", frontRightDrive.getPower());
            telemetry.addData("Back Left Motor Power", backLeftDrive.getPower());
            telemetry.addData("Back Right Motor Power", backRightDrive.getPower());
            telemetry.update();


            limelight.stop();
        }

    }
}