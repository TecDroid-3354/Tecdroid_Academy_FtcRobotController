package org.firstinspires.ftc.teamcode.ArmJoint;

import android.icu.lang.UCharacter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDController;

public class ArmJoint {

    private DcMotorEx armMotor;

    private PIDController pidController;

    private double targetPosition = 0.0;

    public ArmJoint(HardwareMap hw) {
        armMotor = hw.get(DcMotorEx.class, "armMotor");
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        pidController = new PIDController(0.01, 0.0, 0.0);
    }

    public void goToPosition() {
        double motorPositionInDegrees = armMotor.getCurrentPosition() / 537.7 * 360.0;
        double voltage = pidController.calculate(motorPositionInDegrees, targetPosition);

        armMotor.setPower(voltage);
    }
    public void setTargetPosition(double position){
        targetPosition = position;
    }


}
