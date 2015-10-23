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

<<<<<<< Updated upstream
    public ButtonPresser(String servo, double outPosition, double inPosition, HardwareMap hardwareMap) {
=======
    public ButtonPresser(String servoName, double outPosition, double inPosition, HardwareMap hardwareMap){

>>>>>>> Stashed changes
        this.outPosition = outPosition;
        this.inPosition = inPosition;
        this.hardwareMap = hardwareMap;

<<<<<<< Updated upstream
        buttonPressServo = hardwareMap.servo.get(servo);
    }

    public void setDirection(Servo.Direction direction){
        buttonPressServo.setDirection(direction);
    }

    public void swingOut(){
        buttonPressServo.setPosition(outPosition);
=======
        buttonPresserServo = hardwareMap.servo.get(servoName);
>>>>>>> Stashed changes
    }

    public void moveOut() {buttonPresserServo.setPosition(outPosition);}

    public void moveIn() {buttonPresserServo.setPosition(inPosition);}

}