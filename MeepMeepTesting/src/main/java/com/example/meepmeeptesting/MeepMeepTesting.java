package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.PI, Math.PI, 13.465)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-tilelength, -62, Math.toRadians(90)))
                // close claw to grab sample

                //move to bucket
                    .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                    .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 15 inches

                //move to rightmost sample
                    .turnTo(Math.toRadians(90))
                    .strafeTo(new Vector2d(-48.1, -45.0))
                //rotate arm and close claw
                    .waitSeconds(1)

                //rotate viperslides and move to bucket
                    .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                //extend viperslides and drop sample in bucket
                //dextend viperslides to 15 inches
                    .waitSeconds(1)

                //move to middle sample
                    .turnTo(Math.toRadians(90))
                    .strafeTo(new Vector2d(-58.3, -45.0))
                //rotate arm and close claw
                    .waitSeconds(1)

                //move to bucket
                    .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                    .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 15 inches

                //move to leftmost sample
                    .turnTo(Math.toRadians(180))
                    .strafeTo(new Vector2d(-44.6, -25.4))
                //rotate arm and wrist, and close claw
                    .waitSeconds(1)

                //move to bucket
                    .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                    .waitSeconds(1)
                //rotate arm extend viperslides and drop sample into bucket
                //deextend viperslides to 0 inches and rotate arm to 0 degrees

                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}