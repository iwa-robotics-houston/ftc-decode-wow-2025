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
This java file is made specifically for testing autonomous code. Anything that works
will be taken from here and given it's own java file.
Okay? okay slay
- Addy
*/

//Okay I'm trying to do an auto that includes preloaded artifacts - Carys


@Autonomous (name = "AutoBlueGPP", group = "OpMode")
public  class AutoBlueGPP extends OpMode {
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
    }

    @Override
    public void loop() {
        //This is where you paste the void names that you make beneath this loop void.
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        //-Addy
        telemetry.addLine("Autonomous started");
        telemetry.addLine("Blue, GPP");

        opModeIsActive();
    }

    void opModeIsActive(){

        //Right now, this code is really rudimentary, does not include limelight or
        //odometry --> this is not what we really want
        // - Carys

        double forward = -0.50;
        double reverse = 0.50;

        //turn slightly
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(reverse);
        sleep(150);

        //start the flywheel
        flywheelRight.setVelocity(-5600);
        flywheelLeft.setVelocity(5600);
        sleep(3000);
        //you'll probably change this

        //launch artifacts
        flywheelRight.setVelocity(-5600);
        flywheelLeft.setVelocity(5600);
        launcherLeft.setPower(-1);
        intake.setPower(1);
        sleep(3000);

        flywheelRight.setVelocity(-5600);
        flywheelLeft.setVelocity(5600);
        launcherLeft.setPower(0);
        launcherRight.setPower(1);
        intake.setPower(1);
        sleep(3000);

        //move one foot forward
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        flywheelRight.setVelocity(0);
        flywheelLeft.setVelocity(0);
        launcherRight.setPower(0);
        intake.setPower(0);
        sleep(3000);

        //brake!!
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(3000);


        /*
        //robot drives to the triangle
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        sleep(1700);

        //robot turns to face the blue launch target
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(reverse);
        sleep(300);

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        sleep(275);

        //robot stops in launch zone
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(3000);

        //robot warms up the flywheel and launches
        flywheelLeft.setPower(1);
        flywheelRight.setPower(-1);
        sleep(8000); //8000 absolutely needed for flywheel

        //robot launches the GREEN artifact
        flywheelRight.setPower(-1);
        flywheelLeft.setPower(1);
        launcherLeft.setPower(-1);
        sleep(2000);

        //robot launches the TWO PURPLE artifacts
        flywheelRight.setPower(-1);
        flywheelLeft.setPower(1);
        launcherRight.setPower(1);
        launcherLeft.setPower(0);
        sleep(5000);

        //robot stops
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(30000);

        */
        telemetry.addLine("Autonomous finished");
        telemetry.addData("Status", "Completed");

    }

}