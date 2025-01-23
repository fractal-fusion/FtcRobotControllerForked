package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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
        double collectspecimenx = tilelength + 4, collectspecimeny = -70;
        double scorespecimeny = -31.5;

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
                        new SleepAction(0.35),
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
                .strafeToLinearHeading(new Vector2d(-1, scorespecimeny), Math.toRadians(90))
                .waitSeconds(0.3)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.4),
                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                //strafe away from the bar
                .strafeTo(new Vector2d(0,-43))

                .stopAndAdd(new SequentialAction(
                        arm.retractViperslides(),
                        arm.moveArmtoRestPosition()
                ))

                //spline to pushing
                .setTangent(Math.toRadians(90))
                .splineTo(new Vector2d(38.2, -27.6), Math.toRadians(80))
                .splineTo(new Vector2d(47.2, -13.2), Math.toRadians(0))

                //push first sample into zone
                .strafeTo(new Vector2d(48,-58.5))
                //back out
                .strafeTo(new Vector2d(48,-45))
                .waitSeconds(0.3)

                //go for second specimen
                .strafeTo(new Vector2d(collectspecimenx, collectspecimeny))
                //intake second specimen
                .stopAndAdd(new SequentialAction(
                        arm.moveArmToPrimeCollectionDegrees(),
                        arm.slowPrimeScoreSpecimenViperslides(),
                        arm.moveArmToCollectSpecimenDegrees(),
                        new SleepAction(0.5),
                        intake.closeClaw(),
                        new SleepAction(0.3),
                        arm.moveArmToScoreDegrees()
                ))

                //score second specimen
                .strafeToLinearHeading(new Vector2d(-3 ,scorespecimeny + 0.2), Math.toRadians(90))
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.38),
                        intake.openClaw(),
//                        new SleepAction(0.3),
                        arm.retractViperslides(),
                        arm.moveArmToPrimeCollectionDegrees()
                ))

                //go for third specimen
                .strafeToLinearHeading(new Vector2d(collectspecimenx + 4, collectspecimeny - 0.5), Math.toRadians(0))
                //intake third specimen
                .stopAndAdd(new SequentialAction(
                        arm.slowPrimeScoreSpecimenViperslides(),
                        arm.moveArmToCollectSpecimenDegrees(),
                        new SleepAction(0.5),
                        intake.closeClaw(),
                        new SleepAction(0.3),
                        arm.moveArmToScoreDegrees()
                ))

                //score third specimen
                .strafeToLinearHeading(new Vector2d(-5 ,scorespecimeny + 1.4), Math.toRadians(90))
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.38),
                        intake.openClaw(),
//                        new SleepAction(0.3),
                        arm.retractViperslides(),
                        arm.moveArmtoRestPosition()
                ))

                //park
                .strafeTo(new Vector2d(55.0, -55.3))
                .build();

        waitForStart();

        Actions.runBlocking(auto);



    }
}