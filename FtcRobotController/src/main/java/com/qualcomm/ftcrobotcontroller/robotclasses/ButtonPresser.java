package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 10/16/15.
 */
public class ButtonPresser{

    private double outPosition;
    private double inPosition;
    private Servo buttonPresserServo;
    private HardwareMap hardwareMap;

    public ButtonPresser(String servoName, double outPosition, double inPosition, HardwareMap hardwareMap){

        this.outPosition = outPosition;
        this.inPosition = inPosition;
        this.hardwareMap = hardwareMap;

        buttonPresserServo = hardwareMap.servo.get(servoName);
    }

    public void setDirection(Servo.Direction direction){
        buttonPresserServo.setDirection(direction);
    }

    public void swingOut(){
        buttonPresserServo.setPosition(outPosition);
    }

    public void swingIn(){
        buttonPresserServo.setPosition(inPosition);
    }

    public void setPosition(double position){
        buttonPresserServo.setPosition(position);
    }

}