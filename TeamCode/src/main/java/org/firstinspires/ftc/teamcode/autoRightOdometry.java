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

        //pre-build trajectories during initialization
        Action gotoBucket = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                .build();
        Action collectColors = drive.actionBuilder(initialPose)
                //  .strafeTo(new Vector2d(-))
                .strafeTo(new Vector2d(37,-34))
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-50))
                // .lineToXLinearHeading(-10,Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(47.5, 0),Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(40,-48))
                .build();
        Action grab2ndSpec = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(40,-54))
                .build();
        Action hangInitialSpec = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(5,-34))
                .build();
        Action hangSecondSpec = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(4,-34),Math.toRadians(90))
                .build();
        Action collect3rdSpec = drive.actionBuilder(initialPose)
                .strafeToLinearHeading(new Vector2d(34,-60),Math.toRadians(-45))
                .build();
        Action hang3rdSpec = drive.actionBuilder(initialPose)
                .strafeToLinearHeading(new Vector2d(6,-34), Math.toRadians(90))
                .build();
        Action park=drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(36,-60))
                .build();
        Action gotoRightSample = drive.actionBuilder(initialPose)
                //face sample
                .turnTo(Math.toRadians(90))
                //move to rightmost sample
                .strafeTo(new Vector2d(-48.2,-36.5))
                .build();
        Action fullMovementTest = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(5,-34))
                //  .strafeTo(new Vector2d(-))
                .strafeTo(new Vector2d(37,-34))
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-50))
                // .lineToXLinearHeading(-10,Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(47.5, 0),Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(40,-48))
                .strafeTo(new Vector2d(40,-54))
                .splineTo(new Vector2d(4,-34),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(34,-60),Math.toRadians(-45))
                .strafeToLinearHeading(new Vector2d(6,-34), Math.toRadians(90))
                .strafeTo(new Vector2d(36,-60))
                .build();

        //pickup a sample
        Actions.runBlocking(
                new SequentialAction(
                        arm.moveArmToCollectionDegrees(),
                        intake.closeClaw()
                ));

        waitForStart();

        Actions.runBlocking(
               /* new SequentialAction(
                        hangInitialSpec,
                        arm.moveArmToBucketDegrees(),
                        arm.moveArmToCollectionDegrees(),
                        collectColors,
                        new SleepAction(1),
                        intake.openClaw(),
                        grab2ndSpec,
                        intake.openClaw(),
                        hangSecondSpec,
                        //fullMovementTest
                        arm.moveArmToBucketDegrees(),
                        arm.scoreSpecimenViperslides(),
                        intake.openClaw(),
                        arm.retractViperslides(),
                        arm.moveArmToCollectSpecimenDegrees(),
                        collect3rdSpec,

                        intake.closeClaw(),
                        new ParallelAction(
                                hang3rdSpec,
                                arm.moveArmToBucketDegrees()
                        ),
                        arm.scoreSpecimenViperslides(),
                        intake.openClaw(),
                        arm.retractViperslides(),
                        arm.moveArmToCollectSpecimenDegrees(),
                        park
                );
                */
                new SequentialAction(
                        fullMovementTest
                )

        );

    }
}