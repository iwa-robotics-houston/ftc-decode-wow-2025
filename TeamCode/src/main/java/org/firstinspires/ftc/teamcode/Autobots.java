package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

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
    private DcMotorEx frontLeftDrive = null;
    private DcMotorEx backLeftDrive = null;
    private DcMotorEx frontRightDrive = null;
    private DcMotorEx backRightDrive = null;

    //No code for the intake or launcher yet. When we get the teleop version perfected I'll add it here! - Addy
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

        telemetry.addData("Void File Running", "None");
    }

    @Override
    public void loop() {
        //This is where you paste the void names that you make beneath this loop void. Example: "drivetest();" would play the drivetest void.
        //-Addy
        telemetry.addLine("Im transforming it rn");
        telemetry.addLine("Heh... Autobots.... Rev up and roll out!!");

        drivetest();

        sleep(2000);

        //autonomoustest();

        backwardsRobot();

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

        telemetry.addData("Void File Running","DriveTest");

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

        double maxVelocity = 100.00;

        /*
        After testing, I think the numbers for these variables should be a bit higher. As it was a bit slow.
        But they can be individualized for each void class that is actually used.
        These variables make it easier to change the numbers for the code below during testing.
        - Addy
         */

        //Forward
        //You don't need to put numbers for the set powers here, as we defined a number for the variable "forward" and "backward"
        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        //sleep(#) is important so that the robot actually performs the line of code beforehand.
        //1000 = 1 millisecond. And so on and so forth.
        sleep(2000);

        //Waitwaitwaitwaitwait!!!!

        frontLeftDrive.setVelocity(0);
        frontRightDrive.setVelocity(0);
        backLeftDrive.setVelocity(0);
        backRightDrive.setVelocity(0);

        sleep(2000);

        //Back it up now

        frontLeftDrive.setVelocity(backward * maxVelocity);
        frontRightDrive.setVelocity(backward * maxVelocity);
        backLeftDrive.setVelocity(backward * maxVelocity);
        backRightDrive.setVelocity(backward * maxVelocity);

        sleep(2000);

        //Turn RIGHT
        //Right (SMOOTH)

        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward - 10 * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward - 10 * maxVelocity);

        sleep(2000);

        //Right (Sharp)

        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(0);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(0);

        sleep(2000);

        //Right  (In Place)

        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(backward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(backward * maxVelocity);

        sleep(2000);

        //Turn LEFT
        // Left (Smooth)

        frontLeftDrive.setVelocity(forward - 10 * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward - 10 * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(2000);

        //Left (Sharp)

        frontLeftDrive.setVelocity(0);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(0);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(2000);

        //Left (In Place)

        frontLeftDrive.setVelocity(backward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(backward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

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
        double forward = 0.30;
        double backward = -0.40;

        double maxVelocity = 100.00;

        //Step One - Drive forward from starting point to the center of the field, then turn around.

        //Forward
        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(3000);

        //Turn Left (In Place)

        frontLeftDrive.setVelocity(backward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(backward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(2000);


        //Step Two - Drive back towards direction of starting point, but stop and turn and continue driving.

        //Forward
        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(2000);

        //Turn LEFT
        // Left (Smooth)

        frontLeftDrive.setVelocity(forward - 10 * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward - 10 * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(1000);

        //Forward
        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(2000);

        //Waitwaitwaitwaitwait!!!!

        frontLeftDrive.setVelocity(0);
        frontRightDrive.setVelocity(0);
        backLeftDrive.setVelocity(0);
        backRightDrive.setVelocity(0);

        sleep(2000);

        telemetry.addData("Autonomous Status","Finished");
    }

    void backwardsRobot(){

        telemetry.addData("Void File Running", "Backwards");

        //The purpose of this void is to make the robot go backwards around 18 inches.
        //Because Girardot asked <3

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = 0.19;
        double backward = -0.24;

        double maxVelocity = 100.00;

        //Back it up now

        frontLeftDrive.setVelocity(backward * maxVelocity);
        frontRightDrive.setVelocity(backward * maxVelocity);
        backLeftDrive.setVelocity(backward * maxVelocity);
        backRightDrive.setVelocity(backward * maxVelocity);

        sleep(3000);



    }

    void forwardsRobot(){

        telemetry.addData("Void File Running", "Forwards");

        //The purpose of this void is to make the robot go forwards around 18 inches.

        //Variables needed to know how much engine power is needed.
        // The numbers here can be recorded when playing the teleop mode
        double forward = 0.19;
        double backward = -0.24;

        double maxVelocity = 100.00;

        //Back it up now

        frontLeftDrive.setVelocity(forward * maxVelocity);
        frontRightDrive.setVelocity(forward * maxVelocity);
        backLeftDrive.setVelocity(forward * maxVelocity);
        backRightDrive.setVelocity(forward * maxVelocity);

        sleep(3000);

        //peepeepoopoo
        //skibidi
    }
}
