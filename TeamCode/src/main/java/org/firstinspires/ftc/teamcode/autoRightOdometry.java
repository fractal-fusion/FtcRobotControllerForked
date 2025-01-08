package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Right Side Auto", group="Robot")
public class autoRightOdometry extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        double tilelength = 24;
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action fullMovementTest = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(5,-34))
                .strafeTo(new Vector2d(37,-34))
                //start pushing
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,0))
                .strafeTo(new Vector2d(48,-50))
                .strafeToLinearHeading(new Vector2d(47.5, 0),Math.toRadians(0))
                .strafeTo(new Vector2d(55,0))
                .strafeToLinearHeading(new Vector2d(60,-50),Math.toRadians(0))
                //finish pushing
                .strafeToConstantHeading(new Vector2d(40,-48))
                .strafeToLinearHeading(new Vector2d(40,-54),Math.toRadians(270))
                .splineTo(new Vector2d(4,-34),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(34,-60),Math.toRadians(-45))
                .strafeToLinearHeading(new Vector2d(6,-34), Math.toRadians(90))
                .strafeTo(new Vector2d(36,-60))
                .build();

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        fullMovementTest
                )

        );



    }
}