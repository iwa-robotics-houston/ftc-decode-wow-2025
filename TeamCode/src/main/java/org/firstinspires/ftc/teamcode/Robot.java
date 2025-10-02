package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
Hey chat, uh at the moment this is just a copy and paste of the Robot.Java from last year.
I haven't changed much yet, so bare with me. You'll probably see the comments I used
as notes/lists somewhere below this comment so uh have fun with this file i guess?
- Addy
 */

/*
Notes from what the build team told me:
- 4 Mech Wheels: front-right, front-left etc etc
- Arm/Servo/Continuous Rotation Servo
- Direct Current motor (all robots for FTC have these apparently so uh)
- I was told to look at pedro pathing but that isn't relevant for Robot.Java but anyways.
 */

/*
Contains robot build, state, and transformation functions.
Used to init hardware when an OpMode is run and to control hardware and track state during a run.
hardwareMap names come from the robot configuration step on the DS or DC.
 */

public class Robot {
    // dimensions
    final public double drivetrainDiagonal = 19.5; // in
    public boolean isArmClawOpen;
    public boolean isMiniClawOpen;
    double cpr = 537.7; // clicks
    double wheelCirc = 11.9; // in
    static final double vertSlideWheelCirc = Math.PI * 1.5;

    static final double armSlideWheelCirc = Math.PI * 1.5;
    // to correct movement lengths
    static final double drivetrainMultiplier =  1.5;

    // limits
    final public double miniClawOpenPos = 0.3;
    final public double miniClawClosePos = 0.0;

    final public double vertSLideMaxLen = 19.15; // in
    final public double armSLideMaxLen = 18; // in

    // state
    public boolean rightBumperPrev = false;
    public ElapsedTime timer = new ElapsedTime();  // Create a timer instance
    public double lastIntakeTime = 0;
    public double intakeInterval = 2.0;  // Adjust timing as needed (seconds)


    // motors
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor leftBackDrive;
    public DcMotor rightBackDrive;

    public DcMotor arm1;
    public DcMotor arm2;
    public DcMotor armSlide;
    public DcMotor vertSlide;

    //servos
    public CRServo intakeServo1;
    public CRServo intakeServo2;
    public Servo miniClawServo;
    public Servo wristServo;

    public Robot(HardwareMap hardwareMap) {
        // init hardware
        leftFrontDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontRight");
        leftBackDrive = hardwareMap.get(DcMotor.class, "backLeft");
        rightBackDrive = hardwareMap.get(DcMotor.class, "backRight");

        arm1 = hardwareMap.get(DcMotor.class, "arm1");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        armSlide = hardwareMap.get(DcMotor.class, "armSlide");
        vertSlide = hardwareMap.get(DcMotor.class, "vertSlide");

        intakeServo1 = hardwareMap.get(CRServo.class, "intakeServo1");
        intakeServo2 = hardwareMap.get(CRServo.class, "intakeServo2");
        miniClawServo = hardwareMap.get(Servo.class, "miniClaw");
        wristServo = hardwareMap.get(Servo.class, "wristServo");

        // configure drive motors
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        /*// Configure encoders
        drivetrainSetRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrainSetRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
         */

        // Configure servos
        intakeServo1.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeServo2.setDirection(DcMotorSimple.Direction.REVERSE);
        wristServo.setDirection(Servo.Direction.REVERSE);

        // Configure slides
        arm1.setDirection(DcMotor.Direction.REVERSE);
        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setDirection(DcMotor.Direction.REVERSE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armSlide.setDirection(DcMotor.Direction.FORWARD);
        armSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        vertSlide.setDirection(DcMotor.Direction.REVERSE);
        vertSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vertSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Moves the vertical slide to a specified position (in inches).
    // If blockReturn is true, the method will wait until movement is complete.
    // Movement is relative; power is a float in the range [0.0, 1.0].
    public boolean vertSlideToPosition(double in, double power, boolean blockReturn) {

        // Check if the requested movement exceeds the slide's maximum length.
        // If it does, return false and do not move the slide.
        if (in > vertSLideMaxLen) return false;

        // Convert inches to encoder counts.
        // The calculation uses the wheel circumference and encoder counts per revolution (cpr).
        double movementClicks = in / vertSlideWheelCirc * cpr;

        // Set the target position for the motor (rounded to the nearest integer).
        vertSlide.setTargetPosition((int)(movementClicks + 0.5));

        // Set the motor to run automatically to the target position.
        vertSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the motor power (speed) to the given value.
        vertSlide.setPower(power);

        // If blockReturn is false, return immediately and let the movement continue in the background.
        if (!blockReturn) return true;

        // If blockReturn is true, wait until the motor reaches its target.
        while (vertSlide.isBusy()) {  // Keep looping while the motor is still moving.
            try {
                sleep(50);  // Pause for 50ms before checking again.
            } catch (InterruptedException e) {
                // If the thread is interrupted, restore its interrupted state and exit the loop.
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Return true to indicate the slide has reached the target position.
        return true;
    }


    // Moves the arm slide to a specified position (in inches).
    // If blockReturn is true, the method will wait until movement is complete.
    // movement is relative; power is a float in the range [0.0, 1.0]
    // optionally block until movement completion
    public boolean armSlideToPosition(double in, double power, boolean blockReturn) {
        if (in > armSLideMaxLen) return false;
        double movementClicks = in / armSlideWheelCirc * cpr;

        armSlide.setTargetPosition((int)(movementClicks + 0.5));
        armSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armSlide.setPower(power);

        if (!blockReturn) return true;
        while (armSlide.isBusy()) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } return true;
    }
}
    /*
    public void drivetrainSetRunMode(DcMotor.RunMode mode) {
        leftFrontDrive.setMode(mode);
        rightFrontDrive.setMode(mode);
        leftBackDrive.setMode(mode);
        rightBackDrive.setMode(mode);
    }

    // Move linearly from the current position to the specified relative point.
    // Function conditionally blocks until movement completion.
    // x and y are in inches; power is a float in the range [0.0, 1.0]
    public void driveToPosition(float x, float y, double power, boolean blockReturn) {
        // no movement required
        if (x == 0 && y == 0) return;
        // atan2 handles quadrants and x == 0 safely
        double theta = Math.atan2(y, x);

        // Compute motor power ratios
        double ADRatio = Math.cos(theta - Math.PI / 4);
        double BCRatio = Math.sin(theta - Math.PI / 4);

        // max ratio for power scaling
        double maxRatio = Math.max(Math.abs(ADRatio), Math.abs(BCRatio));

        // encoder target position determines travel distance
        // values are rounded to nearest integer
        // multiplier corrects movement length
        double clicks = Math.sqrt(y*y + x*x) / wheelCirc * cpr * drivetrainMultiplier;
        leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + (int)(clicks * ADRatio + 0.5));
        rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + (int)(clicks * BCRatio + 0.5));
        leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + (int)(clicks * BCRatio + 0.5));
        rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + (int)(clicks * ADRatio + 0.5));

        // enable distance based movement
        drivetrainSetRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        // begin movement; direction is based on motor power
        leftFrontDrive.setPower(ADRatio/maxRatio * power);
        rightFrontDrive.setPower(BCRatio/maxRatio * power);
        leftBackDrive.setPower(BCRatio/maxRatio * power);
        rightBackDrive.setPower(ADRatio/maxRatio * power);

        // conditionally block return until movement is complete
        if (!blockReturn) return; // Exit if non-blocking mode

        while (leftFrontDrive.isBusy() || rightFrontDrive.isBusy() ||
                        leftBackDrive.isBusy() || rightBackDrive.isBusy()) { // Check if motors are moving
            try {
                Thread.sleep(50); // Wait briefly before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                break; // Exit loop on interruption
            }
        }
    }

    public void driveToPositionREL(float x, float y, double power, boolean blockReturn) {
        // no movement required
        if (x == 0 && y == 0) return;
        // atan2 handles quadrants and x == 0 safely
        double theta = Math.atan2(y, x);

        // Compute motor power ratios
        double ADRatio = Math.cos(theta - Math.PI / 4);
        double BCRatio = Math.sin(theta - Math.PI / 4);

        // max ratio for power scaling
        double maxRatio = Math.max(Math.abs(ADRatio), Math.abs(BCRatio));

        // Reset encoders so movement is done from a reference position
        drivetrainSetRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // encoder target position determines travel distance
        // values are rounded to nearest integer
        // multiplier corrects movement length
        double clicks = Math.sqrt(y*y + x*x) / wheelCirc * cpr * drivetrainMultiplier;
        leftFrontDrive.setTargetPosition((int)(clicks * ADRatio + 0.5));
        rightFrontDrive.setTargetPosition((int)(clicks * BCRatio + 0.5));
        leftBackDrive.setTargetPosition((int)(clicks * BCRatio + 0.5));
        rightBackDrive.setTargetPosition((int)(clicks * ADRatio + 0.5));

        // enable distance based movement
        drivetrainSetRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        // begin movement; direction is based on motor power
        leftFrontDrive.setPower(ADRatio/maxRatio * power);
        rightFrontDrive.setPower(BCRatio/maxRatio * power);
        leftBackDrive.setPower(BCRatio/maxRatio * power);
        rightBackDrive.setPower(ADRatio/maxRatio * power);

        // conditionally block return until movement is complete
        if (!blockReturn) return; // Exit if non-blocking mode

        while (leftFrontDrive.isBusy() || rightFrontDrive.isBusy() ||
                leftBackDrive.isBusy() || rightBackDrive.isBusy()) { // Check if motors are moving
            try {
                Thread.sleep(50); // Wait briefly before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                break; // Exit loop on interruption
            }
        }
    }

    // degrees is used for familiarity
    // positive power for clockwise, negative for counterclockwise
    public void rotate(double degrees, double power, boolean blockReturn) {

        // Reset encoders to ensure accurate movement
        drivetrainSetRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Determine encoder target position for turn
        // Turning Mecanum drive is normal tank tread movement
        // drivetrain circumference / wheel circumference = number of rotations
        // num rotations * cpr = clicks for a full circle
        // clicks per circle * (fraction of a whole circle as [degrees / 360]) = clicks for movement
        // clicks for movement * scale factor = adjusted clicks
        int turnClicks = (int) (((Math.PI * drivetrainDiagonal) / wheelCirc) * cpr * (degrees / 360) * drivetrainMultiplier);

        // Set target positions for a 180-degree turn
        //Instead of moving motors in a linear direction (x, y), function makes the left side move forward and the right side move backward
        leftFrontDrive.setTargetPosition(turnClicks);
        leftBackDrive.setTargetPosition(turnClicks);
        rightFrontDrive.setTargetPosition(-turnClicks);
        rightBackDrive.setTargetPosition(-turnClicks);

        // Enable encoder distance-based movement
        drivetrainSetRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power for rotation
        //ensures that both sides rotate at the same speed
        leftFrontDrive.setPower(power);
        leftBackDrive.setPower(power);
        rightFrontDrive.setPower(power);
        rightBackDrive.setPower(power);

        // Block until movement is complete if required
        if (!blockReturn) return;

        //ensures the robot fully completes the turn before continuing
        while (leftFrontDrive.isBusy() || rightFrontDrive.isBusy() ||
                leftBackDrive.isBusy() || rightBackDrive.isBusy()) {
            try {
                Thread.sleep(50); // Wait briefly before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    */
//please work im begging you