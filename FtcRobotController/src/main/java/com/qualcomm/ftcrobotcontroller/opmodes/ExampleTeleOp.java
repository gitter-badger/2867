package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.GrapplingHook;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Joshua Zahner on 9/6/15.
 */
public class ExampleTeleOp extends OpMode {

    final static double DEFAULT_MOTOR_POWER = 0.5;
    final static double WHEEL_RADIUS = 5;

    private boolean leftTriggerDown, rightTriggerDown, leftButtonPresserOut, rightButtonPresserOut, bucketDown;

    private boolean dPadLeftPressed, dPadRightPressed, dPadTopPressed, dPadBottomPressed;
    private boolean aButtonPressed, bButtonPressed, xButtonPressed, yButtonPressed;
    private boolean released, rightBumperPressed, leftBumperPressed;


    Drive drive;
    ZipLineTrigger leftTrigger, rightTrigger;
    ButtonPresser leftButtonPresser, rightButtonPresser;
    Bucket bucket;
    GrapplingHook grapplingHook;

    @Override
    public void init() {

        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftTrigger = new ZipLineTrigger("leftTrigger", 1.0, 0.5, hardwareMap);
        rightTrigger = new ZipLineTrigger("rightTrigger", 1.0, 0.5, hardwareMap);
        leftButtonPresser = new ButtonPresser("leftPresser", 1.0, 0.4, hardwareMap);
        rightButtonPresser = new ButtonPresser("rightPresser", 1.0, 0.4, hardwareMap);
        bucket = new Bucket("bucket", 1.0, 0.1, hardwareMap);
        grapplingHook = new GrapplingHook("release", "winch", hardwareMap);

        telemetry.addData("Stuff", "Just ran init");

        rightTrigger.setDirection(Servo.Direction.REVERSE);
        rightButtonPresser.setDirection(Servo.Direction.REVERSE);

        leftTrigger.setTriggerPosition(1.0);
        rightTrigger.setTriggerPosition(1.0);
        leftButtonPresser.setPosition(1.0);
        rightButtonPresser.setPosition(1.0);

        bucket.setPosition(1.0);

        gamepad1.left_stick_y = 0;
        gamepad1.right_stick_y = 0;

    }

    @Override
    public void loop() {

        gamepad1.setJoystickDeadzone((float) 0.2);

        drive.moveFreely(gamepad1.left_stick_y, gamepad1.right_stick_y);

         /**   DIRECTIONAL PAD CONTROLS
         *
         *     LEFT: Left Zipline Trigger
         *     RIGHT: Right Zipline Trigger
         *
         */
        if (gamepad1.dpad_left) {
            if (!dPadLeftPressed) {
                if (!leftTriggerDown) {
                    leftTrigger.triggerDown();
                    leftTriggerDown = true;
                } else {
                    leftTrigger.triggerUp();
                    leftTriggerDown = false;
                }
            }

            dPadLeftPressed = true;

        } else if (gamepad1.dpad_right) {
            if (!dPadRightPressed) {
                if (!rightTriggerDown) {
                    rightTrigger.triggerDown();
                    rightTriggerDown = true;
                } else {
                    rightTrigger.triggerUp();
                    rightTriggerDown = false;
                }
            }

            dPadRightPressed = true;

        } else {
            dPadLeftPressed = false;
            dPadRightPressed = false;
        }

        /**     BUTTONS CONTROLS
         *
         *      A: Bucket
         *      B: Right Button Presser
         *      X: Left Button Presser
         *      Y: None
         *
         */
        if (gamepad1.a) {
            if (!aButtonPressed) {
                if (!bucketDown) {
                    bucket.forward();
                    bucketDown = true;
                } else {
                    bucket.returnToStart();
                    bucketDown = false;
                }
            }

            aButtonPressed = true;
        } else if (gamepad1.x) {
            if (!xButtonPressed) {
                if (!leftButtonPresserOut) {
                    leftButtonPresser.swingOut();
                    leftButtonPresserOut = true;
                } else {
                    leftButtonPresser.swingIn();
                    leftButtonPresserOut = false;
                }
            }

            xButtonPressed = true;
        } else if (gamepad1.b) {
            if (!bButtonPressed) {
                if (!rightButtonPresserOut) {
                    rightButtonPresser.swingOut();
                    rightButtonPresserOut = true;
                } else {
                    rightButtonPresser.swingIn();
                    rightButtonPresserOut = false;
                }
            }

            bButtonPressed = true;
        } else {
            aButtonPressed = false;
            xButtonPressed = false;
            bButtonPressed = false;
        }



        if (gamepad1.left_bumper) {
            grapplingHook.winch();
            telemetry.addData("Grappling Hook", "Winch in progress!");
        }
        else if(gamepad1.left_trigger == 1){
            grapplingHook.winchBackwards();
        }
        else{
            grapplingHook.stopWinch();
        }

        if (gamepad1.right_bumper) {
            grapplingHook.release();
            telemetry.addData("Grappling Hook", "Grappling Hook released!");
        }
        else if(gamepad1.right_trigger > 0.75){
            grapplingHook.releaseBackwards();
        }
        else{
            grapplingHook.stopRelease();
        }

        if (gamepad1.start) {
            kill();
        }

        telemetry.addData("Left Trigger:", leftTrigger + " " + leftTriggerDown + " " + dPadLeftPressed);
        telemetry.addData("RightTrigger:", rightTrigger + " " + rightTriggerDown + " " + dPadRightPressed);
        telemetry.addData("LeftButtonPresser", leftButtonPresser + " " + leftButtonPresserOut + " " + xButtonPressed);
        telemetry.addData("RightButtonPresser", rightButtonPresser + " " + rightButtonPresserOut + " " + bButtonPressed);
        telemetry.addData("Bucket", bucket + " " + bucketDown + " " + aButtonPressed);

    }

    public void kill() {

        leftTrigger.triggerUp();
        rightTrigger.triggerUp();

        leftButtonPresser.swingIn();
        rightButtonPresser.swingIn();

        bucket.returnToStart();

        drive.move(0);

        grapplingHook.stopRelease();
        grapplingHook.stopWinch();

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
