package org.firstinspires.ftc.teamcode;

public class Odometry {

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

    // As of right now, I am working on a Robot.Java before I go any further with odometry. - Addy

}
