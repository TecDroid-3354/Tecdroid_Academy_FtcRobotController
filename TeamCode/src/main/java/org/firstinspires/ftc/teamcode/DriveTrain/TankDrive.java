package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class TankDrive {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    public TankDrive(HardwareMap hw) {
        leftMotor = hw.get(DcMotorEx.class, "leftMotor");
        rightMotor = hw.get(DcMotorEx.class, "rightMotor");
    }

    public void drive(double yAxis, double xAxis) {
        rightMotor.setPower(yAxis + xAxis);
        leftMotor.setPower(-(yAxis - xAxis));
    }
}
