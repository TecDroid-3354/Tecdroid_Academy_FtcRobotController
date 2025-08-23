package org.firstinspires.ftc.teamcode.Slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDController;

public class SliderMotor {

    private DcMotorEx sliderMotor;

    private double targetPosition = 0.0;
    private PIDController pidController;

    private double minLimit = 0.4;

    private double maxLimit = 4.0;

    public SliderMotor (HardwareMap hw) {
        sliderMotor = hw.get(DcMotorEx.class, "sliderMotor");
        sliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sliderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        pidController = new PIDController(0.01, 0.0, 0.0);
    }


    public void goToPosition(){
        double motorPositionInDegrees = getPosition();
        double voltage = pidController.calculate(motorPositionInDegrees, targetPosition);

        sliderMotor.setPower(voltage);
    }

    public void moveMotor(double voltage){
        if (!(voltage > 0.0 && getPosition() > maxLimit || voltage < 0.0 && getPosition() < minLimit)) {
            sliderMotor.setPower(voltage);
        } else {
            sliderMotor.setPower(0.0);
        }
    }

    public void setTargetPosition(double position){
        targetPosition = position;
    }

    public double getPosition(){
        return (sliderMotor.getCurrentPosition() / 537.7) * 1.25;
    }

}









