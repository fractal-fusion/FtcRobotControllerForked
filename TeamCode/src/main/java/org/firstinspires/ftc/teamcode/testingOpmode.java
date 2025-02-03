package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Testing Opmode", group="Robot")
@Config
public class testingOpmode extends LinearOpMode {
    //initialize objects
    Intake intake = new Intake(this);
    public static double pivotpos = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {

        //telemetry to show the robot is initialized
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            intake.pivot.setPosition(pivotpos);
            telemetry.addData("pivot pos:", intake.pivot.getPosition());
            telemetry.update();
        }
    }
}
