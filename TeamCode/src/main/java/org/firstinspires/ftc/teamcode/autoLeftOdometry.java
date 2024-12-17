package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Left Side Auto", group="Robot")
public class autoLeftOdometry extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        double tilelength = 24;
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(-tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        //pre-build trajectories during initialization
        Action gotoBucket = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                .build();

        Action gotoRightSample = drive.actionBuilder(initialPose)
                //face sample
                .turnTo(Math.toRadians(90))
                //move to rightmost sample
                .strafeTo(new Vector2d(-48.2,-36.5))
                .build();

        //pickup a sample
        Actions.runBlocking(
                new SequentialAction(
                        arm.moveArmToCollectionDegrees(),
                        intake.closeClaw()
                ));

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        gotoBucket,
                        arm.extendViperslides(),
                        intake.openClaw()
                ));

    }
}