package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;
        double specimenx = 26, specimeny = -48.1;
        int specimendegrees = 337;

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.PI, Math.PI, 13.465)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -62, Math.toRadians(90)))
                //show to human player where all the specimens should be placed
                .splineTo(new Vector2d(specimenx, specimeny), Math.toRadians(specimendegrees))
                //prime viperslides for specimen
                .waitSeconds(2)

                //go to specimen bar and score first specimen
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                // move arm while strafing
                .waitSeconds(1)
                //strafe away from the bar
                .strafeTo(new Vector2d(2,-38))
                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down

                //move to colored samples to prepare pushing first sample
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(37,-34))
                .strafeTo(new Vector2d(37, -8))
                .strafeTo(new Vector2d(45,-8))

                //push first sample into zone
                .strafeTo(new Vector2d(48,-50))

                //rise back up to prepare pushing second sample
                .strafeTo(new Vector2d(47.5, -8))
                .strafeTo(new Vector2d(55,-8))

                //push second sample while raising arm to prime collection
                .strafeTo(new Vector2d(60,-50))

                //grab first specimen
                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
                //while strafing prime viperslides
                .waitSeconds(1)
                //move to collect specimen degrees, close claw

                //score first specimen
                //move arm to score specimen degrees
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                //extend viperslides and open claw

                //grab second specimen
                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
                //move arm to collect specimen degrees and prime viperslides
                //close claw

                //move arm to score specimen degrees
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                //extend viperslides and open claw

                //grab third specimen
                .strafeToLinearHeading(new Vector2d(specimenx,specimeny), Math.toRadians(specimendegrees))
                //move arm to collect specimen degrees and prime viperslides
                //close claw

                //score third specimen
                //move arm to score specimen degrees
                .strafeToLinearHeading(new Vector2d(2,-34), Math.toRadians(90))
                //extend viperslides and open claw

//                .strafeToConstantHeading(new Vector2d(40,-48))
//                .strafeToLinearHeading(new Vector2d(40,-54),Math.toRadians(270))
//                .splineTo(new Vector2d(4,-34),Math.toRadians(90))
//                .strafeToLinearHeading(new Vector2d(34,-60),Math.toRadians(-45))
//                .strafeToLinearHeading(new Vector2d(6,-34), Math.toRadians(90))
//                .strafeTo(new Vector2d(36,-60))
                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}