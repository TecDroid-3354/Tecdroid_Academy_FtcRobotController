package org.firstinspires.ftc.teamcode.ArmJoint;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class ArmJoint extends SubsystemBase {

    private final Motor armMotor;

    private final Motor.Encoder encoder;

    private final double maxTicks;

    private final double minTicks;


    public ArmJoint(HardwareMap hw, String motorName, double maxTicks, double minTicks) {
        this.armMotor = new Motor(hw, motorName, Motor.GoBILDA.RPM_60);
        this.armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.armMotor.setRunMode(Motor.RunMode.RawPower);
        this.armMotor.setVeloCoefficients(0.001, 0.0, 0.000001);
        this.encoder = armMotor.encoder;
        this.encoder.setDirection(Motor.Direction.REVERSE);
        this.encoder.reset();
        this.maxTicks = maxTicks;
        this.minTicks = minTicks;
    }

    public void setJointMovement(Movement moveUpwards, double output) {
        switch (moveUpwards) {
            case Upwards:
                if (getJointPosition() < maxTicks || getJointPosition() > minTicks) {
                    armMotor.set(-output);
                }
                break;
            case Downwards:
                if (getJointPosition() < maxTicks + 500 || getJointPosition() > minTicks -100) {
                    armMotor.set(output);
                }
                break;
            case Stopped:
                armMotor.stopMotor();
                break;
        }
    }
    public double getJointPosition() {
        return encoder.getPosition();
    }
}