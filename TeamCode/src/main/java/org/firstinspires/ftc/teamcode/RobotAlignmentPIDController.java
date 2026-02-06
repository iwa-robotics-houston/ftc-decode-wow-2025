package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotAlignmentPIDController {
    private double kP, kI, kD;
    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    public RobotAlignmentPIDController(double p, double i, double d) {
        this.kP = p;
        this.kI = i;
        this.kD = d;
    }

    public double calculate(double targetAngle, double currentAngle) {
        // Calculate the error (Limelight tx value is the error in degrees from center)
        double error = targetAngle - currentAngle;

        // Proportional term
        double proportional = error * kP;

        // Integral term (useful for reducing steady-state error)
        integralSum += (error * timer.seconds());

        // Derivative term (helps dampen oscillations)
        double derivative = (error - lastError) / timer.seconds();

        // Calculate output and reset timer/last error
        double output = proportional + (kI * integralSum) + (kD * derivative);

        lastError = error;
        timer.reset(); // Reset the timer for the next cycle

        // Apply soft limits to the output (e.g., -1.0 to 1.0 motor power)
        // Ensure you don't damage your hardware
        // This is a basic example; adjust limits as needed.
        if (output > 1.0) output = 1.0;
        if (output < -1.0) output = -1.0;

        return output;
    }

    // Method to get Limelight tx (horizontal offset)
    public double getTx() {
        // You would typically get this from your Limelight hardware object
        // Example: return limelight.getLatestResult().getTx();
        // Replace with your actual hardware interaction code
        return 0.0; // Placeholder
    }
}