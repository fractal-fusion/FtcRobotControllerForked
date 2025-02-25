package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Test Auto", group="Robot")
public class autoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        double tilelength = 24;
        Intake intake = new Intake(this);
        Arm arm = new Arm(this);

        //initialize beginning pose and mecanum drive object
        Pose2d initialPose = new Pose2d(tilelength, -62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action fullActionTest = drive.actionBuilder(initialPose)
                .stopAndAdd(
                new SequentialAction(
                        arm.moveArmToScoreDegrees(),
                        arm.extendViperslides(),
                        arm.retractViperslides(),
                        intake.openClaw(),
                        new SleepAction(0.3),
                        arm.moveArmToPrimeCollectionDegrees(),
                        intake.closeClaw(),
                        new SleepAction(0.3),
                        arm.moveArmToScoreSpecimenDegrees(),
                        new SleepAction(0.3)
                ))
                .build();

        //pickup a sample
        /*Actions.runBlocking(
                new SequentialAction(
                        arm.moveArmToCollectionDegrees(),
                        intake.closeClaw()
                ));
        */
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        fullActionTest
                )

        );



    }
}