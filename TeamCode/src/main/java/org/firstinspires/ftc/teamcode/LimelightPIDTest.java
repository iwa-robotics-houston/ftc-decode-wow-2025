package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

/*
@TeleOp(name = "LimelightPIDTest")
public class LimelightPIDTest extends LinearOpMode {
    private DcMotor backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive; // Example motors for a drivetrain
    private RobotAlignmentPIDController pidController;
    private Limelight3A limelight;
    private SimplifiedOdometryRobot robot;

    // Define initial PID constants (tune these values later)
    private final double kP = 0.05; // Start with a small Kp
    private final double kI = 0.0;
    private final double kD = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware map motors (replace with your motor names)
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");

        // Initialize Limelight hardware object
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // Initialize PID controller
        pidController = new RobotAlignmentPIDController(kP, kI, kD);

// Initialize the robot hardware & Turn on telemetry
        robot = new SimplifiedOdometryRobot(this);
        robot.initialize(true);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.

        limelight.start();

        telemetry.addData(">", "Robot Ready.  Press Play.");
        telemetry.update();
        waitForStart();
        robot.resetHeading();  // Reset heading to set a baseline for Auto

        while (opModeIsActive()) {
            // Gets the horizontal offset (tx) from the Limelight
            double tx = limelight.getLatestResult().getTx();
            boolean hasTarget = limelight.getLatestResult().isValid(); // Check if target is valid

            LLStatus status = limelight.getStatus();
            telemetry.addData("Name", "%s",
                    status.getName());
            telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                    status.getTemp(), status.getCpu(),(int)status.getFps());
            telemetry.addData("Pipeline", "Index: %d, Type: %s",
                    status.getPipelineIndex(), status.getPipelineType());

            LLResult result = limelight.getLatestResult();
            result.getPipelineIndex();
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


                if (hasTarget) {
                // The target angle is 0.0 (center of the screen)
                double motorPower = pidController.calculate(0.0, tx);

                // Use the output to control motors
                backLeftDrive.setPower(0);
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backRightDrive.setPower(0);
            } else {
                // Implement a search pattern if no target is found
                backLeftDrive.setPower(-.2);
                frontLeftDrive.setPower(-.2);
                frontRightDrive.setPower(-.2);
                backRightDrive.setPower(.2);
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
            tx = limelight.getLatestResult().getTx();
            result.getPipelineIndex();
            hasTarget = limelight.getLatestResult().isValid();
            // The target angle is 0.0 (center of the screen)
            double targetAngle = pidController.calculate(0.0, tx);

            if (hasTarget) {
                // Use the output to control motors
                robot.drive(0,0,0);

            } else {

                // The target angle is 0.0 (center of the screen)
                robot.turnto(0, targetAngle);

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
*/
