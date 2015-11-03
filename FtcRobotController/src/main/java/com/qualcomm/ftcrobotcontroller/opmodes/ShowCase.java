package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.DriveDirectionEnum;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by farrellb on 10/29/15.
 */
public class ShowCase extends LinearOpMode {

    Drive drive;
    ZipLineTrigger zipTriggerLeft;
    ZipLineTrigger zipTriggerRight;
    ButtonPresser buttonPresserLeft;
    ButtonPresser buttonPresserRight;
    HardwareMap hardwareMap;
    Bucket bucket;


    @Override
    public void runOpMode() throws InterruptedException{

        drive = new Drive(0.5, 5, hardwareMap);
        zipTriggerLeft = new ZipLineTrigger("leftTrigger", 1.0, 0.5, hardwareMap);
        zipTriggerRight = new ZipLineTrigger("rightTrigger", 1.0, 0.5, hardwareMap);
        buttonPresserLeft = new ButtonPresser("leftPresser", 1.0, 0.4, hardwareMap);
        buttonPresserRight = new ButtonPresser("rightPresser", 1.0, 0.4, hardwareMap);
        bucket = new Bucket("bucket", 1.0, 0.1, hardwareMap);

        zipTriggerLeft.triggerDown();
        sleep(1000);
        zipTriggerLeft.triggerUp();
        sleep(1000);
        zipTriggerRight.triggerDown();
        sleep(1000);
        zipTriggerRight.triggerUp();
        sleep(1000);
        buttonPresserLeft.swingOut();
        sleep(1000);
        buttonPresserLeft.swingIn();
        sleep(1000);
        buttonPresserRight.swingOut();
        sleep(1000);
        buttonPresserRight.swingIn();
        sleep(1000);
        bucket.forward();
        sleep(1000);
        bucket.returnToStart();
        sleep(1000);
        drive.move(0.25);
        sleep(500);
        drive.move(-0.25);
        sleep(500);
        drive.turn(DriveDirectionEnum.RIGHT, 0.25);
        sleep(500);
        drive.turn(DriveDirectionEnum.LEFT, 0.25);
        sleep(500);
        drive.turn(DriveDirectionEnum.LEFT, 0.25);
        sleep(500);
        drive.turn(DriveDirectionEnum.RIGHT, 0.25);
        sleep(500);
        drive.move(0);


    }

}
