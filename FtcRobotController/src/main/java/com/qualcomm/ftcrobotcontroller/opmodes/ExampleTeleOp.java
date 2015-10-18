package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Nathan Skelton on 9/6/15.
 */
public class ExampleTeleOp extends OpMode {

    final static double DEFAULT_MOTOR_POWER = 0.5;
    final static double WHEEL_RADIUS = 5;

    private boolean leftTriggerDown;
    private boolean rightTriggerDown;

    private boolean dPadLeftPressed;
    private boolean dPadRightPressed;

    Drive drive;
    ZipLineTrigger leftTrigger;
    ZipLineTrigger rightTrigger;
    ColorSensor colorSensor;

    @Override
    public void init() {
        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftTrigger = new ZipLineTrigger("leftTrigger", 0, 0.05, hardwareMap);
        rightTrigger = new ZipLineTrigger("rightTrigger", 0, 0.05, hardwareMap);

        leftTriggerDown = false;
        rightTriggerDown = false;
        dPadLeftPressed = false;
        dPadRightPressed = false;

        telemetry.addData("Stuff", "Just ran init");


    }

    @Override
    public void loop() {

        drive.moveFreely(gamepad1.left_stick_y, gamepad1.right_stick_y);

        /*if(gamepad1.dpad_left){
            leftTrigger.incrementTriggerPosition();
            telemetry.addData("Stuff", leftTrigger.toString());
        }
        else if (gamepad1.dpad_right){
            leftTrigger.decrememtTriggerPosition();
            telemetry.addData("Stuff", leftTrigger.toString());
        }*/

        if(gamepad1.dpad_left){

            dPadLeftPressed = true;

            if(leftTriggerDown){
               leftTrigger.triggerUp();
               telemetry.addData("Left Trigger:", leftTrigger.toString());
            }
            else if(!leftTriggerDown){
                leftTrigger.triggerDown();
                telemetry.addData("Left Trigger:", leftTrigger.toString());
            }

        }
        else if(gamepad1.dpad_right){
            if(!rightTriggerDown) {
                rightTrigger.triggerDown();
                rightTriggerDown = true;
                telemetry.addData("Right Trigger:", rightTrigger.toString());
            }
            else{
                rightTrigger.triggerUp();
                rightTriggerDown = false;
                telemetry.addData("Right Trigger:", rightTrigger.toString());
            }
        }

        dPadLeftPressed = false;

    }



}
