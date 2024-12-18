package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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


        //pre-build trajectories during initialization
//        Action gotoBucket = drive.actionBuilder(initialPose)
//                .strafeToLinearHeading(new Vector2d(-52.5,-52.5), Math.toRadians(225))
//                .build()

        Action moveTest = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(30,-50))
                .strafeTo(new Vector2d(5,-34))
                .strafeTo(new Vector2d(37,-34))
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-50))
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
       /* Actions.runBlocking(
                new SequentialAction(
                        arm.moveArmToCollectionDegrees(),
                        intake.closeClaw()
                ));
                */
       waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        moveTest,
                        arm.moveArmToBucketDegrees(),
                        //arm.extendViperslides(),
                        intake.openClaw(),
                       //
                        // arm.retractViperslides(),
                        arm.moveArmToCollectionDegrees()
                ));

    }
}