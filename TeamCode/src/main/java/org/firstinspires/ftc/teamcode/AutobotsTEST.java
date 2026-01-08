package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

/*
This java file is for testing the robot's basic functions autonomous wise - Addy
- Addy
 */

@Autonomous (name = "AutobotsTEST", group = "OpMode")
public class AutobotsTEST extends OpMode {

    // Declare OpMode members.
    DcMotorEx frontLeftDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backRightDrive;
    DcMotorEx flywheelLeft;
    DcMotorEx flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;
    IMU imu;
    private DcMotor intake = null;

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

        imu = hardwareMap.get(IMU.class, "imu");
        // This needs to be changed to match the orientation on your robot
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        /*
         * Tell the driver that initialization is complete.
         */
        telemetry.addData("Status", "Initialized");

        telemetry.addData("Void File Running", "None");
    }

    @Override
    public void loop() {
        //This is where you paste the void names that you make beneath this loop void. Example: "drivetest();" would play the drivetest void.
        //-Addy
        telemetry.addLine("Im transforming it rn");
        telemetry.addLine("Heh... Autobots.... Rev up and roll out!!");

        //drivetest();

        TestBotPush();

        telemetry.addData("Void File Running", "None");
    }

    /*
    Heyo. Below is a list of different voids that can be used during your autonomous runs.
    If you look above, you can see in the autonomous loop that currently, when you press start, it will run the class drivetest.
    Drivetest is where the basic drive information is stored, and you can use it as a template for other voids.
    Dunno if this makes any sense, but imagine you're making a lego house, and the drivetest void has prebuilt blocks you can add.
    I suggest you add a lot of comments for whatever void you make. That way everyone knows its purpose.
    - Addy
     */
    void drivetest(){

        /*
        This void is the template/testing class. It won't be used during autonomous.
        Rather, it's a storage for the basic information that can be copy and pasted into the void classes that we actually use during autonomous.
        It has a testrun for forwards, stop, backwards, and eventually there will be strafing.
        This void can be used to rest run speed, and overall mechanics.

        -Addy
         */

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = -0.19;
        double backward = 0.24;

        /*
        After testing, I think the numbers for these variables should be a bit higher. As it was a bit slow.
        But they can be individualized for each void class that is actually used.
        These variables make it easier to change the numbers for the code below during testing.
        - Addy
         */

        //Forward
        //You don't need to put numbers for the set powers here, as we defined a number for the variable "forward" and "backward"
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        //sleep(#) is important so that the robot actually performs the line of code beforehand.
        //1000 = 1 millisecond. And so on and so forth.
        sleep(2000);

        //Waitwaitwaitwaitwait!!!!

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        sleep(2000);

        //Back it up now

        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(backward);
        backLeftDrive.setPower(backward);
        backRightDrive.setPower(backward);

        sleep(2000);

        //Turn RIGHT
        //Right (SMOOTH)

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward - 0.10);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward - 0.10);

        sleep(2000);

        //Right (Sharp)

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(0);

        sleep(2000);

        //Right  (In Place)

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(backward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(backward);

        sleep(2000);

        //Turn LEFT
        // Left (Smooth)

        frontLeftDrive.setPower(forward - 0.10);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward - 0.10);

        sleep(2000);

        //Left (Sharp)

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(forward);

        sleep(2000);

        //Left (In Place)

        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(backward);
        backRightDrive.setPower(forward);

        sleep(2000);
    }

    void autonomoustest(){

        telemetry.addData("Void File Running", "Autonomous Test");

        /*
        Purpose of this void: A testrun for the starting point of autonomous.
        We want the robot to move about the arena to different points.
        There are no sensors on it physically (we're using a temporary robot), so I am unsure how we will have it pick anything up.
        But I'm sure that we can figure out a solution
        -Addy
         */

        //Variables needed to know how much engine power is needed.
        //The numbers here can be recorded when playing the teleop mode
        //I changed the numbers from 0.19 and -0.24 to larger numbers to get a bit more speed - Addy
        double forward = -0.30;
        double backward = 0.40;

        //Step One - Drive forward from starting point to the center of the field, then turn around.

        //Forward
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(3000);

        //Turn Left (In Place)

        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(backward);
        backRightDrive.setPower(forward);

        sleep(2000);


        //Step Two - Drive back towards direction of starting point, but stop and turn and continue driving.

        //Forward
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(2000);

        //Turn LEFT
        // Left (Smooth)

        frontLeftDrive.setPower(forward - 0.10);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward - 0.10);
        backRightDrive.setPower(forward);

        sleep(1000);

        //Forward
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(2000);

        //Waitwaitwaitwaitwait!!!!

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        sleep(2000);
    }

    void backwardsRobot(){

        //The purpose of this void is to make the robot go backwards around 18 inches.
        //Because Girardot asked <3

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = -0.40;
        double backward = 0.40;

        //Back it up now

        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(backward);
        backLeftDrive.setPower(backward);
        backRightDrive.setPower(backward);

        sleep(4000);



    }

    void forwardsRobot(){

        //The purpose of this void is to make the robot go forwards around 18 inches.

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = -0.40;
        double backward = 0.40;

        //Back it up now

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(4000);

        //peepeepoopoo
        //skibidi
    }

    void TestBotPush(){

        //The Goal of this Void is to test and eventually have code that can push a bot that lacks an autonomous.

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        //Forward needs to be negative, and backwards needs to be positive.
        double forward = -0.40;
        double backward = 0.40;


        //Forward
        //You don't need to put numbers for the set powers here, as we defined a number for the variable "forward" and "backward"
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep (3000);

        //The goal for the next line of code is to up the power of the motors ever so slightly so that the robot can push another robot.
        //This may need to be configured to where the robots are placed, this is just me theorizing right now.
        frontLeftDrive.setPower(forward - 0.10);
        frontRightDrive.setPower(forward - 0.10);
        backLeftDrive.setPower(forward - 0.10);
        backRightDrive.setPower(forward - 0.10);

        sleep(3000);
    }


    void okayInternAnnieNowHitTheSecondTower(){

        //The purpose of this void is to potentially have the robot launch balls.
        //It is still heavily under construction so uh i wouldn't recommend using it.

        //this is JUST intake

        double intakeIn = -1;
        double intakeOut = 1;

        intake.setPower(-1);
        intake.setPower(1);
        intake.setPower(0);

    }

    void WEARECHARLIEKIRRRRRKWECARRRRRRYTHEFLAMMEEEEE(){

        //I'm probably going to hell for this void title, but this java file is for testing so I'll just change it when it's finished - Addy

        //Variables needed to know how much engine power is needed.
        //The numbers here can be recorded when playing the teleop mode
        //I changed the numbers from 0.19 and -0.24 to larger numbers to get a bit more speed - Addy
        double forward = -0.30;
        double backward = 0.40;
        double launch = 1;

        //Step One - Drive forward from starting point to the center of the field, then turn.

        //Forward
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);

        sleep(2000);
        //Sleep length may need to be changed to get the length we want.

        //Left (In Place)

        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(backward);
        backRightDrive.setPower(forward);

        sleep(2000);

        //Launch Balls

        //this is JUST intake, and may need to be changed if it doesn't properly launch.

        launcherLeft.setPower(-launch);
        launcherRight.setPower(launch);

        sleep(3000);

        launcherLeft.setPower(0);
        launcherRight.setPower(0);

        //This should work I guess

    }
}