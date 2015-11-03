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

        zipLineTriggerServo = hardwareMap.servo.get(servoName);

        this.upPosition = upPosition;
        this.downPosition = downPosition;
        this.hardwareMap = hardwareMap;
    }

    public void setDirection(Servo.Direction direction){
        zipLineTriggerServo.setDirection(direction);
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

    public String toString(){
        return zipLineTriggerServo.getDeviceName() + "is at position: " + Double.toString(zipLineTriggerServo.getPosition());
    }

}
