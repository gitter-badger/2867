package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 11/12/15.
 */
public class ColorPressBlue extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        ColorSensor colorSensor;
        ButtonPresser leftButtonPresser, rightButtonPresser;
        Drive drive;

        colorSensor = hardwareMap.colorSensor.get("gyro_sensor");
        leftButtonPresser = new ButtonPresser("leftPresser", 1.0, 0.4, hardwareMap);
        rightButtonPresser = new ButtonPresser("rightPresser", 1.0, 0.4, hardwareMap);
        drive = new Drive(0.5, 3, hardwareMap);

        leftButtonPresser.setPosition(1.0);
        rightButtonPresser.setPosition(1.0);

        rightButtonPresser.setDirection(Servo.Direction.REVERSE);

        if(colorSensor.blue() >= 1){
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.addData("COLOR FOUND", "Blue");
            leftButtonPresser.swingOut();
            drive.move(1.0);
            sleep(1500);
        }
        else{
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("COLOR FOUND", "Red");
            rightButtonPresser.swingOut();
            drive.move(1.0);
            sleep(1500);
        }


    }

}
