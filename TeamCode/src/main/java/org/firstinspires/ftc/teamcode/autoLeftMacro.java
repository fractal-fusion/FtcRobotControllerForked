package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Left Side Sample Auto Macro", group="Robot")
public class autoLeftMacro extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(this);
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        waitForStart();

        //grab sample
        intake.mode(Intake.close);
        sleep(100);
        arm.moveArmMacro(Arm.collectionDegrees);
        sleep(1000);

        //move to the bucket (tested)
        //forward
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(1);
        drivetrain.backRight.setPower(1);
        sleep(135);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);
        //strafe left
        drivetrain.frontLeft.setPower(-1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(1);
        drivetrain.backRight.setPower(-1);
        sleep(400);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(100);
        //rotate left
        drivetrain.frontLeft.setPower(-0.8);
        drivetrain.frontRight.setPower(0.8);
        drivetrain.backLeft.setPower(-0.8);
        drivetrain.backRight.setPower(0.8);
        sleep(555);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);
        //move forward
        drivetrain.frontLeft.setPower(0.5);
        drivetrain.frontRight.setPower(0.5);
        drivetrain.backLeft.setPower(0.5);
        drivetrain.backRight.setPower(0.5);
        sleep(70);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        //rotate viper slides
        arm.moveArmMacro(Arm.scoreDegrees);
        sleep(2000);
        //extend slides
        arm.moveViperslides(35);
        sleep(3000);
        intake.mode(Intake.open);
        sleep(1000);
        //move backward away from bucket
        drivetrain.frontLeft.setPower(-0.5);
        drivetrain.frontRight.setPower(-0.5);
        drivetrain.backLeft.setPower(-0.5);
        drivetrain.backRight.setPower(-0.5);
        sleep(40);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        //de-extend slides
        arm.moveViperslides(0);
        sleep(5000);
        //rotate right
        drivetrain.frontLeft.setPower(0.8);
        drivetrain.frontRight.setPower(-0.8);
        drivetrain.backLeft.setPower(0.8);
        drivetrain.backRight.setPower(-0.8);
        sleep(555);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(20);
        drivetrain.frontLeft.setPower(0.8);
        drivetrain.frontRight.setPower(-0.8);
        drivetrain.backLeft.setPower(0.8);
        drivetrain.backRight.setPower(-0.8);
        sleep(100);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(30);
        arm.moveArmMacro(Arm.collectionDegrees);
        sleep(5000);
//        //go forward towards sample
//        drivetrain.frontLeft.setPower(1);
//        drivetrain.frontRight.setPower(1);
//        drivetrain.backLeft.setPower(1);
//        drivetrain.backRight.setPower(1);
//        sleep(200);
//        drivetrain.frontLeft.setPower(0);
//        drivetrain.frontRight.setPower(0);
//        drivetrain.backLeft.setPower(0);
//        drivetrain.backRight.setPower(0);
//        sleep(20);
//        //untested drive to submersible
//        arm.moveArm(Arm.collectionDegrees);
//        sleep(2000);
//        drivetrain.frontLeft.setPower(-0.8);
//        drivetrain.frontRight.setPower(0.8);
//        drivetrain.backLeft.setPower(-0.8);
//        drivetrain.backRight.setPower(0.8);
//        sleep(200);
//        drivetrain.frontLeft.setPower(0);
//        drivetrain.frontRight.setPower(0);
//        drivetrain.backLeft.setPower(0);
//        drivetrain.backRight.setPower(0);
//        sleep(10);









    }
};