package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Drivetrain {
    //declare motors
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;
    private OpMode opMode;

    //constructor which acts as an initialization function for whenever an object of the class is created
    public Drivetrain(OpMode linearopmode)
    {
        this.opMode = linearopmode;
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "frontleft");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "backleft");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "frontright");
        backRight = opMode.hardwareMap.get(DcMotor.class, "backright");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    //main mecanum drive function
    public void drive(Gamepad gamepad)
    {
        //get gamepad inputs
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x * 1.1;
        double rx = gamepad.right_stick_x;

        //speed control with triggers
        double maxSpeed = 0.5;
        double maxSpeedMultiplier;
        maxSpeedMultiplier = maxSpeed + ((-gamepad.right_trigger * (maxSpeed * 0.5)) + (gamepad.left_trigger * maxSpeed));

        //solve for power
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        //multiply calculated power with the speed control to obtain final power for the motors
        frontLeft.setPower(frontLeftPower * maxSpeedMultiplier);
        backLeft.setPower(backLeftPower * maxSpeedMultiplier);
        frontRight.setPower(frontRightPower * maxSpeedMultiplier);
        backRight.setPower(backRightPower * maxSpeedMultiplier);
    }

//unused and unfinished drivetrain methods
//    public void forward() {
//            frontRight.setPower(1);
//            backRight.setPower(1);
//            frontLeft.setPower(1);
//            backLeft.setPower(1);
//        }
//    public void backward(){
//            frontRight.setPower(-1);
//            backRight.setPower(-1);
//            frontLeft.setPower(-1);
//            backLeft.setPower(-1);
//        }
//    public void leftStrafe(int direction) {
//        frontRight.setPower(1);
//        backRight.setPower(-1);
//        frontLeft.setPower(1);
//        backLeft.setPower(-1);
//    }
//    public void rightStrafe(int direction){
//        frontRight.setPower(1);
//        backRight.setPower(-1);
//        frontLeft.setPower(1);
//        backLeft.setPower(-1);
//    }
    }



