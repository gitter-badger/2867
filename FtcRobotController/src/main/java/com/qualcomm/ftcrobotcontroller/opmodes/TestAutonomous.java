package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
 * Created by zahnerj01 on 9/10/15.
 */
public class TestAutonomous extends LinearOpMode {

    final static double DEFAULT_MOTOR_POWER = 0.5;
    final static double WHEEL_RADIUS = 4;
    final static HardwareMap hardwareMap = new HardwareMap();
    Drive drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
    ZipLineTrigger leftTrigger = new ZipLineTrigger("zip_trigger_left", 0, 1, hardwareMap);
    ZipLineTrigger rightTrigger = new ZipLineTrigger("zip_trigger_right", 0, 1, hardwareMap);
    ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        drive.move(0.75);
        sleep(2000);
        drive.move(-0.75);
        sleep(2000);
        drive.turn(true, 0.5);
        sleep(1000);
        drive.turn(false, 0.5);
        sleep(1000);
        leftTrigger.triggerDown();
        leftTrigger.triggerUp();
        rightTrigger.triggerDown();
        rightTrigger.triggerUp();

    }

    public void determineSide(int expectedColor){
        double colorFound = colorSensor.argb();
        if(colorFound == 1.0){
            //move a servo to out position
        }
        else if(colorFound == 2.0){
            //move other servo to out position
        }
    }



}
