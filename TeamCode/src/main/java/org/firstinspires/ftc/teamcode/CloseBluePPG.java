package org.firstinspires.ftc.teamcode;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "CloseBluEPPG", group = "LinearOpMode")
public class CloseBluePPG extends LinearOpMode {
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    DcMotorEx flywheel;
    CRServo launcher;
    Servo diverter;
    RobotLimelightV2 limelightV2;

    @Override
    public void runOpMode() throws InterruptedException {
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
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        diverter = hardwareMap.get(Servo.class, "diverter");
        limelightV2 = new RobotLimelightV2();

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
        //Heh... guess who has limelight now divas

        double forward = -0.50;
        double reverse = 0.50;

        //start flywheel
        //NOTE: CHANGE THIS FOR CLOSE SHOOTING
        flywheel.setVelocity(1700);
        sleep(3000);

        //drive back to shoot
        //you can either edit the velocity or the time it drives back when editing
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(reverse);
        backRightDrive.setPower(reverse);
        sleep(2500);

        //Have the limelight detect the april tag for aiming and readjust???
        limelightV2.runOpMode();

        //launch left artis
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        launcher.setPower(-1);
        intake.setPower(1);
        sleep(3000);

        //launch right arti
        launcher.setPower(1);
        sleep(3000);

        //strafe LEFT one foot (blue goal)
        frontLeftDrive.setPower(reverse);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(reverse);
        flywheel.setPower(0);
        launcher.setPower(0);
        intake.setPower(0);
        sleep(1000);

        //brake
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        telemetry.addLine("Autonomous finished");
        telemetry.addData("Status", "Completed");


    }
}