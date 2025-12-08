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

Okay I'm trying to do an auto that includes preloaded artifacts
- Carys
 */

@Autonomous (name = "AutoForward", group = "OpMode")
class AutoForward extends OpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    DcMotor flywheelLeft;
    DcMotor flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;

    // Declare OpMode members.

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
        telemetry.addLine("Im transforming it rn");
        telemetry.addLine("Heh... Autobots.... Rev up and roll out!!");

        autoBlue();
    }

    void autoBlue(){

        //The purpose of this void is to make the robot go forwards around 18 inches.
        //Right now, this code is really rudimentary, does not include limelight or
        //odometry --> this is not what we really want

        double forward = -0.50;
        double reverse = 0.40;
        double brake = 0;

        //robot drives to the triangle
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(2000);
        //robot turns to face the blue launch target
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(reverse);

        sleep(1000);

        /*
        //robot warms up the flywheel and launches
        flywheelLeft.setPower(1);
        flywheelRight.setPower(-1);
        //8000 absolutely needed for flywheel
        launcherLeft.setPower(-1);
        launcherRight.setPower(1);
        */

        telemetry.addLine("Autonomous finished");

    }
}
