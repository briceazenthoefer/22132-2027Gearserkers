package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp (name = "Basic Drive", group= "Drive")
public class BasicDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Lines 16-19. Left side of the = is what the motor will be referred to as.
        // The right side is the configuration on the driver hub.
        DcMotorEx frontLeft  = hardwareMap.get(DcMotorEx.class,"frontLeft");
        DcMotorEx frontRight  = hardwareMap.get(DcMotorEx.class,"frontRight");
        DcMotorEx backLeft  = hardwareMap.get(DcMotorEx.class,"backLeft");
        DcMotorEx backRight  = hardwareMap.get(DcMotorEx.class,"backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("Basic Drive Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1.0);

            double frontLeftPower = (y + x + turn) / denominator;
            double backLeftPower = (y - x + turn) / denominator;
            double frontRightPower = (y - x - turn) / denominator;
            double backRightPower =  (y + x - turn) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
        }
    }
}