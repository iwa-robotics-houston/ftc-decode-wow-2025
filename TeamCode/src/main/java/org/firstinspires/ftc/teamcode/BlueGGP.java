package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class BlueGGP {package org.firstinspires.ftc.teamcode;

import static sleep;
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
*/

    //Okay I'm trying to do an auto that includes preloaded artifacts - Carys
    DcMotorEx  frontLeftDrive;
    DcMotorEx frontRightDrive;
    DcMotorEx backLeftDrive;
    DcMotorEx backRightDrive;
    DcMotor intake;
    DcMotorEx flywheelLeft;
    DcMotorEx flywheelRight;
    CRServo launcherLeft;
    CRServo launcherRight;
    Servo diverter;
     /*
         * Note: The settings here assume direct drive on left and right wheels. Gear
@@ -82,47 +85,124 @@
        // Example: "drivetest();" would play a void called drivetest.
        // This autonomous java file should run the forwardsRobot(); void.
        //-Addy
      */
     telemetry.addLine("Autonomous started");
        telemetry.addLine("Blue, GPP");
    void opModeIsActive();
}
void opModeIsActive(){
    //Right now, this code is really rudimentary, does not include limelight or
    //odometry --> this is not what we really want
    // - Carys

    double forward = -0.50;
    double reverse = 0.50;

    //turn slightly
    frontLeftDrive.setPower(forward);
    frontRightDrive.setPower(reverse);
    backLeftDrive.setPower(forward);
    backRightDrive.setPower(reverse);
    sleep(150);

    //start the flywheel
    flywheelRight.setVelocity(-5600);
    flywheelLeft.setVelocity(5600);
    sleep(3000);
    //you'll probably change this

    //launch artifacts
    flywheelRight.setVelocity(-5600);
    flywheelLeft.setVelocity(5600);
    launcherLeft.setPower(-1);
    intake.setPower(1);
    sleep(3000);

    flywheelRight.setVelocity(-5600);
    flywheelLeft.setVelocity(5600);
    launcherLeft.setPower(0);
    launcherRight.setPower(1);
    intake.setPower(1);
    sleep(3000);

    //move one foot forward
    frontLeftDrive.setPower(forward);
    frontRightDrive.setPower(forward);
    backLeftDrive.setPower(forward);
    backRightDrive.setPower(forward);
    flywheelRight.setVelocity(0);
    flywheelLeft.setVelocity(0);
    launcherRight.setPower(0);
    intake.setPower(0);
    sleep(3000);

    //brake!!
    frontLeftDrive.setPower(0);
    frontRightDrive.setPower(0);
    backLeftDrive.setPower(0);
    backRightDrive.setPower(0);
    sleep(3000);


        /*
        //robot drives to the triangle
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        sleep(1700);
//robot turns to face the blue launch target
        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(reverse);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(reverse);
        sleep(300);

        frontLeftDrive.setPower(forward);
        frontRightDrive.setPower(forward);
        backLeftDrive.setPower(forward);
        backRightDrive.setPower(forward);
        sleep(275);
  //robot stops in launch zone
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(3000);
  //robot warms up the flywheel and launches

         */
        flywheelLeft.setPower(1);
        flywheelRight.setPower(-1);

    sleep(8000); //8000 absolutely needed for flywheel

    //robot launches the GREEN artifact
    flywheelRight.setPower(-1);
    flywheelLeft.setPower(1);
    launcherLeft.setPower(-1);
    sleep(2000);

    //robot launches the TWO PURPLE artifacts
    flywheelRight.setPower(-1);
    flywheelLeft.setPower(1);
    launcherRight.setPower(1);
    launcherLeft.setPower(0);
    sleep(5000);

    //robot stops
    frontLeftDrive.setPower(0);
    frontRightDrive.setPower(0);
    backLeftDrive.setPower(0);
    backRightDrive.setPower(0);
    sleep(30000);
    telemetry.addLine("Autonomous finished");
    telemetry.addData("Status", "Completed");

}
}
}
