package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.PI, Math.PI, 13.465)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -62, Math.toRadians(90)))
                // close claw to grab sample, move arm and prime viperslides

                //go to specimen bar and score first specimen
                .strafeTo(new Vector2d(2,-34))
                .waitSeconds(1)
                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down

                //move to colored samples to prepare pushing
                .strafeTo(new Vector2d(37,-34))
                //start pushing
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,0))
                .strafeTo(new Vector2d(48,-50))
                .strafeToLinearHeading(new Vector2d(47.5, 0),Math.toRadians(0))
                .strafeTo(new Vector2d(55,0))
                .strafeToLinearHeading(new Vector2d(60,-50),Math.toRadians(0))
                //finish pushing
                .strafeToConstantHeading(new Vector2d(40,-48))
                .strafeToLinearHeading(new Vector2d(40,-54),Math.toRadians(270))
                .splineTo(new Vector2d(4,-34),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(34,-60),Math.toRadians(-45))
                .strafeToLinearHeading(new Vector2d(6,-34), Math.toRadians(90))
                .strafeTo(new Vector2d(36,-60))
                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}