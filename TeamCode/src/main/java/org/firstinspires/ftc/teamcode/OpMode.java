package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.ButtonReader;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.ArmJoint.ArmJoint;
import org.firstinspires.ftc.teamcode.DriveTrain.TankDrive;
import org.firstinspires.ftc.teamcode.Garra.Gripper;
import org.firstinspires.ftc.teamcode.Slider.SliderMotor;

@TeleOp(name = "CMD", group = "Op mode")

public class OpMode extends LinearOpMode {

    private TankDrive tankDrive;
    private ArmJoint armJoint;
    private SliderMotor sliderMotor;
    private ButtonReader bReader;
    private Gripper gripper;

    public void initialize() {
        tankDrive = new TankDrive(hardwareMap);
        armJoint = new ArmJoint(hardwareMap);
        bReader = new ButtonReader(new GamepadEx(gamepad1), GamepadKeys.Button.RIGHT_BUMPER);
        sliderMotor = new SliderMotor(hardwareMap);
        gripper = new Gripper(hardwareMap);
    }

    @Override
    public void runOpMode() {
        initialize();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("ArmJoint Position", armJoint.getPosition());
            telemetry.addData("gripper", gamepad1.right_trigger);
            telemetry.addData("SliderMotor",sliderMotor.getPosition());

            tankDrive.drive(gamepad1.left_stick_y, gamepad1.right_stick_x);
            //armJoint.goToPosition();

            //armJoint.setTargetPosition(90.0);


            if (gamepad1.y){armJoint.setTargetPosition(90.0);}
            if (gamepad1.b){armJoint.setTargetPosition(50.0);}
            if (gamepad1.a){
                armJoint.setTargetPosition(20.0);
            }

            if (gamepad1.right_trigger > 0.0) {
                sliderMotor.moveMotor(1.0);
            }
            else {
                sliderMotor.moveMotor(0.0);
            }

            if (gamepad1.left_trigger > 0.0){
                sliderMotor.moveMotor(-0.4);
            } else {
                sliderMotor.moveMotor(0.0);
            }

            if (gamepad1.left_bumper){
                gripper.open();
            }
            else if (gamepad1.right_bumper){
                gripper.close();
            }

            telemetry.update();

        }
    }
}

