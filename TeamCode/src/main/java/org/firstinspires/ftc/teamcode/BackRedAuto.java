package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BackRedAuto", group = "LinearOpMode")
public class BackRedAuto extends LinearOpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    CRServo passThrough;
    CRServo launcher;
    DcMotorEx flywheel;

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
        passThrough = hardwareMap.get(CRServo.class, "pass");
        launcher = hardwareMap.get(CRServo.class, "launcher");
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");

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
        flywheel.setVelocityPIDFCoefficients(200,0,0,14);
        telemetry.addData("speed", flywheel.getVelocity());



        //This is where you paste the void names that you make beneath this loop void.
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        telemetry.addLine("Autonomous started");
        telemetry.addLine("Back, PPG");


        waitForStart();
        opModeIsActive();

        //6 ball auto
        // - Carys

        double forward = 0.50;
        double reverse = -0.50;


        //start flywheel
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        flywheel.setVelocity(-1500);
        sleep(3000);

        //launch all artis????
        //this works I promise you, it's supposed to be less than
        double flywheelVelocity = Math.abs(flywheel.getVelocity());
        while (flywheelVelocity < 1500) {
            flywheelVelocity = Math.abs(flywheel.getVelocity());
            sleep(100);
        }
        launcher.setPower(1);
        intake.setPower(-1);
        passThrough.setPower(-1);
        sleep(8000);

        //drive forward one foot
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        flywheel.setPower(0);
        launcher.setPower(0);
        intake.setPower(0);
        sleep(680);

        //turn
        frontLeftDrive.setPower(.5);
        frontRightDrive.setPower(-.5);
        backLeftDrive.setPower(.5);
        backRightDrive.setPower(-.5);
        sleep(450);

        //drive forward to intake
        //values of left same as right
        frontLeftDrive.setPower(.25);
        frontRightDrive.setPower(.25);
        backLeftDrive.setPower(.25);
        backRightDrive.setPower(.25);
        intake.setPower(-1);
        passThrough.setPower(-1);
        sleep(2000);

        //go back
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        sleep(750);

        //turn other way
        frontLeftDrive.setPower(-.5);
        frontRightDrive.setPower(.5);
        backLeftDrive.setPower(-.5);
        backRightDrive.setPower(.5);
        intake.setPower(0);
        passThrough.setPower(0);
        sleep(570);

        //drive back
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        flywheel.setVelocity(-1500);
        sleep(800);

        //SHOOT
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(2500);
        launcher.setPower(1);
        intake.setPower(-1);
        passThrough.setPower(-1);
        sleep(8000);

        //drive forward one foot
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        flywheel.setVelocity(0);
        passThrough.setPower(0);
        launcher.setPower(0);
        intake.setPower(0);
        sleep(600);

        //brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(3000);

        telemetry.addLine("Autonomous finished");
        telemetry.addData("Status", "Completed");


    }
}