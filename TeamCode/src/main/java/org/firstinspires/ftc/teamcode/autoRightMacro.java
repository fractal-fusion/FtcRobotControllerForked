package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Right Side Specimen Auto Macro", group="Robot")
public class autoRightMacro extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(this);
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        waitForStart();

        //grab sample
        intake.mode(Intake.close);
        sleep(1500);
        arm.moveArm(Arm.collectionDegrees);
        sleep(1000);

        //move arm to correct degrees
        arm.moveArm(Arm.scoreDegrees);
        sleep(1000);
        arm.moveViperslides(10);
        sleep(1000);

        //move to the chamber
        drivetrain.frontLeft.setPower(0.2);
        drivetrain.frontRight.setPower(0.2);
        drivetrain.backLeft.setPower(0.2);
        drivetrain.backRight.setPower(0.2);
        sleep(4000);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(1000);

        //extend viperslide and score specimen
        arm.moveViperslides(15);
        sleep(1000);
        //release specimen
        intake.mode(Intake.open);
        sleep(200);

        //park in the scoring zone
        //move backwards out
        drivetrain.frontLeft.setPower(-1);
        drivetrain.frontRight.setPower(-1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(-1);
        sleep(250);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(20);

        //move arm down
        arm.moveViperslides(0);
        sleep(1000);
        arm.moveArm(Arm.collectionDegrees);
        sleep(1000);

        //strafe right
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(-1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(1);
        sleep(800);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(100);
    }
};