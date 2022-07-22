package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="velocity control", group="none")
public class VelocityControl extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor;
        motor = hardwareMap.dcMotor.get("motor");
        telemetry.addData("Ready for start", 0);
        telemetry.update();
        waitForStart();
        motor.setPower(1);
//        float PPR = 1120f;
        float angularVelocity = 4500;//counts/second - max is 4750

        ElapsedTime timer = new ElapsedTime();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        float currentPosition = 0;
        float lastPosition = 0;

        float adjustment = 0.01f;
        float power = 0;
        timer.reset();

        while (timer.seconds()<5) {
            currentPosition = Math.abs(motor.getCurrentPosition());

            float derivative = (float) ((currentPosition - lastPosition)/timer.seconds());

            lastPosition = currentPosition;

            if (derivative < angularVelocity)
                power = power + adjustment;
            if (derivative > angularVelocity)
                power = power - adjustment;

            if (power > 1)
                power = 0.95f;//safety net

            motor.setPower(power);

            if (gamepad1.a) {
                angularVelocity = angularVelocity + 1;
            } else if (gamepad1.b) {
                angularVelocity -= 1;
            }

            timer.reset();
            telemetry.addData("speed: ", derivative);
            telemetry.addData("power", power);
            telemetry.addData("max angular velocity: ", angularVelocity);
            telemetry.update();
        }
        motor.setPower(0);
    }
}
