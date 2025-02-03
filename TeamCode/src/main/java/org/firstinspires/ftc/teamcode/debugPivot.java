package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="debugPivot", group="Robot")

public class debugPivot extends LinearOpMode {

    public Servo pivot = null;
    double servoposition = 0.0;
    public void runOpMode() {
        pivot  = hardwareMap.get(Servo.class, "pivot");
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                pivot.setPosition(0);
            }
            if (gamepad1.right_bumper) {
                servoposition = 1.0;
            }
            if (gamepad1.left_bumper) {
                servoposition = 0.0;
            }

            pivot.setPosition(servoposition);

            telemetry.addData("pivot position: ", pivot.getPosition());
            telemetry.update();
        }

    }

}
