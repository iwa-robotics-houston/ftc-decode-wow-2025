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

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    DcMotor intake;
    DcMotor flywheelLeft;
    DcMotor flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    CRServo diverter;

    // This declares the IMU needed to get the current direction the robot is facing
    IMU imu;

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcherLeft = hardwareMap.get(CRServo.class, "launcherLeft");
        launcherRight = hardwareMap.get(CRServo.class, "launcherRight");
        flywheelLeft = hardwareMap.get(DcMotor.class, "flywheelLeft");
        flywheelRight = hardwareMap.get(DcMotor.class, "flywheelRight");
        //diverter = hardwareMap.get(CRServo.class,"diverter");

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
        telemetry.addLine("Autonomous Drive");
        telemetry.addLine("Moving the lateral joystick left and lateral turns the robot");


        // If you press the left bumper, you get a drive from the point of view of the robot
        // (much like driving an RC vehicle)

        double axial = gamepad1.left_stick_y;
        double lateral = gamepad1.right_stick_x;
        double yaw = gamepad1.left_stick_x;
        drive(axial, lateral, yaw);
    }
        // This routine drives the robot field relative
    /*
       void driveFieldRelative ( double axial, double lateral, double yaw){
            // First, convert direction being asked to drive to polar coordinates
            double theta = Math.atan2(axial, lateral);
            double r = Math.hypot(lateral, axial);

            // Third, convert back to cartesian
            double newAxial = r * Math.sin(theta);
            double newLateral = r * Math.cos(theta);

            // Finally, call the drive method with robot relative axial and lateral amounts
            drive(newAxial, newLateral, yaw);
        }
        */

        // Thanks to FTC16072 for sharing this code!!
        void drive( double axial, double lateral, double yaw){
            // This calculates the power needed for each wheel based on the amount of axial,
            // strafe lateral, and yaw

            double frontLeftVelocity = axial + lateral + yaw;
            double frontRightVelocity = axial - lateral - yaw;
            double backRightVelocity = axial + lateral - yaw;
            double backLeftVelocity = axial - lateral + yaw;

            double maxPower = 1.0;
            double maxSpeed = 2788;  // make this slower for outreaches
            double maxVelocity = 1.0;
            //This velocity is the MAX velocity


            // This is needed to make sure we don't pass > 1.0 to any wheel
            // It allows us to keep all of the motors in proportion to what they should
            // be and not get clipped
            maxVelocity = Math.max(maxVelocity, Math.abs(frontLeftVelocity));
            maxVelocity = Math.max(maxVelocity, Math.abs(frontRightVelocity));
            maxVelocity = Math.max(maxVelocity, Math.abs(backRightVelocity));
            maxVelocity = Math.max(maxVelocity, Math.abs(backLeftVelocity));

            // We multiply by maxSpeed so that it can be set lower for outreaches
            // When a young child is driving the robot, we may not want to allow full
            // speed.

            frontLeftDrive.setVelocity(maxSpeed * (frontLeftVelocity / maxVelocity));
            frontRightDrive.setVelocity(maxSpeed * (frontRightVelocity / maxVelocity));
            backLeftDrive.setVelocity(maxSpeed * (backLeftVelocity / maxVelocity));
            backRightDrive.setVelocity(maxSpeed * (backRightVelocity / maxVelocity));


            telemetry.addData("status", "Run Time:" + runtime);
            telemetry.addData("Front left/right", "%4.2f,%4.2f", frontLeftVelocity, frontRightVelocity);
            telemetry.addData("Back left/right", "%4.2f,%4.2f", backLeftVelocity, backRightVelocity);
            telemetry.update();


                    //this is JUST intake
            double intakePower = 1;

            float intakeIn = gamepad2.right_trigger;
            float intakeOut = gamepad2.left_trigger;

            if (gamepad2.right_trigger > 0) {
                intake.setPower(-1);}
            else if (gamepad2.left_trigger > 0) {
                intake.setPower(1);}
            else {
                intake.setPower(0);
            }

            //Launcher + flywheels
            //These should be working simultaneously

            double launcherLeftPower = 1;
            double launcherRightPower = 1;


            if (gamepad2.y){
                launcherLeft.setPower(-1);
                flywheelRight.setPower(-1);
                flywheelLeft.setPower(1);
            } else{
                launcherLeft.setPower(0);
                flywheelRight.setPower(0);
                flywheelLeft.setPower(0);
            }

            if(gamepad2.b){
                launcherRight.setPower(1);
                flywheelRight.setPower(-1);
                flywheelLeft.setPower(1);
            } else{
                launcherRight.setPower(0);
                flywheelRight.setPower(0);
                flywheelLeft.setPower(0);
            }
        }
    }


