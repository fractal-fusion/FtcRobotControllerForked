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
        double collectspecimenx = tilelength, collectspecimeny = -64;
        double scorespecimeny = -32;
        double specimendegrees = 0;

        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action auto = drive.actionBuilder(initialPose)
                //close claw
                .stopAndAdd(new SequentialAction(
                        intake.closeClaw(),
                        intake.rotateWristToHorizontal(),
                        arm.moveArmToScoreDegrees(),
                        new SleepAction(0.5),
                        arm.primeScoreSpecimenViperslides()
                ))

                //show to human player where all the specimens should be placed
//                .strafeTo(new Vector2d(collectspecimenx, collectspecimeny))
//                .turnTo(Math.toRadians(specimendegrees))
                //prime viperslides for specimen
//                .stopAndAdd(arm.primeScoreSpecimenViperslides())
//                .waitSeconds(2)

                //go to specimen bar and score first specimen
//                // move arm
//                .stopAndAdd(arm.moveArmToScoreDegrees())
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(2,scorespecimeny), Math.toRadians(90))
                .waitSeconds(1)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(1),
                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                //strafe away from the bar
                .strafeTo(new Vector2d(2,-38))

                .stopAndAdd(new SequentialAction(
                        arm.moveArmToPrimeCollectionDegrees()
                ))

                //go back for second specimen
                .turnTo(Math.toRadians(specimendegrees))
                .strafeTo(new Vector2d(collectspecimenx, collectspecimeny))
                .waitSeconds(2)

                .stopAndAdd(new SequentialAction(
                        arm.primeScoreSpecimenViperslides(),
                        new SleepAction(1)
                ))

                //collect second specimen
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSpecimenDegrees(),
                        new SleepAction(1),
                        intake.closeClaw(),
                        new SleepAction(1),
                        arm.moveArmToScoreDegrees()
                ))

                //score second specimen
                .strafeToLinearHeading(new Vector2d(-4 ,scorespecimeny), Math.toRadians(90))
                .waitSeconds(1)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(1),
                        intake.openClaw(),
                        arm.retractViperslides(),
                        arm.moveArmtoRestPosition(),
                        new SleepAction(1.8)
                ))

                //START PUSHING

                //strafe away from the bar
                .strafeTo(new Vector2d(-4,-38))
                //move to colored samples to prepare pushing first sample
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(37,-34))
                .strafeTo(new Vector2d(37, -8))
                .strafeTo(new Vector2d(45,-8))

                //push first sample into zone
                .strafeTo(new Vector2d(48,-50))
//
//                //rise back up to prepare pushing second sample
//                .strafeTo(new Vector2d(47.5, -8))
//                .strafeTo(new Vector2d(55,-8))
//
//                //push second sample while raising arm to prime collection
//                .strafeTo(new Vector2d(60,-50))
//
//                //grab first specimen
//                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
//                //while strafing prime viperslides
//                .waitSeconds(1)
//                //move to collect specimen degrees, close claw
//
//                //score first specimen
//                //move arm to score specimen degrees
//                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
//                //extend viperslides
//                //slide specimen to the right to make room for future specimen
//                .strafeTo(new Vector2d(6,-34))
//                //open claw and prime viperslides
//
//                //grab second specimen
//                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
//                //move arm to collect specimen degrees and prime viperslides
//                //close claw
//
//                //score second specimen
//                //move arm to score specimen degrees
//                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
//                //extend viperslides
//                //slide specimen to the right to make room for future specimen
//                .strafeTo(new Vector2d(6,-34))
//                //open claw and prime viperslides
//
//                //grab third specimen
//                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
//                //move arm to collect specimen degrees and prime viperslides
//                //close claw
//
//                //score third specimen
//                //move arm to score specimen degrees
//                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
//                //extend viperslides, open claw, deextend viperslides, and return to resting position

                //park
                .strafeTo(new Vector2d(46.5, -61))
                .build();

        waitForStart();

        Actions.runBlocking(auto);



    }
}