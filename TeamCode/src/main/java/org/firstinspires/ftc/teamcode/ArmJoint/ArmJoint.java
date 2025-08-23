package org.firstinspires.ftc.teamcode.ArmJoint;

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
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pidController = new PIDController( 0.01, 0.0, 0.0);
    }
    public void goToPosition(){
      double motorPositionInDegrees = getPosition();
       double voltage = pidController.calculate(motorPositionInDegrees, targetPosition);

       armMotor.setPower(voltage);
    }
    public void setTargetPosition(double position){
        targetPosition = position;
    }

    public double getPosition(){
        return (armMotor.getCurrentPosition() / 383.6) * 360.0 ;
    }



}
