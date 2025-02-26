package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Right Side Auto States", group="Robot")
public class autoRightOdometrySpline extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        double tilelength = 24;
        double collectspecimenx = tilelength + 4, collectspecimeny = -70;
        double scorespecimeny = -29.5;

        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action auto = drive.actionBuilder(initialPose)
                //close claw and power wrist
                .stopAndAdd(new ParallelAction(
                        intake.rotatePivotDown(),
                        intake.closeClaw(),
                        intake.rotateWristToHorizontal()
                ))

                //move the arm to the correct position to score the preloaded specimen
                .afterTime(0.1, new ParallelAction(
                        arm.moveArmToScoreSpecimenFromAboveDegrees(),
                        arm.primeScoreSpecimenViperslidesFromAboveBar()
                ))

                //go to specimen bar to score preloaded specimen
                .strafeToLinearHeading(new Vector2d(-8, scorespecimeny+1), Math.toRadians(90))
                .waitSeconds(0.3)

                //raise viperslides to score the preloaded specimen, then open claw
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslidesFromAboveBar(),
                        new SleepAction(0.4),
                        intake.openClaw()
                ))

                //go to front of leftmost sample
                .setTangent(Math.toRadians(0))
                .splineTo(new Vector2d(27.7, -40.7), Math.toRadians(0))
                .afterTime(0.1, new ParallelAction(
                        arm.retractViperslides(),
                        arm.moveArmToPrimeCollectionDegrees()
                ))
                .splineTo(new Vector2d(49.8, -10.3), Math.toRadians(0))

                //start push leftmost sample into observation zone
                .setReversed(true)
                .splineTo(new Vector2d(49.8, -55.5), Math.toRadians(270))
                .setReversed(false)

                //go to front of middle sample
                .setTangent(Math.toRadians(150))
                .splineTo(new Vector2d(57.1, -13.4), Math.toRadians(330))

                //prepare for collection of the first specimen
                .stopAndAdd(new ParallelAction(
                        intake.rotatePivotStraight(),
                        intake.openClaw(),
                        arm.primeScoreSpecimenViperslidesFromBelowBar()
                ))

                //push middle sample into the observation zone
                .setTangent(Math.toRadians(270))
                .splineTo(new Vector2d(57.1, -52.5), Math.toRadians(270))

                .stopAndAdd(new SequentialAction(
                        intake.closeClaw(),
                        new SleepAction(0.4),
                        intake.rotatePivotUpright()
                ))

                .afterTime(0.1, new ParallelAction(
                        arm.moveArmToScoreSpecimenFromUnderDegrees()
                ))

                //score first specimen
//                .setTangent(Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-6,scorespecimeny - 7), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-6, scorespecimeny + 1.5), Math.toRadians(270))

                .waitSeconds(0.3)

                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslidesFromBelowBar(),
                        intake.openClaw(),
                        new SleepAction(0.4)
                ))

                //go to collect second specimen
                .stopAndAdd(new ParallelAction(
                        arm.moveArmToPrimeCollectionDegrees(),
                        arm.primeScoreSpecimenViperslidesFromBelowBar(),
                        intake.rotatePivotStraight()
                ))

                .strafeToLinearHeading(new Vector2d(2*tilelength, -46.5), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(2*tilelength, -52.5), Math.toRadians(270))

                .stopAndAdd(new SequentialAction(
                        intake.closeClaw(),
                        new SleepAction(0.4),
                        intake.rotatePivotUpright()
                ))

                .stopAndAdd(new ParallelAction(
                        arm.moveArmToScoreSpecimenFromUnderDegrees()
                ))

                //score second specimen
                .strafeToLinearHeading(new Vector2d(-9, scorespecimeny - 7), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-9, scorespecimeny+1.5), Math.toRadians(270))

                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslidesFromBelowBar(),
                        intake.openClaw(),
                        new SleepAction(0.4),
                        arm.moveArmToPrimeCollectionDegrees()
                ))

                .build();

        waitForStart();

        Actions.runBlocking(auto);



    }
}