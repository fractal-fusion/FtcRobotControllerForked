package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
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
                .stopAndAdd(intake.closeClaw())

                //initial move to bucket
                .splineTo(new Vector2d(-50.6,-50.6), Math.toRadians(225))
                //.afterTime(0.1, arm.moveArmToScoreDegrees())
                .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket, then deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToScoreDegrees(),
                        arm.extendViperslides(),
                        new SleepAction(1),
                        intake.openClaw(),
                        arm.primeCollectionViperslides()
                ))

                //move to rightmost sample
                .turnTo(Math.toRadians(90))
                .strafeTo(new Vector2d(-48.4, -46.0))
                .waitSeconds(1)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSampleDegrees(),
                        new SleepAction(1.8),
                        intake.closeClaw(),
                        new SleepAction(0.5),
                        arm.moveArmToScoreDegrees(),
                        new SleepAction(1.8)
                ))

                //rotate viperslides and move to bucket
                .turnTo(Math.toRadians(225))
                .strafeTo(new Vector2d(-50.6,-50.6))
                .waitSeconds(1)
                //extend viperslides and drop sample in bucket
                //dextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        arm.extendViperslides(),
                        intake.openClaw(),
                        new SleepAction(1),
                        arm.primeCollectionViperslides()
                ))

                //move to middle sample
                .turnTo(Math.toRadians(90))
                .strafeTo(new Vector2d(-59, -46.0))
                .waitSeconds(1)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSampleDegrees(),
                        new SleepAction(1.8),
                        intake.closeClaw(),
                        new SleepAction(0.5),
                        arm.moveArmToScoreDegrees(),
                        new SleepAction(1.8)
                ))

                //move to bucket
                .turnTo(Math.toRadians(225))
                .strafeTo(new Vector2d(-50.6,-50.6))
                .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        //arm.moveArmToBucketDegrees(),
                        arm.extendViperslides(),
                        intake.openClaw(),
                        new SleepAction(1),
                        arm.primeCollectionViperslides()
                ))

                //move to leftmost sample
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-44.6, -24.4))
                .waitSeconds(1)
                //rotate arm and wrist, and close claw
                .stopAndAdd(new SequentialAction(
                        intake.rotateWristToVertical(),
                        arm.moveArmToCollectSampleDegrees(),
                        new SleepAction(1.8),
                        intake.closeClaw(),
                        intake.rotateWristToHorizontal(),
                        new SleepAction(0.2),
                        arm.moveArmToScoreDegrees(),
                        new SleepAction(1.8)
                ))

                //move to bucket
                .turnTo(Math.toRadians(225))
                .strafeTo(new Vector2d(-50.6,-50.6))
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 0 inches and rotate arm to 0 degrees
                .stopAndAdd(new SequentialAction(
                        //arm.moveArmToBucketDegrees(),
                        arm.extendViperslides(),
                        intake.openClaw(),
                        new SleepAction(0.2),
                        arm.retractViperslides(),
                        arm.moveArmtoRestPosition(),
                        new SleepAction(1.8)
                ))
                .build();

       waitForStart();

       Actions.runBlocking(auto);

    }
}