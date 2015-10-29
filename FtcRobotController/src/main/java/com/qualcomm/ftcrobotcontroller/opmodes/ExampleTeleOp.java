package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.GrapplingHook;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Joshua Zahner on 9/6/15.
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
    private boolean released;


    Drive drive;
    ZipLineTrigger leftTrigger;
    ZipLineTrigger rightTrigger;
    ButtonPresser leftButtonPresser;
    ButtonPresser rightButtonPresser;
    Bucket bucket;
    GrapplingHook grapplingHook;
    ColorSensor colorSensor;

    @Override
    public void init() {

        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftTrigger = new ZipLineTrigger("leftTrigger", 1.0, 0.5, hardwareMap);
        rightTrigger = new ZipLineTrigger("rightTrigger", 1.0, 0.5, hardwareMap);
        leftButtonPresser = new ButtonPresser("leftPresser", 1.0, 0.4, hardwareMap);
        rightButtonPresser = new ButtonPresser("rightPresser", 1.0, 0.4, hardwareMap);
        bucket = new Bucket("bucket", 1.0, 0.1, hardwareMap);
        grapplingHook = new GrapplingHook("g_hook_release", "g_hook_winch", hardwareMap);

        leftTriggerDown = false;
        rightTriggerDown = false;
        leftButtonPresserOut = false;
        rightButtonPresserOut = false;

        bucketDown = false;

        dPadLeftPressed = false;
        dPadRightPressed = false;
        dPadTopPressed = false;
        dPadBottomPressed = false;
        aButtonPressed = false;
        bButtonPressed = false;
        xButtonPressed = false;
        yButtonPressed = false;
        released = false;

        telemetry.addData("Stuff", "Just ran init");

        rightTrigger.setDirection(Servo.Direction.REVERSE);
        rightButtonPresser.setDirection(Servo.Direction.REVERSE);

        leftTrigger.setTriggerPosition(1.0);
        rightTrigger.setTriggerPosition(1.0);
        leftButtonPresser.setButtonPressServoPosition(1.0);
        rightButtonPresser.setButtonPressServoPosition(1.0);
        bucket.setPosition(1.0);

    }

    @Override
    public void loop(){

        drive.moveFreely(gamepad1.left_stick_y, gamepad1.right_stick_y);

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

        if(gamepad1.left_bumper){
            grapplingHook.winch();
            telemetry.addData("Grappling Hook", "Winch in progress!");
        }
        else if(gamepad1.right_bumper && !released){
            grapplingHook.release();
            released = true;
            telemetry.addData("Grappling Hook", "Grappling Hook released!");
            kill();

        }

        if(gamepad1.start){
            kill();
        }

        telemetry.addData("Left Trigger:", leftTrigger + " " + leftTriggerDown + " " + dPadLeftPressed);
        telemetry.addData("RightTrigger:", rightTrigger + " " + rightTriggerDown + " " + dPadRightPressed);
        telemetry.addData("LeftButtonPresser", leftButtonPresser + " " + leftButtonPresserOut + " " + xButtonPressed);
        telemetry.addData("RightButtonPresser", rightButtonPresser + " " + rightButtonPresserOut + " " + bButtonPressed);
        telemetry.addData("Bucket", bucket + " " + bucketDown + " " + aButtonPressed);

    }

    public void kill(){

        leftTrigger.triggerUp();
        rightTrigger.triggerUp();

        leftButtonPresser.swingIn();
        rightButtonPresser.swingIn();

        bucket.returnToStart();

        drive.move(0);

        leftTriggerDown = false;
        rightTriggerDown = false;
        leftButtonPresserOut = false;
        rightButtonPresserOut = false;

        bucketDown = false;

        dPadLeftPressed = false;
        dPadRightPressed = false;
        dPadTopPressed = false;
        dPadBottomPressed = false;
        aButtonPressed = false;
        bButtonPressed = false;
        xButtonPressed = false;
        yButtonPressed = false;
        released = false;

    }


}
