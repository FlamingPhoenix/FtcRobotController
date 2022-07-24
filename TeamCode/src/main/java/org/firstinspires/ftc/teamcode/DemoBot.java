package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class DemoBot extends LinearOpMode { //drives forward 10 inches, strafes 10 inches left, drives back 10 inches, strafes 10 inches right - even if distances are wrong, it should about make a good square
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontRight, frontLeft, backRight, backLeft;
        frontRight = hardwareMap.dcMotor.get("front right");
        frontLeft = hardwareMap.dcMotor.get("front left");
        backRight = hardwareMap.dcMotor.get("back right");
        backLeft = hardwareMap.dcMotor.get("back left");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        int targetValue = Math.round(((1120*20)/(4*((float)Math.PI)))*(float)(Math.pow(2, 0.5)));

        waitForStart();

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (Math.abs(backLeft.getCurrentPosition()) < targetValue) {
            frontRight.setPower(0.5);
            frontLeft.setPower(0.5);
            backRight.setPower(0.5);
            backLeft.setPower(0.5);
            telemetry.addData("current position: ", Math.abs(backLeft.getCurrentPosition()));
            telemetry.addData("percent done: ", ((backLeft.getCurrentPosition()/targetValue)/4));
            telemetry.update();
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (Math.abs(backLeft.getCurrentPosition()) < targetValue) {
            frontRight.setPower(-0.5);
            frontLeft.setPower(0.5);
            backRight.setPower(0.5);
            backLeft.setPower(-0.5);
            telemetry.addData("current position: ", Math.abs(backLeft.getCurrentPosition()));
            telemetry.addData("percent done: ", (0.25+((Math.abs(backLeft.getCurrentPosition())/targetValue)/4)));
            telemetry.update();
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (Math.abs(backLeft.getCurrentPosition()) < targetValue) {
            frontRight.setPower(-0.5);
            frontLeft.setPower(-0.5);
            backRight.setPower(-0.5);
            backLeft.setPower(-0.5);
            telemetry.addData("current position: ", Math.abs(backLeft.getCurrentPosition()));
            telemetry.addData("percent done: ", (0.5+((Math.abs(backLeft.getCurrentPosition())/targetValue)/4)));
            telemetry.update();
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (Math.abs(backLeft.getCurrentPosition()) < targetValue) {
            frontRight.setPower(0.5);
            frontLeft.setPower(-0.5);
            backRight.setPower(-0.5);
            backLeft.setPower(0.5);
            telemetry.addData("current position: ", Math.abs(backLeft.getCurrentPosition()));
            telemetry.addData("percent done: ", (0.75+((Math.abs(backLeft.getCurrentPosition())/targetValue)/4)));
            telemetry.update();
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
