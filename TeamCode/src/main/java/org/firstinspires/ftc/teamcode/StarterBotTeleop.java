package org.firstinspires.ftc.teamcode;

/*
This is a test to see if the drive and intake code will work together,
includes strafe and I'm hoping this will fix our driving and intake problems.
- Carys

For future reference, because I'm struggling to find stuff due to the minor
 layout changes made when the code was slightly overhauled,
 If you remove/add/change anything, please comment where the change was made
  so that someone who is here more often will have a better ability
  to read the newer code versions you make.
 This is just because I'm still learning Java so my main method of
 learning is pattern recognition so having context helps :D
 -Addy
*/

/* Copylateral (c) 2025 FIRST. All laterals reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copylateral notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copylateral notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT lateralS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYlateral HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYlateral OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

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
@TeleOp(name = "StarterBotTeleop", group = "Robot")
public class StarterBotTeleop extends OpMode {

    ElapsedTime runtime = new ElapsedTime();

    // This declares the motors + servos needed
    DcMotorEx frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotorEx intake;
    DcMotorEx flywheelLeft;
    DcMotorEx flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;

   //Blue limelight code AprilTag 20 pipeline 0
    public class aprilTagBlueLimelightTest extends OpMode{
        private Limelight3A limelight;
        private IMU imu;

        @Override
        public void init(){
            limelight = hardwareMap.get(Limelight3A.class, "limelight");
            limelight.pipelineSwitch(0);//april tag #20 pipeline
            limelight.start();

            imu = hardwareMap.get(IMU.class,"imu");
            RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
            imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
        }

        @Override
        public void start(){
            limelight.start();
        }

        @Override
        public void loop(){
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            limelight.updateRobotOrientation(orientation.getYaw());
            LLResult llResult = limelight.getLatestResult();
            if(llResult != null && llResult.isValid()){
                Pose2D botPose = llResult.getBotpose_MT2();

                telemetry.addData("Tx", llResult.getTx());
                telemetry.addData("Ty", llResult.getTy());
                telemetry.addData("Ta", llResult.getTa());
            }
        }
    }

    //Red Limelight code AprilTag 24 pipeline 1:
    public class aprilTagRedLimelightTest extends OpMode{
        private Limelight3A limelight;
        private IMU imu;

        @Override
        public void init(){
            limelight = hardwareMap.get(Limelight3A.class, "limelight");
            limelight.pipelineSwitch(1);//april tag #24 pipeline
            limelight.start();

            imu = hardwareMap.get(IMU.class,"imu");
            RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
            imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
        }

        @Override
        public void start(){
            limelight.start();
        }

        @Override
        public void loop(){
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            limelight.updateRobotOrientation(orientation.getYaw());
            LLResult llResult = limelight.getLatestResult();
            if(llResult != null && llResult.isValid()){
                Pose2D botPose = llResult.getBotpose_MT2();

                telemetry.addData("Tx", llResult.getTx());
                telemetry.addData("Ty", llResult.getTy());
                telemetry.addData("Ta", llResult.getTa());
            }
        }
    }



    // This declares the IMU needed to get the current direction the robot is facing
    IMU imu;

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        launcherLeft = hardwareMap.get(CRServo.class, "launcherLeft");
        launcherRight = hardwareMap.get(CRServo.class, "launcherRight");
        flywheelLeft = hardwareMap.get(DcMotorEx.class, "flywheelLeft");
        flywheelRight = hardwareMap.get(DcMotorEx.class, "flywheelRight");
       diverter = hardwareMap.get(Servo.class,"diverter");

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

        imu = hardwareMap.get(IMU.class, "imu");
        // This needs to be changed to match the orientation on your robot
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    @Override
    public void loop() {
        telemetry.addLine("Teleop Drive");
        telemetry.addLine("WOW 2025 Code");


        // If you press the left bumper, you get a drive from the point of view of the robot
        // (much like driving an RC vehicle)


        double axial = gamepad1.left_stick_y;
        double lateral = gamepad1.right_stick_x;
        double yaw = gamepad1.left_stick_x;


        drive(axial, lateral, yaw);
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
        double maxVelocity = 2788;
        //This velocity is the MAX velocity


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
            telemetry.addData("Flywheel Left", "%4.2f,%4.2f", flywheelLeft.getVelocity());
            telemetry.addData("Flywheel Right", "%4.2f,%4.2f", flywheelRight.getVelocity());
            telemetry.update();


            //this is JUST intake
            double intakePower = 1;

            float intakeIn = gamepad2.right_trigger;
            float intakeOut = gamepad2.left_trigger;

            if (gamepad2.right_trigger > 0) {
                intake.setPower(-1);
            } else if (gamepad2.left_trigger > 0) {
                intake.setPower(1);
            } else {
                intake.setPower(0);
            }


            //Possible Diverter
        if (gamepad2.dpad_left) diverter.setPosition(0);
            else if (gamepad2.dpad_right) {
                diverter.setPosition(.70);
            } else {
                diverter.setPosition(.5);
            }
            

            //Launcher + flywheels
            //They've just been broken up into separate buttons

            double launcherLeftPower = 1;
            double launcherRightPower = 1;


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



