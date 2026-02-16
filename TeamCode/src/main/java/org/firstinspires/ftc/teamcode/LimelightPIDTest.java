package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

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

        waitForStart();

        while (opModeIsActive()) {
            // Gets the horizontal offset (tx) from the Limelight
            double tx = limelight.getLatestResult().getTx();
            boolean hasTarget = limelight.getLatestResult().isValid(); // Check if target is valid

            if (hasTarget) {
                // The target angle is 0.0 (center of the screen)
                double motorPower = pidController.calculate(0.0, tx);

                // Use the output to control motors
                // Adjust motor logic based on robot setup (e.g., tank drive, swerve)
                backLeftDrive.setPower(-motorPower);
                frontLeftDrive.setPower(-motorPower);
                frontRightDrive.setPower(motorPower);
                backRightDrive.setPower(motorPower);
            } else {
                // Stop motors or implement a search pattern if no target is found
                backLeftDrive.setPower(0);
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backRightDrive.setPower(0);
            }

            // Add telemetry for tuning and debugging
            telemetry.addData("Target X Offset (tx)", tx);
            telemetry.addData("Motor Power", backLeftDrive.getPower());
            telemetry.update();
        }
    }
}