package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by farrellb on 10/27/15.
 */
public class TestDrive {

    private double DEFAULT_POWER;                                               //Default Motor Power used for all robot drive motors
    private double WHEEL_RADIUS;                                                  //The radius of utilized wheels

    private DcMotor motorRight;
    private DcMotor motorLeft;

    private HardwareMap hardwareMap;
    private GyroSensor gyroSensor;

    public TestDrive(double defaultPower, double wheelRadius, HardwareMap hardwareMap) {
        this.DEFAULT_POWER = defaultPower;
        this.WHEEL_RADIUS = wheelRadius;
        this.hardwareMap = hardwareMap;

        motorLeft = hardwareMap.dcMotor.get("motor_drive_left");                 //maps front-left motor to variable
        motorRight = hardwareMap.dcMotor.get("motor_drive_right");               //maps front-right motor to variable

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);                             //Reverses left motors
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveLeftSide(double power){
        motorFrontLeft.setPower(power);
    }
    public void moveRightSide(double power){
        motorFrontRight.setPower(power);
    }

    public void moveFreely(double leftPower, double rightPower){
        moveLeftSide(leftPower);
        moveRightSide(rightPower);
    }

    public void move(double power){
        moveFreely(power, power);
    }
    public void move(){
        moveFreely(DEFAULT_POWER, DEFAULT_POWER);
    }

    public void turn(String direction, double power){
        if(direction.equals("LEFT")){
            moveFreely(-power, power);
        }
        else if(direction.equals("RIGHT")){
            moveFreely(power, -power);
        }
    }

    public void turnForAngle(double angle){
        double gyroAngle = 0;
        while(gyroAngle != angle+1 || gyroAngle != angle || gyroAngle != angle-1){
            gyroAngle = gyroSensor.getRotation();
            if(angle > 0){
                turn("LEFT", 0.5);
            }
            else if(angle < 0){
                turn("RIGHT", 0.5);
            }
        }
    }
}
