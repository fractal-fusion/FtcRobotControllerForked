package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="debugWrist", group="Robot")

public class debugWrist extends LinearOpMode {

    public Servo wrist = null;
    double servoposition = 0.0;
    public void runOpMode() {
        wrist  = hardwareMap.get(Servo.class, "wrist");
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                //wrist.setPosition(0);
                wrist.setPosition(0);
            }
            if (gamepad1.right_bumper) {
                servoposition = 1.0;
            }
            if (gamepad1.left_bumper) {
                servoposition = 0.0;
            }

            wrist.setPosition(servoposition);

            telemetry.addData("wrist position: ", wrist.getPosition());
            telemetry.update();
        }

    }

}
