package org.firstinspires.ftc.teamcode.Intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.solversHardware.SolversCRServo;

public class Intake {
    SolversCRServo servo;

    public Intake(HardwareMap hw) {
        servo = new SolversCRServo(hw.get(CRServo.class, "intakeServo"), 0.75);
    }

    public void runIntake() {
        servo.setPower(1.0);
    }

    public void runOuttake() {
        servo.setPower(-1.0);
    }

    public void stopIntake() {
        servo.setPower(0.0);
    }

    public void setBindings(boolean intakeButton, boolean outtakeButton) {
        if (intakeButton) {
            runIntake();
        } else if (outtakeButton) {
            runOuttake();
        } else {
            stopIntake();
        }
    }
}
