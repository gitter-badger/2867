package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 10/16/15.
 */
public class ButtonPresser {

    private double outPosition;
    private double inPosition;
    private Servo buttonPressServo;
    private HardwareMap hardwareMap;

    public ButtonPresser(double outPosition, double inPosition, String servo, HardwareMap hardwareMap) {
        this.outPosition = outPosition;
        this.inPosition = inPosition;
        this.hardwareMap = hardwareMap;

        buttonPressServo = hardwareMap.servo.get(servo);
    }

    public void swingOut(){
        buttonPressServo.setPosition(outPosition);
    }

    public void swingIn(){
        buttonPressServo.setPosition(inPosition);
    }

    public void setButtonPressServoPosition(double position){
        buttonPressServo.setPosition(position);
    }

    public String toString(){
        return buttonPressServo.getDeviceName() + " is at position " + Double.toString(buttonPressServo.getPosition());
    }

}
