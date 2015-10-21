package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 10/20/15.
 */
public class Bucket {

    double startPosition;
    double endPosition;
    Servo bucketServo;

    public Bucket(String servoName, double startPosition, double endPosition, HardwareMap hardwareMap){
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        bucketServo = hardwareMap.servo.get(servoName);
    }

    public void forward(){
        bucketServo.setPosition(this.endPosition);
    }

    public void returnToStart(){
        bucketServo.setPosition(this.startPosition);
    }

    public void setPosition(double position){
        bucketServo.setPosition(position);
    }

    public String toString(){
        return "Bucket is at position" + Double.toString(bucketServo.getPosition());
    }

}
