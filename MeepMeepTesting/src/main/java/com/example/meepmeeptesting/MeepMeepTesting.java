package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        double tilelength = 24;
        double collectspecimenx = tilelength, collectspecimeny = -62;
        double scorespecimeny = -32;
        double specimendegrees = 0;

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.PI, Math.PI, 13.465)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(tilelength, -62, Math.toRadians(90)))

//close claw
//                .stopAndAdd(intake.closeClaw())

                //show to human player where all the specimens should be placed
//                .strafeTo(new Vector2d(collectspecimenx, collectspecimeny))
//                .turnTo(Math.toRadians(specimendegrees))
                //prime viperslides for specimen
//                .stopAndAdd(arm.primeScoreSpecimenViperslides())
//                .waitSeconds(2)

                //go to specimen bar and score first specimen
                // move arm
//                .stopAndAdd(arm.moveArmToScoreDegrees())
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(2,scorespecimeny), Math.toRadians(90))
                .waitSeconds(1)

                //raise viperslides to score the specimen, then open claw and deextend viperslides and move arm down
                .stopAndAdd(new SequentialAction(
//                        arm.scoreSpecimenViperslides(),
                        new SleepAction(0.3)
//                        intake.openClaw()
//                        arm.retractViperslides()
//                        arm.moveArmtoRestPosition(),
//                        new SleepAction(1.8)
                ))

                //strafe away from the bar
                .strafeTo(new Vector2d(2,-38))

//                .stopAndAdd(arm.primeScoreSpecimenViperslides())

                //go back for second specimen
                .turnTo(Math.toRadians(specimendegrees))
                .strafeTo(new Vector2d(collectspecimenx, collectspecimeny))

                //score second specimen
                .strafeToLinearHeading(new Vector2d(2,scorespecimeny), Math.toRadians(90))
                .waitSeconds(1)

                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}