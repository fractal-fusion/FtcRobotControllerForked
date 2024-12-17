package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Park Auto Macro", group="Robot")
public class parkMacro extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(this);
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        waitForStart();

        //grab sample
        intake.mode(Intake.close);
        sleep(100);
        arm.moveArm(Arm.collectionDegrees);
        sleep(1000);

        //park
        //strafe right
        drivetrain.frontLeft.setPower(1);
        drivetrain.frontRight.setPower(-1);
        drivetrain.backLeft.setPower(-1);
        drivetrain.backRight.setPower(1);
        sleep(700);
        drivetrain.frontLeft.setPower(0);
        drivetrain.frontRight.setPower(0);
        drivetrain.backLeft.setPower(0);
        drivetrain.backRight.setPower(0);
        sleep(100);
    }
};