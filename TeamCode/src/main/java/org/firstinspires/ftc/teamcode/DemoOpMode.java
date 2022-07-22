package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class DemoOpMode extends OpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft;

    public void drive (float x1, float y1, float x2) {
        float fl = Range.clip(y1 + x1 + x2, -1, 1);
        float fr = Range.clip(y1 - x1 - x2, -1, 1);
        float bl = Range.clip(y1 - x1 + x2, -1, 1);
        float br = Range.clip(y1 + x1 - x2, -1, 1);

        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get("front right");
        frontLeft = hardwareMap.dcMotor.get("front left");
        backRight = hardwareMap.dcMotor.get("back right");
        backLeft = hardwareMap.dcMotor.get("back left");
    }

    @Override
    public void loop() {
        drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
