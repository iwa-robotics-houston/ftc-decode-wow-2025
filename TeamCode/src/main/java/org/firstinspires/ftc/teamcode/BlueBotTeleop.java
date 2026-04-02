package org.firstinspires.ftc.teamcode;

/*
This is a test to see if the drive and intake code will work together,
includes strafe and I'm hoping this will fix our driving and intake problems.
- Carys


/* Copyright (c) 2025 FIRST. All laterals reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT lateralS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import static java.lang.Thread.sleep;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
//import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;


/*
 * This OpMode illustrates how to program your robot to drive field relative.  This means
 * that the robot drives the direction you push the joystick regardless of the current orientation
 * of the robot.
 *
 * This OpMode assumes that you have four mecanum wheels each on its own motor named:
 *   front_left_motor, front_lateral_motor, back_left_motor, back_lateral_motor
 *
 *   and that the left motors are flipped such that when they turn clockwise the wheel moves backwards
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 *
 */
@TeleOp(name = "BlueBotTeleop", group = "Robot")
public class BlueBotTeleop extends OpMode {

    ElapsedTime runtime = new ElapsedTime();

    // This declares the motors + servos needed
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    DcMotorEx flywheelL;
    DcMotorEx flywheelR;
    CRServo launcher;
    CRServo passThrough;
    GoBildaPinpointDriver imu;
    RevBlinkinLedDriver light;
    Limelight limelight;


    double targetVelocity;
    double maxVelocity;

    // This declares the IMU needed to get the current direction the robot is facing
    //fixed this to0
    //GoBildaPinpointDriver imu;

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        passThrough = hardwareMap.get(CRServo.class, "pass");
        launcher = hardwareMap.get(CRServo.class, "launcher");
        flywheelL = hardwareMap.get(DcMotorEx.class, "flywheelL");
        flywheelR = hardwareMap.get(DcMotorEx.class, "flywheelR");
        imu = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        light = hardwareMap.get(RevBlinkinLedDriver.class, "light");
        limelight = new Limelight();
        limelight.initLimelight(hardwareMap);


        //  diverter = hardwareMap.get(Servo.class,"diverter");

        // We set the left motors in reverse which is needed for drive trains where the left
        // motors are opposite to the right ones.
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);

        // This uses RUN_USING_ENCODER to be more accurate.   If you don't have the encoder
        // wires, you should remove these
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flywheelL.setVelocityPIDFCoefficients(200, 0, 0, 14);
        flywheelR.setVelocityPIDFCoefficients(200, 0, 0, 14);
//f is 14 btw
//fixed this and hen imported hardword
        //imu = hardwareMap.get(GoBildaPinpointDriver.class, "imu");
        // This needs to be changed to match the orientation on your robot
        // RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
        //      RevHubOrientationOnRobot.LogoFacingDirection.UP;
        // RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
        //  RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        //  RevHubOrientationOnRobot orientationOnRobot = new
        //   RevHubOrientationOnRobot(logoDirection, usbDirection);
        // imu.initialize();
        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        RevBlinkinLedDriver.BlinkinPattern readyColor = RevBlinkinLedDriver.BlinkinPattern.BLACK;
    }


    RevBlinkinLedDriver.BlinkinPattern readyColor = RevBlinkinLedDriver.BlinkinPattern.BLACK;

    @Override
    public void loop() {
        PIDFCoefficients coefficients = flywheelL.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addLine("Teleop Drive");
        telemetry.addLine("Women of the Wires");
        telemetry.addData("Velocity", targetVelocity);
        telemetry.addData("P", coefficients.p);
        telemetry.addData("I", coefficients.i);
        telemetry.addData("D", coefficients.d);
        telemetry.addData("F", coefficients.f);
        telemetry.addData("MotorControlAlgorithm", coefficients.algorithm);

        // If you press the left bumper, you get a drive from the point of view of the robot
        // (much like driving an RC vehicle)

        double axial = -gamepad1.left_stick_y;
        double lateral = gamepad1.right_stick_x;
        double yaw = gamepad1.left_stick_x;


        drive(axial, lateral, yaw);

        double targetVelocity = 0;
        double maxVelocity = 0;
        RevBlinkinLedDriver.BlinkinPattern readyColor;
        double scanGoal;
    }


    // Thanks to FTC16072 for sharing this code!!
    void drive(double axial, double lateral, double yaw) {
        // This calculates the power needed for each wheel based on the amount of axial,
        // strafe lateral, and yaw
        double frontLeftPower = axial + lateral + yaw;
        double frontRightPower = axial - lateral - yaw;
        double backRightPower = axial - lateral + yaw;
        double backLeftPower = axial + lateral - yaw;

        double maxPower = 1.0;
        double maxSpeed = 1.0;
        int targetPosition = 7;


        // This is needed to make sure we don't pass > 1.0 to any wheel
        // It allows us to keep all of the motors in proportion to what they should
        // be and not get clipped

        /*
        maxSpeed = Math.max(maxPower, Math.abs(frontLeftPower));
        maxSpeed = Math.max(maxPower, Math.abs(frontRightPower));
        maxSpeed = Math.max(maxPower, Math.abs(backRightPower));
        maxSpeed = Math.max(maxPower, Math.abs(backLeftPower));
        */

        // We multiply by maxSpeed so that it can be set lower for outreaches
        // When a young child is driving the robot, we may not want to allow full
        // speed.


        frontLeftDrive.setPower(maxSpeed * (frontLeftPower / maxPower));
        frontRightDrive.setPower(maxSpeed * (frontRightPower / maxPower));
        backLeftDrive.setPower(maxSpeed * (backLeftPower / maxPower));
        backRightDrive.setPower(maxSpeed * (backRightPower / maxPower));

        /*
        if (maxSpeed) {
            frontLeftPower /= maxSpeed;
            frontRightPower /= maxSpeed;
            backLeftPower /= maxSpeed;
            backRightPower /= maxSpeed;
        }
        */


        telemetry.addData("status", "Run Time:" + runtime);
        telemetry.addData("Front left/right", "%4.2f,%4.2f", frontLeftPower, frontRightPower);
        telemetry.addData("Back left/right", "%4.2f,%4.2f", backLeftPower, backRightPower);
        telemetry.addData("speed left", flywheelL.getVelocity());
        telemetry.addData("speed right", flywheelR.getVelocity());
        telemetry.update();

        //intake
        if (gamepad2.right_trigger > 0) {
            intake.setPower(-1);
        } else if (gamepad2.left_trigger > 0) {
            intake.setPower(1);
        } else {
            intake.setPower(0);
        }


        //pass through
        if (gamepad2.dpad_up) {
            passThrough.setPower(-1);
        } else if (gamepad2.dpad_down) {
            passThrough.setPower(1);
        } else {
            passThrough.setPower(0);
        }

        //launch
        if (gamepad2.right_bumper) {
            launcher.setPower(1);
        } else if (gamepad2.left_bumper) {
            launcher.setPower(-1);
        } else {
            launcher.setPower(0);
        }

        //BOTH FLYWHEELS IN SAME DIRECTION
        //on flywheel
        if (gamepad2.b) {
            targetVelocity = 1290;
            maxVelocity = 1400;
            flywheelL.setVelocity(-targetVelocity);
            flywheelR.setVelocity(-targetVelocity);
            readyColor = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;

        }

        limelight.setPipeline(8);
        //blue goal
        if (gamepad1.left_trigger > 0.1) {
            limelight.updateLimelight();
            limelight.scanGoal();
            if (limelight.resultWorks() && limelight.getTa() > 1.7) {
                frontLeftDrive.setPower(-1);
                frontRightDrive.setPower(-1);
                backLeftDrive.setPower(-1);
                backRightDrive.setPower(-1);
            } else if (limelight.resultWorks() && limelight.getTa() < 1.7) {
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
                telemetry.addLine("blue goal");
            }
        }

            if (gamepad1.right_trigger > 0.1) {
                limelight.updateLimelight();
                limelight.scanGoal();
                if (limelight.resultWorks() && limelight.getTx() < -4) {
                    frontLeftDrive.setPower(-.5);
                    frontRightDrive.setPower(.5);
                    backLeftDrive.setPower(-.5);
                    backRightDrive.setPower(.5);
                } else if (limelight.resultWorks() && limelight.getTx() > -4) {
                    frontLeftDrive.setPower(0);
                    frontRightDrive.setPower(0);
                    backLeftDrive.setPower(0);
                    backRightDrive.setPower(0);
                    telemetry.addLine("blue goal");
                }
            }

                if (gamepad2.a) {
                    targetVelocity = 1700;
                    maxVelocity = 1750;
                    flywheelL.setVelocity(-targetVelocity);
                    flywheelR.setVelocity(-targetVelocity);
                    readyColor = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;
                }

                //off flywheel
                if (gamepad2.y) {
                    flywheelL.setVelocity(0);
                    flywheelR.setVelocity(0);
                }

                //out flywheel
                if (gamepad2.x) {
                    flywheelL.setVelocity(targetVelocity);
                    flywheelR.setVelocity(targetVelocity);
                }


                //light code
                if (targetVelocity > 0) {
                    double flywheelVelocity = Math.abs(flywheelL.getVelocity());

                    if (flywheelVelocity >= targetVelocity) {
                        light.setPattern(readyColor);
                    } else if (flywheelVelocity >= maxVelocity) {
                        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
                    } else {
                        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
                    }

                    if (flywheelVelocity < targetVelocity) {
                        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
                    }
                }



    /*
    @TeleOp(name = "LimelightPIDTest")
    public class LimelightPIDTest extends LinearOpMode {
        private DcMotor leftMotor, rightMotor; // Example motors for a drivetrain
        private RobotAlignmentPIDController pidController;
        private Limelight3A limelight;

        // Define initial PID constants (tune these values later)
        private final double kP = 0.05; // Start with a small Kp
        private final double kI = 0.0;
        private final double kD = 0.0;

        @Override
        public void runOpMode() throws InterruptedException {
            // Hardware map motors (replace with your motor names)
            leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
            rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

            // Initialize Limelight hardware object
            limelight = hardwareMap.get(Limelight3A.class, "limelight");

            // Initialize PID controller
            pidController = new RobotAlignmentPIDController(kP, kI, kD);

            waitForStart();

            while (opModeIsActive()) {
                // Get the horizontal offset (tx) from the Limelight
                double tx = limelight.getLatestResult().getTx();
                boolean hasTarget = limelight.getLatestResult().isValid(); // Check if target is valid

                if (hasTarget) {
                    // The target angle is 0.0 (center of the screen)
                    double motorPower = pidController.calculate(0.0, tx);

                    // Use the output to control motors
                    // Adjust motor logic based on robot setup (e.g., tank drive, swerve)
                    leftMotor.setPower(-motorPower);
                    rightMotor.setPower(motorPower);
                } else {
                    // Stop motors or implement a search pattern if no target is found
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                }

                // Add telemetry for tuning and debugging
                telemetry.addData("Target X Offset (tx)", tx);
                telemetry.addData("Motor Power", leftMotor.getPower());
                telemetry.update();
            }
        }
    }
    */
            }
        }
