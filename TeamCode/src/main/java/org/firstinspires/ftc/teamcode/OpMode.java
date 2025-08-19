package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.DriveTrain.TankDrive;

@TeleOp(name = "CMD", group = "Op mode")

public class OpMode extends LinearOpMode {

    private TankDrive tankDrive;
    public void initialize() {
        tankDrive = new TankDrive(hardwareMap);
    }

    @Override
    public void runOpMode() {
        initialize();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            tankDrive.drive(gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}

