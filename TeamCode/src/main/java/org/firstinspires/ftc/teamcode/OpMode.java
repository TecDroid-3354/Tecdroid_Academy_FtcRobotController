package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.ArmJoint.ArmJoint;
import org.firstinspires.ftc.teamcode.ArmJoint.Movement;
import org.firstinspires.ftc.teamcode.DriveTrain.TankDrive;
import org.firstinspires.ftc.teamcode.Intake.Intake;

@TeleOp(name = "CMD", group = "Op mode")

public class OpMode extends LinearOpMode {
    private TankDrive tankDrive;

    private ArmJoint bottomJoint;

    private ArmJoint upperJoint;

    private Intake intake;

    private GamepadEx controller;

    public void initialize() {
        tankDrive = new TankDrive(hardwareMap);
        controller = new GamepadEx(gamepad1);
        bottomJoint = new ArmJoint(hardwareMap, "bottomJoint", 1100, -30);
        upperJoint = new ArmJoint(hardwareMap, "upperJoint", 860, -80);
        intake =  new Intake(hardwareMap);
    }

    @Override
    public void runOpMode() {
        initialize();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Upper reading ", upperJoint.getJointPosition());
            telemetry.addData("Bottom reading ", bottomJoint.getJointPosition());
            telemetry.update();

            tankDrive.drive(-gamepad1.left_stick_y * 0.8, -gamepad1.right_stick_x * 0.6);
            intake.setBindings(controller.gamepad.y, controller.gamepad.x);


            if (gamepad1.right_trigger > 0.1){
                bottomJoint.setJointMovement(Movement.Upwards, 0.75);
            } else if (gamepad1.left_trigger > 0.1) {
                bottomJoint.setJointMovement(Movement.Downwards, 0.75);
            } else {
                bottomJoint.setJointMovement(Movement.Stopped, 0.75);
            }
            if (gamepad1.right_bumper){
                upperJoint.setJointMovement(Movement.Upwards, 0.3);
            } else if (gamepad1.left_bumper) {
                upperJoint.setJointMovement(Movement.Downwards, 0.3);
            } else {
                upperJoint.setJointMovement(Movement.Stopped, 0.3);
            }
        }
    }
}

