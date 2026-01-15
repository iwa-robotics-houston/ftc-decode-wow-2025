package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

/*
Guys we need to cook - Addy
This lacks odometry because we lowkey got MuhLuhMuhed over by the delivery people and their delays
so uh hooray
Together we are FTC!!!!
*/


@Autonomous (name = "BackupAutonomousRed", group = "OpMode")
public  class BackupAutonomousRed extends OpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    DcMotorEx flywheelLeft;
    DcMotorEx flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;

    @Override
    public void init() {
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
        launcherLeft = hardwareMap.get(CRServo.class, "launcherLeft");
        launcherRight = hardwareMap.get(CRServo.class, "launcherRight");
        flywheelLeft = hardwareMap.get(DcMotorEx.class, "flywheelLeft");
        flywheelRight = hardwareMap.get(DcMotorEx.class, "flywheelRight");
        diverter = hardwareMap.get(Servo.class,"diverter");

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
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Void File Running", "Forwards");
        telemetry.addLine("Left 2 Purple, Right Green");
    }

    @Override
    public void loop() {
        //This is where you paste the void names that you make beneath this loop void.
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        //-Addy
        telemetry.addLine("Autonomous started");
        telemetry.addLine("Blue, GPP");

        forward();

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(100);

        launchBalls();

        sleep(100);

        killSwitch();
        sleep(100);

        telemetry.addLine("Autonomous finished");
        telemetry.addData("Status", "Completed");

        requestOpModeStop();
    }

    void forward(){

        //Right now, this code is really rudimentary, does not include limelight or
        //odometry --> this is not what we really want
        // - Carys

        double forward = -0.50;
        double reverse = 0.50;

        //backwards slightly
        //This works
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        sleep(1300);

        //Brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        sleep(1000);
    }

    void launchBalls(){
        double forward = -0.50;
        double reverse = 0.50;

        //Brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(1000);

        //start flywheel
        //Its flywheeling it up
        flywheelRight.setPower(-5600);
        flywheelLeft.setPower(5600);
        sleep(3000);

        //launch right arti
        launcherRight.setPower(1);
        launcherLeft.setPower(0);
        sleep(3000);

        //launch left artis
        launcherRight.setPower(0);
        launcherLeft.setPower(-1);
        intake.setPower(1);
        sleep(3000);

        //Brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(1000);

        //strafe right
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(forward);
        sleep(2000);
    }

        void killSwitch(){

            //Brake
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            sleep(1000);

            //Brake Launcher
            launcherRight.setPower(0);
            launcherLeft.setPower(0);
            flywheelRight.setPower(0);
            flywheelLeft.setPower(0);


        }
}
