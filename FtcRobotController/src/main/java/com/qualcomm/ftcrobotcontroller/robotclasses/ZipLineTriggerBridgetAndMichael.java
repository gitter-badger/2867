package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 10/17/15.
 */
public class ZipLineTriggerBridgetAndMichael{

    private double upPosition;
    private double downPosition;
    private Servo zipLineTriggerServo;
    private HardwareMap hardwareMap;

    public ZipLineTriggerBridgetAndMichael(String servoName, double upPosition, double downPosition, HardwareMap hardwareMap){
        this.upPosition = upPosition;
        this.downPosition = downPosition;
        this.hardwareMap = hardwareMap;

        zipLineTriggerServo = hardwareMap.servo.get(servoName);
    }

    public void moveDown(){
        zipLineTriggerServo.setPosition(downPosition);
    }

    public void moveUp(){
        zipLineTriggerServo.setPosition(upPosition);
    }


}
