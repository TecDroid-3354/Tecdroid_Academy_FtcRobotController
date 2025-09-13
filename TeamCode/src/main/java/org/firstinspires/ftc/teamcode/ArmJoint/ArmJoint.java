package org.firstinspires.ftc.teamcode.ArmJoint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDController;



public class ArmJoint {

    private DcMotorEx armMotor;

    private PIDController pidController;

    private double targetPosition = 0.0;

    private double minLimit = 1.0;

    private double maxLimit = 74.0;

    public ArmJoint(HardwareMap hw) {
        armMotor = hw.get(DcMotorEx.class, "armMotor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pidController = new PIDController( 0.01, 0.0, 0.0);
    }
    public void goToPosition(){
      double motorPositionInDegrees = getPosition();
       double voltage = pidController.calculate(motorPositionInDegrees, targetPosition) + 0.15 * Math.cos(Math.toRadians(getPosition()));

       moveMotor(voltage);
    }

    public void moveMotor(double voltage){
        if (!(voltage > 0.0 && getPosition() > maxLimit || voltage < 0.0 && getPosition() < minLimit)) {
            armMotor.setPower(voltage);
        } else {
            armMotor.setPower(0.0);
        }
    }

    public void setTargetPosition(double position){
        targetPosition = position;
    }

    public double getPosition(){
        double maxTicksValue = 286.0;

        return (armMotor.getCurrentPosition()) * 75.0 / maxTicksValue ;
    }



}
