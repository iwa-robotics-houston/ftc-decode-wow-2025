package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "CloseRedAuto", group = "LinearOpMode")
public class CloseRedAuto extends LinearOpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    CRServo launcher;
    CRServo passThrough;
    DcMotorEx flywheel;
    Servo diverter;

    @Override
    public void runOpMode() {
        /*
         * Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step.
         */
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        launcher = hardwareMap.get(CRServo.class, "launcher");
        passThrough = hardwareMap.get(CRServo.class, "pass");
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        diverter = hardwareMap.get(Servo.class, "diverter");

        /*
         * Note: The settings here assume direct drive on left and right wheels. Gear
         * Reduction or 90 Deg drives may require direction flips
         */

        frontLeftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        backRightDrive.setDirection(DcMotorEx.Direction.FORWARD);

        /*
         * Setting zeroPowerBehavior to BRAKE enables a "brake mode". This causes the motor to
         * slow down much faster when it is coasting. This creates a much more controllable
         * drivetrain. As the robot stops much quicker.
         */
        frontLeftDrive.setZeroPowerBehavior(BRAKE);
        backLeftDrive.setZeroPowerBehavior(BRAKE);
        frontRightDrive.setZeroPowerBehavior(BRAKE);
        backRightDrive.setZeroPowerBehavior(BRAKE);

        /*
         * Tell the driver that initialization is complete.
         */

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Void File Running", "Forwards");
        telemetry.addLine("Left 2 Purple, Right Green");


        //This is where you paste the void names that you make beneath this loop void.
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        //-Addy
        telemetry.addLine("Autonomous started");
        telemetry.addLine("Back, PPG");

        waitForStart();
        opModeIsActive();

        //Right now, this code is really rudimentary, does not include limelight or
        //odometry --> this is not what we really want
        // - Carys
        //Heh... guess who has the limelight now divas - Addy

        double forward = 0.50;
        double reverse = -0.50;

        //drive back
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        sleep(1500);

        //start flywheel
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        flywheel.setVelocity(-1200);
        sleep(3000);

        //launch all artis????
        double flywheelVelocity = Math.abs(flywheel.getVelocity());
        while (flywheelVelocity <= -1200) {
            flywheelVelocity = Math.abs(flywheel.getVelocity());
            sleep(100);
        }
        launcher.setPower(1);
        passThrough.setPower(-1);
        intake.setPower(-1);
        sleep(5000);

        //strafe left one foot
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(forward);
        flywheel.setPower(0);
        launcher.setPower(0);
        passThrough.setPower(0);
        intake.setPower(0);
        sleep(750);

        //brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        telemetry.addLine("Autonomous finished");
        telemetry.addData("Status", "Completed");


    }
}