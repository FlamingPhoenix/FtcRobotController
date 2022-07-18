package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="velocity control", group="none")
public class VelocityControl extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        DcMotor motor;
        motor = hardwareMap.dcMotor.get("motor");
        float PPR = 1120f;
        float angularVelocity = 10;//counts/second
        float out = 0;

        ElapsedTime timer = new ElapsedTime();
        ElapsedTime globalTimer = new ElapsedTime();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        float currentPosition = 0;
        float lastPosition = 0;

        float adjustment = 0.1f;
        float power = 0;

        while (globalTimer.seconds() < 10 && opModeIsActive()) {
            currentPosition = Math.abs(motor.getCurrentPosition());

            double derivative = (currentPosition - lastPosition)/timer.seconds();

            lastPosition = currentPosition;

            if (derivative < angularVelocity)
                power = power + adjustment;
            if (derivative > angularVelocity)
                power = power - adjustment;

            motor.setPower(out);

            timer.reset();

            telemetry.addData("speed: ", derivative);
        }
    }
}
