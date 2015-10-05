package com.qualcomm.ftcrobotcontroller.opmodes;

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

    Drive drive;
    ZipLineTrigger leftTrigger;
    ZipLineTrigger rightTrigger;
    ColorSensor colorSensor;

    @Override
    public void init() {
        drive = new Drive(DEFAULT_MOTOR_POWER, WHEEL_RADIUS, hardwareMap);
        leftTrigger = new ZipLineTrigger("zip_trigger_left", 0, 1, hardwareMap);
        rightTrigger = new ZipLineTrigger("zip_trigger_right", 0, 1, hardwareMap);


        telemetry.addData("Stuff", "Just ran init");


    }

    @Override
    public void loop() {

        /*double throttle = -gamepad1.left_stick_y;
        double direction = gamepad1.right_stick_x;
        double right = throttle - direction;
        double left = throttle + direction;

        left = Range.clip(left, -1,1);
        right = Range.clip(right, -1,1);*/

        drive.moveFreely(gamepad1.left_stick_y, gamepad1.right_stick_y);

        if(gamepad1.dpad_left){
            if(!leftTriggerDown) {
                leftTrigger.triggerDown();
                leftTriggerDown = true;
                telemetry.addData("Left Trigger:", leftTrigger.toString());
            }
            else{
                leftTrigger.triggerUp();
                leftTriggerDown = false;
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

    }



}
