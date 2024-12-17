package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Right Side Auto Macro", group="Robot")
public class autoRightMacroSomeone extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(this);
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        waitForStart();

        //grab sample
        intake.mode(Intake.close);
        sleep(1000);

        //move arm to correct degrees
        arm.moveArm(Arm.scoreDegrees);

        //move to the chamber
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(1);
        drivetrain.backRight.setPower(1);
        sleep(300);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);
        //extend viperslide and score specimen
        arm.moveViperslides(15);
        sleep(1000);
        //release specimen
        intake.mode(Intake.open);
        sleep(200);

        //move backwards out after first score
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(1);
        drivetrain.backRight.setPower(1);
        sleep(100);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);

        //move arm down
        arm.moveArm(Arm.collectionDegrees);
        sleep(1000);
        //strafe right
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(-1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(1);
        sleep(300);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(100);
        //go forward
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(1);
        drivetrain.backRight.setPower(1);
        sleep(300);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);
        //rotate right
        drivetrain.frontLeft.setPower(-1);
        drivetrain.frontRight.setPower(1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(1);
        sleep(888);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(10);


        //strafe right
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(-1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(1);
        sleep(350);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(100);
    }
};