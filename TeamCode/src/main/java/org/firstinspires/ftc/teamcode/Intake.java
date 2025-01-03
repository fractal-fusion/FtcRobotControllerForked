package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    //declare the servos
    public Servo claw;
    public Servo wrist;

    //gamepads for rising edge detection to add debounce
    private Gamepad currentGamepad = new Gamepad();
    private Gamepad previousGamepad = new Gamepad();

    //boolean for the toggling of the claw
    private boolean clawIsOpen = true;

    //define preset degrees for the opening and closing of the claw
    public static final double open = 0.25;
    public static final double close = 0.0;

    //define the two wrist positions position.
    final double wristHorizontalPos = 0.0;
    final double wristVerticalPos = 0.3;

    //wrist control
    double wristIncrementTotal = 0.0;
    double wristIncrement = 0.05;

    private OpMode opMode;

    //utility function
    private double clampDouble(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    //constructor which acts as an initialization function for whenever an object of the class is created
    public Intake(OpMode linearopmode){
        this.opMode = linearopmode;
        claw = opMode.hardwareMap.get(Servo.class, "claw");
        wrist = opMode.hardwareMap.get(Servo.class, "wrist");
    }

    //sets the open or close mode of the claw
    public void mode(double servoPos) {
        claw.setPosition(servoPos);
        opMode.telemetry.addData("claw position", servoPos);
    }

    public void toggleClaw(Gamepad gamepad) {
        //copy the correct previous and current gamepad values in order for the debounce to work
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad);

        //toggle the clawisopen boolean
        if (currentGamepad.a && !previousGamepad.a) {
            clawIsOpen = !clawIsOpen;
        }

        //control the claw based on the boolean
        if (clawIsOpen) {
            claw.setPosition(open);
        }
        else {
            claw.setPosition(close);
        }

    }

    //lets the passed in gamepad control the wrist
    public void controlWrist(Gamepad gamepad) {
        //since restingPos is 0.5, max position to the left would be 0.5 + -0.5 resulting in zero and 0.5 + 0.5
        //would result in one
        //double servoTarget = (gamepad.right_stick_x * wristVerticalPos);

        wristIncrementTotal += gamepad.right_stick_x * wristIncrement;
        wristIncrementTotal = clampDouble(wristIncrementTotal,0.0, wristVerticalPos);
        double servoTarget = wristIncrementTotal;
        wrist.setPosition(servoTarget);
    }

    //returns the wrist to resting position
    public void setHorizontalPos() {
        wrist.setPosition(wristHorizontalPos);
    }

    //roadrunner actions

    public Action closeClaw() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    claw.setPosition(Intake.close);
                    initialized = true;
                }
                return initialized;
            }
        };
    }

    public Action openClaw() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    claw.setPosition(Intake.open);
                    initialized = true;
                }
                return initialized;
            }
        };
    }

    public Action rotateWristToHorizontal() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    wrist.setPosition(wristHorizontalPos);
                    initialized = true;
                }
                return initialized;
            }
        };
    }

    public Action rotateWristToVertical() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    wrist.setPosition(wristVerticalPos);
                    initialized = true;
                }
                return initialized;
            }
        };
    }

}