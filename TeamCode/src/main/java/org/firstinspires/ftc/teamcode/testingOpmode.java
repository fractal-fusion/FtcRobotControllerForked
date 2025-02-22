package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Testing Opmode", group="Robot")
public class testingOpmode extends LinearOpMode {
    //initialize objects
    Arm arm = new Arm(this);
    Intake intake = new Intake(this);
    Drivetrain drivetrain = new Drivetrain(this);

    @Override
    public void runOpMode() throws InterruptedException {

        //telemetry to show the robot is initialized
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            drivetrain.drive(gamepad1);

            arm.controlArm(gamepad2);
            intake.updateGamepad(gamepad2);
            intake.testPivot(gamepad2);
        }
    }
}
