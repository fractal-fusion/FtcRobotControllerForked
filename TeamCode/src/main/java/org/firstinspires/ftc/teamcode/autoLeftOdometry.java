package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

        Action auto = drive.actionBuilder(initialPose)
                // close claw to grab sample
                .stopAndAdd(new SequentialAction(
                        intake.closeClaw(),
                        intake.rotateWristToHorizontal()
                ))

//                .afterTime(0.1, new ParallelAction(
//                        arm.moveArmToScoreDegrees(),
//                        arm.extendViperslides()
//                ))

                //initial move to bucket
                .splineTo(new Vector2d(-47,-47), Math.toRadians(230))

//                .waitSeconds(0.25)
                
                //extend viperslides and move arm
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToScoreDegrees(),
                        arm.extendViperslides()
                ))

                //strafe so claw is above the bucket
                .strafeTo(new Vector2d(-51.5,-52))

                //rotate arm extend viperslides and drop sample into bucket, then deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
//                        arm.moveArmToScoreDegrees(),
//                        arm.extendViperslides(),
//                        new SleepAction(1),
                        new SleepAction(0.28),
                        intake.openClaw()
//                        arm.primeCollectionViperslides()
                ))

                //strafe back out so claw is not over the bucket
                .strafeTo(new Vector2d(-47,-47))

                //deextend viperslide
                .stopAndAdd(arm.primeCollectionViperslides())

                //move to rightmost sample
                .turnTo(Math.toRadians(90))
                .waitSeconds(0.3)
                .afterTime(0.3, arm.moveArmToCollectSampleDegrees())
                .strafeTo(new Vector2d(-49.5, -45.5))
                .waitSeconds(1.25)
//                .waitSeconds(0.25)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
//                        arm.moveArmToCollectSampleDegrees(),
                        intake.closeClaw(),
                        new SleepAction(0.35),
                        arm.moveArmToScoreDegrees()
//                        new SleepAction(0.5)
                ))
                //rotate viperslides and move to bucket
                .turnTo(Math.toRadians(225))
//                .strafeTo(new Vector2d(-50,-50))
//                .waitSeconds(0.25)

                //extend viperslides
                .stopAndAdd(new SequentialAction(
                        arm.extendViperslides()
                ))

                //strafe so claw is above the bucket
                .strafeTo(new Vector2d(-50.5,-52))

                //extend viperslides and drop sample in bucket
                //dextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
//                        arm.extendViperslides(),
                        new SleepAction(0.28),
                        intake.openClaw()
//                        arm.primeCollectionViperslides()
                ))

                //strafe back out so claw is not over the bucket
                .strafeTo(new Vector2d(-47,-47))

                //deextend viperslide
                .stopAndAdd(arm.primeCollectionViperslides())

                //move to middle sample
                .turnTo(Math.toRadians(90))
                .afterTime(0.6, arm.moveArmToCollectSampleDegrees())
                .strafeTo(new Vector2d(-61.8, -44.5))
//                .waitSeconds(0.25)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
//                        arm.moveArmToCollectSampleDegrees(),
                        new SleepAction(0.65),
                        intake.closeClaw(),
                        new SleepAction(0.28),
                        arm.moveArmToScoreDegrees()
//                        new SleepAction(0.5)
                ))

                //move to bucket
                .turnTo(Math.toRadians(225))
//                .strafeTo(new Vector2d(-47,-47))
//                .waitSeconds(0.25)

                //extend viperslides
                .stopAndAdd(new SequentialAction(
                        arm.extendViperslides()
                ))

                //strafe so claw is above the bucket
                .strafeTo(new Vector2d(-50.5,-52))

                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
//                        arm.extendViperslides(),
                        new SleepAction(0.28),
                        intake.openClaw()
//                        arm.primeCollectionViperslides()
                ))

                //strafe back out so claw is not over the bucket
//                .strafeTo(new Vector2d(-47,-47))

                //deextend viperslide
//                .stopAndAdd(arm.primeCollectionViperslides())

                //move to leftmost sample
                .turnTo(Math.toRadians(90))
//                .strafeTo(new Vector2d(-44.6, -25.4))
//                .waitSeconds(0.25)
                //rotate arm and wrist, and close claw
//                .stopAndAdd(new SequentialAction(
//                        intake.rotateWristToVertical(),
//                        arm.moveArmToCollectSampleDegrees(),
//                        new SleepAction(0.3),
//                        intake.closeClaw(),
//                        new SleepAction(0.28),
//                        intake.rotateWristToHorizontal()
////                        new SleepAction(0.2),
////                        arm.moveArmToScoreDegrees(),
////                        new SleepAction(1)
//                ))

//                //move to bucket
//                .turnTo(Math.toRadians(225))
//                .strafeTo(new Vector2d(-50,-50))
//
//                //extend viperslides
//                .stopAndAdd(new SequentialAction(
//                        arm.extendViperslides()
//                ))

//                //strafe so claw is above the bucket
//                .strafeTo(new Vector2d(-51.5,-51.5))
//
//                //rotate arm extend viperslides and drop sample into bucket
//                //deextend viperslides to 0 inches and rotate arm to 0 degrees
//                .stopAndAdd(new SequentialAction(
//                        //arm.moveArmToBucketDegrees(),
////                        arm.extendViperslides(),
//                        intake.openClaw(),
//                        new SleepAction(0.2)
//
////                        arm.retractViperslides(),
////                        arm.moveArmtoRestPosition(),
////                        new SleepAction(1.8)
//                ))
//
//                //strafe back out so claw is not over the bucket
//                .strafeTo(new Vector2d(-49,-49))

                //deextend and move back to resting
                .afterTime(0.1, arm.retractViperslides())
                .afterTime(0.5, arm.moveArmtoRestPosition())

                .build();

       waitForStart();

       Actions.runBlocking(auto);

    }
}