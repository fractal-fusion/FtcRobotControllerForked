package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
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

                //move to bucket
                .splineTo(new Vector2d(-51.5,-51.5), Math.toRadians(225))
                .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket, then deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToBucketDegrees(),
                        arm.extendViperslides(),
                        new SleepAction(1),
                        intake.openClaw(),
                        arm.primeCollectSampleViperslides()
                ))

                //move to rightmost sample
                .turnTo(Math.toRadians(90))
                .strafeTo(new Vector2d(-48.1, -45.0))
                .waitSeconds(1)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSampleDegrees(),
                        new SleepAction(2),
                        intake.closeClaw(),
                        arm.moveArmToBucketDegrees(),
                        new SleepAction(1)
                ))

                //rotate viperslides and move to bucket
                .splineTo(new Vector2d(-51.5,-51.5), Math.toRadians(225))
                .waitSeconds(1)
                //extend viperslides and drop sample in bucket
                //dextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        arm.extendViperslides(),
                        intake.openClaw(),
                        new SleepAction(1),
                        arm.primeCollectSampleViperslides()
                ))

                //move to middle sample
                .turnTo(Math.toRadians(90))
                .strafeTo(new Vector2d(-58.3, -45.0))
                .waitSeconds(1)
                //rotate arm and close claw
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSampleDegrees(),
                        intake.closeClaw(),
                        new SleepAction(2)
                ))

                //move to bucket
                .splineTo(new Vector2d(-51.5,-51.5), Math.toRadians(225))
                .afterTime(1, arm.moveArmToBucketDegrees())
                .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 15 inches
                .stopAndAdd(new SequentialAction(
                        //arm.moveArmToBucketDegrees(),
                        arm.extendViperslides(),
                        intake.openClaw(),
                        arm.primeCollectSampleViperslides()
                ))

                //move to leftmost sample
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-44.6, -25.4))
                .waitSeconds(1)
                //rotate arm and wrist, and close claw
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSampleDegrees(),
                        intake.rotateWristToVertical(),
                        intake.closeClaw()
                ))

                //move to bucket
                .splineTo(new Vector2d(-51.5,-51.5), Math.toRadians(225))
                .afterTime(1, arm.moveArmToBucketDegrees())
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 0 inches and rotate arm to 0 degrees
                .stopAndAdd(new SequentialAction(
                        //arm.moveArmToBucketDegrees(),
                        arm.extendViperslides(),
                        intake.openClaw(),
                        arm.moveArmtoRestPosition()
                ))
                .build();

       waitForStart();

       Actions.runBlocking(auto);

    }
}