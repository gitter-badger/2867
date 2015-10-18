package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by zahnerj01 on 9/27/15.
 */

public class ZipLineTrigger {

    private double upPosition;
    private double downPosition;
    private Servo zipLineTriggerServo;
    private HardwareMap hardwareMap;

    public ZipLineTrigger(String servoName, double upPosition, double downPosition, HardwareMap hardwareMap){
        this.upPosition = upPosition;
        this.downPosition = downPosition;
        this.hardwareMap = hardwareMap;

        zipLineTriggerServo = hardwareMap.servo.get(servoName);
    }

    public void triggerDown(){
        zipLineTriggerServo.setPosition(downPosition);
    }

    public void triggerUp(){
        zipLineTriggerServo.setPosition(upPosition);
    }

    public void setTriggerPosition(double position){
        zipLineTriggerServo.setPosition(position);
    }

    public void incrementTriggerPosition(){
        double startingPosition = zipLineTriggerServo.getPosition();
        if(startingPosition != 1.0){
            zipLineTriggerServo.setPosition(startingPosition + 0.05);
        }
    }

    public void decrememtTriggerPosition(){
        double startingPosition = zipLineTriggerServo.getPosition();
        if(startingPosition != 0.0){
            zipLineTriggerServo.setPosition(startingPosition - 0.05);
        }
    }

    public String toString(){
        return zipLineTriggerServo.getDeviceName() + "is at position" + Double.toString(zipLineTriggerServo.getPosition());
    }

}
