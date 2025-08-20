package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ArmJoint.ArmJoint;
import org.firstinspires.ftc.teamcode.DriveTrain.TankDrive;

@TeleOp(name = "CMD", group = "Op mode")

public class OpMode extends LinearOpMode {
    private TankDrive tankDrive;

    private ArmJoint armJoint;


    public void initialize() {
        tankDrive = new TankDrive(hardwareMap);
        armJoint = new ArmJoint(hardwareMap);
    }

    @Override
    public void runOpMode() {
        initialize();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            tankDrive.drive(gamepad1.left_stick_y, gamepad1.right_stick_x);
            armJoint.goToPosition();

            armJoint.setTargetPosition(90.0);
        }
    }
}

