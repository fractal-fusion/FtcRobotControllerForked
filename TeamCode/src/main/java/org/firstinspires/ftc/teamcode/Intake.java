package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Intake {
    //declare the servos
    public Servo claw;
    public Servo wrist;

    public Servo pivot;

    //gamepads for rising edge detection to add debounce
    private Gamepad currentGamepad = new Gamepad();
    private Gamepad previousGamepad = new Gamepad();

    //boolean for the toggling of the claw
    private boolean clawIsOpen = true;

    //define preset degrees for the opening and closing of the claw
    public static double open = 0.0;
    public static double close = 0.32;

    //define the two wrist positions position.
    final double wristHorizontalPos = 0.0;
    final double wristVerticalPos = 0.3;

    //wrist control
    double wristIncrementTotal = 0.0;
    double wristIncrement = 0.05;

    //pivot control
    double pivotPosition = 0.0;
    public static double pivotPositionMax = 0.7;
    double pivotPositionOffset = 0.0;
    public static double pivotIncrement = 0.35;

    public static double pivotDownPos = 0.0;
    public static double pivotStraightPos = 0.35;
    public static double pivotUprightPos = pivotPositionMax;

    //opmode variable
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
        pivot = opMode.hardwareMap.get(Servo.class, "pivot");
    }

    //sets the open or close mode of the claw
    public void mode(double servoPos) {
        claw.setPosition(servoPos);
    }

    public void telemetry() {
        opMode.telemetry.addData("claw position:", claw.getPosition());
        opMode.telemetry.addData("wrist position:", wrist.getPosition());
        opMode.telemetry.addData("pivot position:", pivot.getPosition());
    }

    public void updateGamepad(Gamepad gamepad) {
        //copy the correct previous and current gamepad values in order for the debounce to work
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad);
    }

    public void toggleClaw(Gamepad gamepad) {
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

    public void controlPivot(double pos, Gamepad gamepad) {
        if (currentGamepad.x && !previousGamepad.x) {
            pivotPositionOffset += pivotIncrement;
        }
        else if (currentGamepad.b && !previousGamepad.b) {
            pivotPositionOffset -= pivotIncrement;
        }
        pivotPositionOffset = clampDouble(pivotPositionOffset, -pos, pivotPositionMax - pos);
        pivotPosition = pos + pivotPositionOffset;
        pivotPosition = clampDouble(pivotPosition, 0, pivotPositionMax);
//        opMode.telemetry.addData("pivot target position", pivotPosition);
//        opMode.telemetry.addData("pivot offset", pivotPositionOffset);
//        opMode.telemetry.addData("pivot actual position,", pivot.getPosition());
        pivot.setPosition(pivotPosition);
    }

    public void testPivot(Gamepad gamepad) {
        if (currentGamepad.x && !previousGamepad.x) {
            pivotPosition += 0.05;
        }
        else if (currentGamepad.b && !previousGamepad.b) {
            pivotPosition -= 0.05;
        }

        opMode.telemetry.addData("pivot position,", pivot.getPosition());
        opMode.telemetry.update();

        pivotPosition = clampDouble(pivotPosition, 0, pivotPositionMax);
        pivot.setPosition(pivotPosition);
    }

    //returns the wrist to resting position
    public void setHorizontalPos() {
        wrist.setPosition(wristHorizontalPos);
    }

    //roadrunner actions

    public Action closeClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(Intake.close);
                return false;
            }
        };
    }



    public Action openClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(Intake.open);
                return false;
            }
        };
    }

    public Action rotateWristToHorizontal() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wrist.setPosition(wristHorizontalPos);
                return false;
            }
        };
    }

    public Action rotateWristToVertical() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wrist.setPosition(wristVerticalPos);
                return false;
            }
        };
    }

    public Action rotatePivotDown() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pivot.setPosition(pivotDownPos);
                return false;
            }
        };
    }

    public Action rotatePivotStraight() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pivot.setPosition(pivotStraightPos);
                return false;
            }
        };
    }
    public Action rotatePivotUpright() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pivot.setPosition(pivotUprightPos);
                return false;
            }
        };
    }
}