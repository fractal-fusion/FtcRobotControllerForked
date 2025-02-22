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
        double scorespecimeny = -31.5;

        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action auto = drive.actionBuilder(initialPose)
                //close claw
                .stopAndAdd(new ParallelAction(
                        intake.closeClaw(),
                        intake.rotateWristToHorizontal()
                ))

                .afterTime(0.1, new ParallelAction(
                        arm.moveArmToScoreDegrees(),
                        arm.primeScoreSpecimenViperslidesFromAboveBar()
                ))
                //go to specimen bar and score first specimen
                .strafeToLinearHeading(new Vector2d(-5, scorespecimeny), Math.toRadians(90))
                .waitSeconds(0.3)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
                        arm.scoreSpecimenViperslidesFromAboveBar(),
                        new SleepAction(0.4),
                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                .strafeTo(new Vector2d(0,-43))

                .stopAndAdd(new SequentialAction(
                        arm.retractViperslides(),
                        arm.moveArmtoRestPosition()
                ))
                .build();

        waitForStart();

        Actions.runBlocking(auto);



    }
}