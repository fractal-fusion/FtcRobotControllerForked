package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="productionOpmode Manual Arm", group="Robot")
public class productionOpmodeManualArm extends LinearOpMode {

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

        //move subsystems to starting positions
        intake.mode(Intake.open);

        waitForStart();

        while (opModeIsActive())
        {
            //gamepad 1 driving
            drivetrain.drive(gamepad1);

            //gamepad 2 arm
            arm.controlViperslides(gamepad2);
            arm.controlArm(gamepad2);
            intake.controlWrist(gamepad2);

            //gamepad 2 intake control
            intake.toggleClaw(gamepad2);

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
