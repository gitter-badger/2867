package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Nathan Skelton on 9/5/15.
 */

public class Drive {

    private double DEFAULT_POWER;                                               //Default Motor Power used for all robot drive motors
    private double WHEEL_RADIUS;                                                //The radius of utilized wheels

    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;

    private HardwareMap hardwareMap;

    private GyroHelper gyroHelper;

    //Drive Constructor. Dictate the default motorPower (probably 0.5-0.75) and the wheel RADIUS!
    public Drive(double defaultPower, double wheelRadius, HardwareMap hardwareMap) {
        this.DEFAULT_POWER = defaultPower;
        this.WHEEL_RADIUS = wheelRadius;
        this.hardwareMap = hardwareMap;

        //gyroHelper = new GyroHelper(hardwareMap);
        
        motorFrontLeft = hardwareMap.dcMotor.get("motor_drive_front_left");                 //maps front-left motor to variable
        motorFrontRight = hardwareMap.dcMotor.get("motor_drive_front_right");               //maps front-right motor to variable
        motorBackLeft = hardwareMap.dcMotor.get("motor_drive_back_left");                   //maps back-left motor to variable
        motorBackRight = hardwareMap.dcMotor.get("motor_drive_back_right");                 //maps back-right motor to variable

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);                             //Reverses left motors
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveLeftSide(double power){
        motorFrontLeft.setPower(power);
        motorBackLeft.setPower(power);
    }
    public void moveRightSide(double power){
        motorFrontRight.setPower(power);
        motorBackRight.setPower(power);
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

    public void turn(DriveDirectionEnum direction, double power){

        switch (direction) {

            case LEFT:
                moveFreely(-power, power);
                break;

            case RIGHT:
                moveFreely(power, -power);
                break;
        }
    }

    /*public void turnForAngle(double angle, DriveDirectionEnum direction){

        gyroHelper.calibrate();

        angle = Math.abs(angle);

        if(direction == DriveDirectionEnum.RIGHT){
            while(angle > gyroHelper.getAngle()) {
                turn(DriveDirectionEnum.RIGHT, 0.5);
            }
        }
        else{
            while(angle > gyroHelper.getAngle()){
                turn(DriveDirectionEnum.LEFT, 0.5);
            }
        }

    }*/

}
