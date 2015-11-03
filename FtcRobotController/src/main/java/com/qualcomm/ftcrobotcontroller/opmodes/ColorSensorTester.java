package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.GrapplingHook;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.hardware.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 10/30/15.
 */
public class ColorSensorTester extends OpMode {

    final static double DEFAULT_MOTOR_POWER = 0.5;
    final static double WHEEL_RADIUS = 5;

    public static enum colors {BLUE, RED};

    public Drive drive;
    public ButtonPresser leftButtonPresser;
    public ButtonPresser rightButtonPresser;
    public ColorSensor colorSensor;

    @Override
    public void init(){

        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftButtonPresser = new ButtonPresser("leftPresser", 1.0, 0.4, hardwareMap);
        rightButtonPresser = new ButtonPresser("rightPresser", 1.0, 0.4, hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("color_sensor");

        if(colorSensor != null){
            telemetry.addData("Color Sensor", colorSensor.getConnectionInfo() + " " + colorSensor.getDeviceName());
        }

        telemetry.addData("Stuff", "Just ran init");

        rightButtonPresser.setDirection(Servo.Direction.REVERSE);

        leftButtonPresser.setPosition(1.0);
        rightButtonPresser.setPosition(1.0);

    }

    @Override
    public void loop(){

        if(gamepad1.a){
            telemetry.addData("Color Sensor:", colorSensor.blue() + " " + colorSensor.red() + " " + colorSensor.argb());
            switch(readColorSensor()){
                case 0:
                    colorFound(colors.BLUE);
                    break;
                case 1:
                    colorFound(colors.RED);
                    break;
                default:
                    break;
            }

        }

    }

    public int readColorSensor(){
        int blue = colorSensor.blue();
        int red = colorSensor.red();
        int isBlue;
        if(blue > 200 && red < 100){
            isBlue = 0;
        }
        else if(red > 200 && blue < 100){
            isBlue = 1;
        }
        else{
            isBlue = 2;
        }
        telemetry.addData("isBlue", isBlue);
        telemetry.addData("Color Sensor Value:", blue + " " + red);
        return isBlue;
    }

    public void colorFound(colors color){
        switch(color){
            case BLUE:
                leftButtonPresser.swingOut();
                leftButtonPresser.swingIn();
                break;
            case RED:
                rightButtonPresser.swingOut();
                rightButtonPresser.swingIn();
                break;
        }
    }

}
