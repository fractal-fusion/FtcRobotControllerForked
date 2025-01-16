package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="productionOpmode League Champs", group="Robot")
public class productionOpmode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize objects
        Drivetrain drivetrain = new Drivetrain(this);
        Arm arm = new Arm(this);
        Intake intake = new Intake(this);

        //set intake to resting position on init
        intake.setHorizontalPos();

        //telemetry to show the robot is initialized
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            //intake telemetry
            intake.telemetry();

            //gamepad 1 driving
            drivetrain.drive(gamepad1);

            //gamepad 2 arm
            arm.controlViperslides(gamepad2);
            //arm.controlArm(gamepad2);
            intake.controlWrist(gamepad2);

            //gamepad 2 intake control
            intake.toggleClaw(gamepad2);

            //preset positions for gamepad2
            if (gamepad2.dpad_down) {
                arm.moveArm(Arm.collectionDegrees);
            }
            else if (gamepad2.left_bumper) {
                arm.moveArm(Arm.clearBarrierDegrees);
            }
            else if (gamepad2.y) {
                arm.moveArm(Arm.scoreDegrees);
            }
            else if (gamepad2.dpad_left) {
                arm.moveArm(Arm.hangExtendedDegrees);
            }
            else if (gamepad2.dpad_right) {
                arm.moveArm(Arm.hangClimbDegrees);
            }

            //old intake control
//            else if (gamepad2.a) {
//                intake.mode(Intake.close);
//            }
//            else if (gamepad2.b) {
//                intake.mode(Intake.open);
//            }
        }
    }
}
