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
This java file is made specifically for testing autonomous code. Anything that works
will be taken from here and given it's own java file.
Okay? okay slay
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

        //telemetry.addData("Void File Running","DriveTest");

        //sleep(100);

        //drivetest();

        //sleep(2000);

        //autonomoustest();

        //telemetry.addData("Void File Running", "Backwards");

        //sleep(100);

        //backwardsRobot();

        //sleep(2000);

        //telemetry.addData("Void File Running", "Forwards");

        //sleep(100);

        //forwardsRobot();

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

    void odometryCore(){

        //This is all a big big WIP, I only have the main notes written as of right now.
        //Feel free to peruse the notes and see if you can find anything of use from it. - Addy

        /*

        Equations for the Robot and Other Notes

        >>fwd = (R + L)/2
        (Simple For Forward)

        >>fwd = ((R*L_y) - (L*R_y))/(L_y-R_y)
        (one that can better fit the parameters of the robot if the sensor isn't centered)

        (L_y and R_y are the Y coordinates of the encoders respectfully.)
        (L_y - R_y represents distance between the pods in y-axis)

        >>0 = R - L / (L_y - R_y) (The equation for heading/direction)

        (0 (theta) is the mathematical symbol for heading)
        (R & L are the amount the left & right pods have moved respectively)

        When the robot moves forward, both wheels move in the same direction, cancelling the heading.
        But when the robot rotates, the heading is active and the forward cancels.
        Finally for strafing, the wheels don't move so there is no change to forward or heading.
        (This may not be fully accurate as I am writing down the explanation from a video and my memory is piss poor with stuff like this) - Addy

        Equation to find out how much the robot has strafed:
        >>Str = B - B_x * 0

        >>Linear Odometry Math (Delta my beloathed):

        x_n = x_(n-1) + relΔX(_n)cos(0_n) - relΔY(_n)sin(0_n)
        y_n = y_(n-1) + relΔY(_n)cos(0_n) + relΔX(_n)sin(0_n)


        The faster the loops, the more accurate the approximation.
        But the physical construction of the odometry pods will contribute more error after a while.

        To increase accuracy without loop speed you need to make assumptions that are more accurate to a robot's movements.

        >>Equation for Constant Velocity Arc Odometry:
        relΔX = Δr(_0)sin(Δ0) - r(_1)(1-cos(Δ0))
        relΔY = Δr(_1)sin(Δ0) + r(_0)(1 - cos(Δ0))

        r_0 = Δfwd/Δ0
        r_1 = Δstr/Δ0

         */

        /*
        Tips:

        1. Build your odometry pods as sturdy as possible
        2. Make sure they can spin freely and are not obstructed.
        3. Increase loop speeds and use a good approximation.
        4. Measure the pod's positions accurately
        5. Know the limits of your odo
         */

        double x_n = x_(n-1) + relΔX(_n)cos(0_n) - relΔY(_n)sin(0_n);
        double y_n = y_(n-1) + relΔY(_n)cos(0_n) + relΔX(_n)sin(0_n);

        double relΔX = Δr(_0)sin(Δ0) - r(_1)(1-cos(Δ0));
        double relΔY = Δr(_1)sin(Δ0) + r(_0)(1 - cos(Δ0));

        double r_0 = Δfwd/Δ0;
        double r_1 = Δstr/Δ0;

    }

    void okayInternAnnieNowHitTheSecondTower(){

        //The purpose of this void is to potentially have the robot launch balls.
        //It is still heavily under construction so uh i wouldn't recommend using it.

        //this is JUST intake
        double intakePower = 1;

        double intakeIn = -1;
        double intakeOut = 1;

        //Launcher + flywheels

        double launcherLeftPowerStart = 1;
        double launcherLeftPowerStop = 0;
        double launcherRightPowerStart = 1;
        double launcherRightPowerStop = 0;

        //Goal: for autonomous it would be a lot easier if the robot had a sensor to sense balls/read april tags
        //That way I could do an if/then/else statement with how autonomous works.
        //I want the robot to keep driving until It is close to an obstacle, then i want it to turn a certain direction.
        //but thats hard to do without sensors

        //Guys i have no clue what I'm doing and Im lowkey kinda burnt out bear with me I'll have code made by ythe next meet trust - Addy


        intake.setPower(-1);
        intake.setPower(1);
        intake.setPower(0);


        //Possible Diverter
        if (gamepad2.dpad_left) diverter.setPosition(0);
        else if (gamepad2.dpad_right) {
            diverter.setPosition(.70);
        } else {
            diverter.setPosition(.5);
        }


        if (gamepad2.left_bumper) {
            launcherLeft.setPower(-1);

        } else {
            launcherLeft.setPower(0);
        }

        if (gamepad2.right_bumper) {
            launcherRight.setPower(1);

        } else {
            launcherRight.setPower(0);
        }

        if (gamepad2.a) {
            flywheelRight.setPower(-1);
            flywheelLeft.setPower(1);
        } else {
            flywheelRight.setPower(0);
            flywheelLeft.setPower(0);
        }
    }
}
