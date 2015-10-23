package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTriggerBridgetAndMichael;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by zahnerj01 on 10/17/15.
 */
public class AutoMoveForward extends LinearOpMode{

    Drive drive;
    ZipLineTriggerBridgetAndMichael zipTriggerLeft;
    ZipLineTriggerBridgetAndMichael zipTriggerRight;
    ButtonPresser buttonPresserLeft;
    ButtonPresser buttonPresserRight;

    @Override
    public void runOpMode() throws InterruptedException{

        drive = new Drive(0.5, 5, hardwareMap);
        zipTriggerLeft = new ZipLineTriggerBridgetAndMichael("leftTriggerServo", 0.5, 0, hardwareMap);
        zipTriggerRight = new ZipLineTriggerBridgetAndMichael("rightTriggerServo", 0.5, 0, hardwareMap);
        buttonPresserLeft = new ButtonPresser("leftButtonPresserServo", 0.5, 0, hardwareMap);
        buttonPresserRight = new ButtonPresser("rightButtonPresserServo", 0.5, 0, hardwareMap);



        waitForStart();

        drive.move(0.5);
        sleep(6000);



       /* drive.move(0.5);
        sleep(1000);
        drive.turn("LEFT", 0.25);
        sleep(1000);

        drive.move(0.5);
        sleep(1000);
        drive.turn("LEFT", 0.25);
        sleep(1000);

        drive.move(0.5);
        sleep(1000);
        drive.turn("LEFT", 0.25);
        sleep(1000);

        drive.move(0.5);
        sleep(1000);
        drive.turn("LEFT", 0.25);
        sleep(1000);

        /*zipTriggerLeft.moveDown();
        sleep(1000);
        zipTriggerLeft.moveUp();
        sleep(1000);

        zipTriggerRight.moveDown();
        sleep(1000);
        zipTriggerRight.moveUp();
        sleep(1000);*/






    }

}
