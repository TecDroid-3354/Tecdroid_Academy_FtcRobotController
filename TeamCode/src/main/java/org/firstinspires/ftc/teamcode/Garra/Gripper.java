package org.firstinspires.ftc.teamcode.Garra;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.gamepad.ButtonReader;

public class Gripper {

    Servo servo;

    public Gripper(HardwareMap hw) {
    servo = hw.get(Servo.class, "gripperServo");
    }

    public void goToPosition(double position){
        servo.setPosition(position);

    }

    public void open(){
        goToPosition(0.0);
    }

    public void close (){
        goToPosition(0.35);
    }
}
