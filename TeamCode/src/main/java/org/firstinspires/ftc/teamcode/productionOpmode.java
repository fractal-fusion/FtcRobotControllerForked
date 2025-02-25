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

        //degree variable for arm
        double degrees = 0;
        //pivot position variable for intake
        double pivotpos = 0;

        waitForStart();

        while (opModeIsActive())
        {
            //intake telemetry
//            intake.telemetry();

            //gamepad 1 driving
            drivetrain.drive(gamepad1);

            //gamepad 2 arm
            //arm.controlArm(gamepad2);
            arm.moveArm(degrees, gamepad2);
            arm.controlViperslides(gamepad2);

            //gamepad 2 intake control
            intake.updateGamepad(gamepad2);
            intake.toggleClaw(gamepad2);
            intake.controlWrist(gamepad2);
            intake.controlPivot(pivotpos, gamepad2);

            //preset positions for gamepad2
            if (gamepad2.dpad_down) {
                degrees = Arm.collectionDegrees;
                pivotpos = Intake.pivotDownPos;
            }
            else if (gamepad2.left_bumper) {
                degrees = Arm.clearBarrierDegrees;
            }
            else if (gamepad2.right_bumper) {
                degrees = Arm.scoreSpecimenDegrees;
                pivotpos = Intake.pivotUprightPos;
            }
            else if (gamepad2.y) {
                degrees = Arm.scoreDegrees;
               // pivotpos = Intake.pivotStraightPos;
            }
            else if (gamepad2.dpad_left) {
                degrees = Arm.hangExtendedDegrees;
//                pivotpos = Intake.pivotUprightPos;
            }
            else if (gamepad2.dpad_right) {
                degrees = Arm.hangClimbDegrees;
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
