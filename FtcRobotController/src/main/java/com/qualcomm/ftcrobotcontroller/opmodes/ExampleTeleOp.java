package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nathan Skelton on 9/6/15.
 */
public class ExampleTeleOp extends OpMode {

    final static double DEFAULT_MOTOR_POWER = 0.5;
    final static double WHEEL_RADIUS = 5;

    private boolean leftTriggerDown;
    private boolean rightTriggerDown;
    private boolean leftButtonPresserOut;
    private boolean rightButtonPresserOut;
    private boolean bucketDown;

    private boolean dPadLeftPressed;
    private boolean dPadRightPressed;
    private boolean dPadTopPressed;
    private boolean dPadBottomPressed;
    private boolean aButtonPressed;
    private boolean bButtonPressed;
    private boolean xButtonPressed;
    private boolean yButtonPressed;


    Drive drive;
    ZipLineTrigger leftTrigger;
    ZipLineTrigger rightTrigger;
    ButtonPresser leftButtonPresser;
    ButtonPresser rightButtonPresser;
    Bucket bucket;
    ColorSensor colorSensor;

    @Override
    public void init() {

        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftTrigger = new ZipLineTrigger("leftTrigger", 1.0, 0.6, hardwareMap);
        rightTrigger = new ZipLineTrigger("rightTrigger", 1.0, 0.4, hardwareMap);
        leftButtonPresser = new ButtonPresser("leftPresser", 0.5, 0.75, hardwareMap);
        rightButtonPresser = new ButtonPresser("rightPresser", 0.5, 0.75, hardwareMap);
        bucket = new Bucket("bucket", 0, 0.5, hardwareMap);

        leftTriggerDown = false;
        rightTriggerDown = false;

        bucketDown = false;

        dPadLeftPressed = false;
        dPadRightPressed = false;
        dPadTopPressed = false;
        dPadBottomPressed = false;
        aButtonPressed = false;
        bButtonPressed = false;
        xButtonPressed = false;
        yButtonPressed = false;

        telemetry.addData("Stuff", "Just ran init");

        rightTrigger.setDirection(Servo.Direction.REVERSE);
        rightButtonPresser.setDirection(Servo.Direction.REVERSE);

        leftTrigger.setTriggerPosition(1.0);
        rightTrigger.setTriggerPosition(0.0);
        leftButtonPresser.setButtonPressServoPosition(0.5);
        rightButtonPresser.setButtonPressServoPosition(0.5);
        bucket.setPosition(0);

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
            if(!dPadLeftPressed) {
                if (!leftTriggerDown) {
                    leftTrigger.triggerDown();
                    leftTriggerDown = true;
                }
                else {
                    leftTrigger.triggerUp();
                    leftTriggerDown = false;
                }
            }

            dPadLeftPressed = true;

        }
        else if(gamepad1.dpad_right){
            if(!dPadRightPressed){
                if(!rightTriggerDown){
                    rightTrigger.triggerDown();
                    rightTriggerDown = true;
                }
                else{
                    rightTrigger.triggerUp();
                    rightTriggerDown = false;
                }
            }

            dPadRightPressed = true;

        }
        else{
            dPadLeftPressed = false;
            dPadRightPressed = false;
        }


        if(gamepad1.a){
            if(!aButtonPressed){
                if(!bucketDown){
                    bucket.forward();
                    bucketDown = true;
                }
                else{
                    bucket.returnToStart();
                    bucketDown = false;
                }
            }

            aButtonPressed = true;
        }
        else if(gamepad1.x){
            if(!xButtonPressed){
                if(!leftButtonPresserOut) {
                    leftButtonPresser.swingOut();
                    leftButtonPresserOut = true;
                }
                else{
                    leftButtonPresser.swingIn();
                    leftButtonPresserOut = false;
                }
            }

            xButtonPressed = true;
        }
        else if(gamepad1.b){
            if(!bButtonPressed){
                if(!rightButtonPresserOut){
                    rightButtonPresser.swingOut();
                    rightButtonPresserOut = true;
                }
                else{
                    rightButtonPresser.swingIn();
                    rightButtonPresserOut = false;
                }
            }

            bButtonPressed = true;
        }
        else{
            aButtonPressed = false;
            xButtonPressed = false;
            bButtonPressed = false;
        }

        telemetry.addData("Left Trigger:", leftTrigger.toString() + " " + Boolean.toString(leftTriggerDown) + " " + Boolean.toString(dPadLeftPressed));
        telemetry.addData("RightTrigger:", rightTrigger.toString() + " " + Boolean.toString(rightTriggerDown) + " " + Boolean.toString(dPadRightPressed));
        telemetry.addData("LeftButtonPresser", leftButtonPresser.toString() + " " + Boolean.toString(leftButtonPresserOut) + " " + Boolean.toString(xButtonPressed));
        telemetry.addData("RightButtonPresser", rightButtonPresser.toString() + " " + Boolean.toString(rightButtonPresserOut) + " " + Boolean.toString(bButtonPressed));
        telemetry.addData("Bucket", bucket.toString() + " " + Boolean.toString(bucketDown) + " " + Boolean.toString(aButtonPressed));

    }



}
