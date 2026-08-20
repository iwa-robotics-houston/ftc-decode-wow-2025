package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BackBlueAuto", group = "LinearOpMode")
public class BackBlueAuto extends LinearOpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    CRServo passThrough;
    CRServo passThrough2;
    CRServo launcher;
    DcMotorEx flywheelL;
    DcMotorEx flywheelR;


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
        passThrough2 = hardwareMap.get(CRServo.class, "pass2");
        launcher = hardwareMap.get(CRServo.class, "launcher");
        flywheelL = hardwareMap.get(DcMotorEx.class, "flywheelL");
        flywheelR = hardwareMap.get(DcMotorEx.class, "flywheelR");


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
         * Tell the driver initialization is complete.
         */

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Void File Running", "Forwards");
        telemetry.addLine("Left 2 Purple, Right Green");
        flywheelR.setVelocityPIDFCoefficients(100, 0, 0, 14);
        flywheelL.setVelocityPIDFCoefficients(100, 0, 0, 14);
        telemetry.addData("speed", flywheelL.getVelocity());


        //This is where you paste the void names that you make beneath this loop void.
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        telemetry.addLine("Autonomous started");
        telemetry.addLine("Back, PPG");


        waitForStart();
        opModeIsActive();

        //fix red auto
        // - Carys

        double forward = 0.50;
        double reverse = -0.50;


        //start flywheel
        //fresh battery velocity
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        flywheelL.setVelocity(-1530);
        flywheelR.setVelocity(-1530);
        sleep(3000);

        /*
        //launch all artis????
        //this works I promise you, it's supposed to be less than
        double flywheelLVelocity = Math.abs(flywheelL.getVelocity());
        while (flywheelLVelocity < 1500) {
            flywheelLVelocity = Math.abs(flywheelL.getVelocity());
            sleep(100);
        }

         */
        passThrough2.setPower(1);
        sleep(400);
        passThrough.setPower(-1);
        launcher.setPower(-1);
        intake.setPower(-1);
        sleep(6000);

        /*
        //turn
        frontLeftDrive.setPower(-.5);
        frontRightDrive.setPower(.5);
        backLeftDrive.setPower(-.5);
        backRightDrive.setPower(.5);
        sleep(300);

         */
        //drive forward one foot
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        flywheelL.setPower(0);
        flywheelR.setPower(0);
        launcher.setPower(0);
        intake.setPower(0);
        passThrough.setPower(0);
        sleep(630);

        //turn
        frontLeftDrive.setPower(-.5);
        frontRightDrive.setPower(.5);
        backLeftDrive.setPower(-.5);
        backRightDrive.setPower(.5);
        sleep(470);

        //drive forward to intake
        //values of left same as right
        frontLeftDrive.setPower(.25);
        frontRightDrive.setPower(.25);
        backLeftDrive.setPower(.25);
        backRightDrive.setPower(.25);
        intake.setPower(-1);
        launcher.setPower(1);
        sleep(3500);

        //go back
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        sleep(1150);

        //turn other way
        frontLeftDrive.setPower(.5);
        frontRightDrive.setPower(-.5);
        backLeftDrive.setPower(.5);
        backRightDrive.setPower(-.5);
        launcher.setPower(0);
        sleep(450);

        //drive back
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        flywheelL.setVelocity(-1530);
        flywheelR.setVelocity(-1530);
        sleep(900);

        //SHOOT
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(2500);
        intake.setPower(-1);
        passThrough.setPower(-1);
        passThrough2.setPower(1);
        launcher.setPower(-1);
        sleep(5000);

        //drive forward one foot
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        flywheelL.setVelocity(0);
        flywheelR.setVelocity(0);
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