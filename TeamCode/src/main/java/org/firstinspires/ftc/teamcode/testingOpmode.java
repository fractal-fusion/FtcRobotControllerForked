package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Testing Opmode", group="Robot")
@Config
public class testingOpmode extends LinearOpMode {
    //initialize objects
    Arm arm = new Arm(this);


    @Override
    public void runOpMode() throws InterruptedException {

        //telemetry to show the robot is initialized
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            if (gamepad1.x) {
                arm.motorTest();
            }
        }
    }
}
