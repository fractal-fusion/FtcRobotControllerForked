package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="debugClaw", group="Robot")

public class debugClaw extends LinearOpMode {
    public Servo claw = null;
    double servoposition = 0.0;
    public void runOpMode() {
        claw = hardwareMap.get(Servo.class, "claw");
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                //wrist.setPosition(0);
                claw.setPosition(0);
            }
            if (gamepad1.right_bumper) {
                servoposition = 1.0;
            }
            if (gamepad1.left_bumper) {
                servoposition = 0.0;
            }

            claw.setPosition(servoposition);

            telemetry.addData("claw position: ", claw.getPosition());
            telemetry.update();
        }

    }

}
