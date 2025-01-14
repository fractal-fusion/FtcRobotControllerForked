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
        double specimenx = 26, specimeny = -48.1;
        int specimendegrees = 337;

        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(0, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action auto = drive.actionBuilder(initialPose)
                //close claw
                .stopAndAdd(intake.closeClaw())

                //show to human player where all the specimens should be placed
                .splineTo(new Vector2d(specimenx, specimeny), Math.toRadians(specimendegrees))
                //prime viperslides for specimen
                .stopAndAdd(arm.primeScoreSpecimenViperslides())
                .waitSeconds(2)

                //go to specimen bar and score first specimen
                // move arm
                .stopAndAdd(arm.moveArmToScoreDegrees())
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                .waitSeconds(1)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.3),
                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                //strafe away from the bar
                .strafeTo(new Vector2d(2,-38))

                .stopAndAdd(arm.primeScoreSpecimenViperslides())

                //go back for second specimen
                .turnTo(Math.toRadians(specimendegrees))
                .strafeTo(new Vector2d(specimenx, specimeny))
                .waitSeconds(2)

                //collect second specimen
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToCollectSpecimenDegrees(),
                        new SleepAction(0.5),
                        intake.closeClaw(),
                        new SleepAction(0.28),
                        arm.moveArmToScoreDegrees()
                ))

                //score second specimen
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                .waitSeconds(1)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.3),
                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                //START PUSHING

//                //strafe away from the bar
//                .strafeTo(new Vector2d(2,-38))
//                //move to colored samples to prepare pushing first sample
//                .turnTo(Math.toRadians(0))
//                .strafeTo(new Vector2d(37,-34))
//                .strafeTo(new Vector2d(37, -8))
//                .strafeTo(new Vector2d(45,-8))
//
//                //push first sample into zone
//                .strafeTo(new Vector2d(48,-50))
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