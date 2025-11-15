package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*
Hey guys! This is the autonomous code for our robot. (Yes I named it Autobots. No shame. No regrets)
It's rather simple, and I left a bunch of comments/instructions to help y'all edit it if that is needed.
I plan on making two versions of this code to align with the red alliance and the blue alliance.
That way we don't have to worry about as many errors if the code is configured more accurately for each color team.
- Addy
 */

@Autonomous (name = "Autobots", group = "OpMode")
public class Autobots extends OpMode {

    // Declare OpMode members.
   DcMotor frontLeftDrive;
   DcMotor backLeftDrive;
   DcMotor frontRightDrive;
   DcMotor backRightDrive;
    DcMotor intake;
    DcMotor flywheelLeft;
    DcMotor flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;


    //No code for the intake or launcher yet. When we get the teleop version perfected I'll add it here! - Addy

    @Override
    public void init() {
        /*
         * Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step.
         */
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcherLeft = hardwareMap.get(CRServo.class, "launcherLeft");
        launcherRight = hardwareMap.get(CRServo.class, "launcherRight");
        flywheelLeft = hardwareMap.get(DcMotor.class, "flywheelLeft");
        flywheelRight = hardwareMap.get(DcMotor.class, "flywheelRight");
        diverter = hardwareMap.get(Servo.class,"diverter");

        /*
         * Note: The settings here assume direct drive on left and right wheels. Gear
         * Reduction or 90 Deg drives may require direction flips
         */

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

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
    }

    @Override
    public void loop() {
        //This is where you paste the void names that you make beneath this loop void. Example: "drivetest();" would play the drivetest void.
        //-Addy

        drivetest();
        telemetry.addLine("loading next void command...");

        sleep(2000);

        telemetry.addLine("next command loaded");
        autonomoustest();

        telemetry.addLine("Autonomous code finished.");
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

        telemetry.addLine("drivetest started");

        /*
        This void is the template/testing class. It won't be used during autonomous.
        Rather, it's a storage for the basic information that can be copy and pasted into the void classes that we actually use during autonomous.
        It has a testrun for forwards, stop, backwards, and eventually there will be strafing.
        This void can be used to rest run speed, and overall mechanics.

        -Addy
         */

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = 0.19;
        double backward = -0.24;
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
        frontRightDrive.setPower(forward - 10);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward - 10);

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

        frontLeftDrive.setPower(forward - 10);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward - 10);
        backRightDrive.setPower(forward);

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

        telemetry.addLine("drivetest finished");
    }

    void autonomoustest(){

        telemetry.addLine("AutonomousTest start");

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
        double forward = 0.80;
        double backward = -0.80;

        //Step One - Drive forward from starting point to the center of the field, then turn around.

        //Forward
        frontLeftDrive.setPower(backward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(backward);
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

        frontLeftDrive.setPower(forward - 10);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward - 10);
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


        telemetry.addLine("AutonomousTest finished");
    }
}
