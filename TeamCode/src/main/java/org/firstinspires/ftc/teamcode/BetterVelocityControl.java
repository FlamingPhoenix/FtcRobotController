package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="better velocity control", group="none")
public class BetterVelocityControl extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        /*
        tangent - we are tuning the velocity
        so should we use the derivative of the velocity, velocity and the integral of velocity instead
        aka position, velocity and acceleration
        */

        float Kp = 0;//creates oscilator
        float kd = 0;//damper
        float ki = 0;//i should be very small so we get rid of the steady state error and can actually reach the velocity while not getting a ridiculous out variable value and occilation at the end of the op mode

        float reference = 1000;//desired speed in counts/sec - this is ab 1 rev/sec

        float derivative;

        float integral = 0;

        float out;

        ElapsedTime timer = new ElapsedTime();
        ElapsedTime globalTimer = new ElapsedTime();

        DcMotor motor;
        motor = hardwareMap.dcMotor.get("motor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        int currentPosition = 0;

        int lastPosition = 0;

        float error;

        float lastError = 0;

        timer.reset();

        while (opModeIsActive() && globalTimer.seconds()<30) {
            currentPosition = Math.abs(motor.getCurrentPosition());

            error = (float) (reference - ((currentPosition-lastPosition)/timer.seconds()));
            derivative = (float)((error - lastError)/timer.seconds());
            integral = motor.getCurrentPosition();
            out = ((Kp*error) + (kd*derivative) + (ki*integral));

            motor.setPower(out);

            //reseting changing variables
            lastError = error;
            lastPosition = currentPosition;
            timer.reset();

            //telemetry updates
            if (((currentPosition-lastPosition)/timer.seconds()) > (reference - 20))//tolerance of 20
                telemetry.addData("Desired velocity reached", 0);
            if (((currentPosition-lastPosition)/timer.seconds()) < (reference - 20))//tolerance of 20
                telemetry.addData("Desired velocity not reached", error);
        }

        motor.setPower(0);
    }
}
