package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;

        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(tilelength, -62, Math.toRadians(90)))
                /*     // close claw to grab sample
                      //move to bucket
                      .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(225))
                      // move arm, extend viperslide and open claw to drop sample, then retract viperslides
                      // and move back arm to collection position
                      //turn to pick up first rightmost sample from drivers perspective
                      .turnTo(Math.toRadians(90))
                      //move to rightmost sample
                      .strafeTo(new Vector2d(-48.2,-36.5))
                      // close claw to grab sample
                      //move to bucket
                      .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(210))
                      // move arm, extend viperslide and open claw to drop sample, then retract viperslides
                      // and move back arm to collection position
                      //turn to pick up the middle sample
                      .turnTo(Math.toRadians(90))
                      //move to middle sample
                      .strafeTo(new Vector2d(-(2*tilelength+11),-37.1))
                      // close claw to grab sample
                      //move to bucket
                      .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(245))
                      // move arm, extend viperslide and open claw to drop sample, then retract viperslides
                      // and move back arm to collection position
                      //turn to pickup leftmost sample
                      .turnTo(Math.toRadians(180))
                      //move to leftmost sample
                      .strafeTo(new Vector2d(-(2*tilelength+11),-27.5))
                      // close claw to grab sample
                      //move to basket
                      .splineTo(new Vector2d(-52.5,-52.5), Math.toRadians(150))
                      // move arm, extend viperslide and open claw to drop sample, then retract viperslides
                      // and move back arm to collection position

                      //park
                      .strafeTo(new Vector2d(-28,0))
                      .turnTo(Math.toRadians(0))
          */
                .strafeTo(new Vector2d(5,-34))
                //  .strafeTo(new Vector2d(-))
                .strafeTo(new Vector2d(37,-34))
                .strafeToLinearHeading(new Vector2d(40, 0), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-50))
                // .lineToXLinearHeading(-10,Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(47.5, 0),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(0))
                .strafeToConstantHeading(new Vector2d(40,-48))
                .strafeTo(new Vector2d(40,-54))
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