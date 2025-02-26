package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;
        double collectspecimenx = tilelength + 2, collectspecimeny = -62;
        double scorespecimeny = -32;
        double specimendegrees = 0;

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 70, Math.PI, Math.PI, 13.465)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -62, Math.toRadians(90)))

                .strafeToLinearHeading(new Vector2d(2, scorespecimeny), Math.toRadians(90))
                .waitSeconds(0.3)

                //go to front of leftmost sample
                .setTangent(Math.toRadians(0))
                .splineTo(new Vector2d(27.7, -40.7), Math.toRadians(0))
                .splineTo(new Vector2d(49.8, -10.3), Math.toRadians(0))

                //start push leftmost sample into observation zone
                .setReversed(true)
                .splineTo(new Vector2d(49.8, -55.5), Math.toRadians(270))
                .setReversed(false)

                //go to front of middle sample
                .setTangent(Math.toRadians(150))
                .splineTo(new Vector2d(57.1, -13.4), Math.toRadians(330))

                //push middle sample into the observation zone
                .setTangent(Math.toRadians(270))
                .splineTo(new Vector2d(57.1, -48.8), Math.toRadians(270))

                .splineTo(new Vector2d(57.1, -50.5), Math.toRadians(270), new TranslationalVelConstraint(30))
                //score first specimen
                .setTangent(Math.toRadians(180))
                .splineTo(new Vector2d(0 ,scorespecimeny - 5), Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(0 ,scorespecimeny), Math.toRadians(270))




                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}