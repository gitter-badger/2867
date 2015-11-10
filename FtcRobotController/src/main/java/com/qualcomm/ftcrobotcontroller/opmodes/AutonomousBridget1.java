package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.DriveDirectionEnum;
import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by farrellb on 11/6/15.
 */
public class AutonomousBridget1 extends LinearOpMode {

    Drive drive;
    Bucket bucket;

    @Override
    public void runOpMode() throws InterruptedException{

        drive = new Drive(0.5, 5, hardwareMap);
        bucket = new Bucket("bucket", 1.0, 0.1, hardwareMap);


        //move 115 inches
        //turn 140 degrees
        //move 30 inches

        waitForStart();

        drive.move(0.5);
        sleep(3050);
        drive.turn(DriveDirectionEnum.RIGHT, 0.5);
        sleep(500);
        drive.move(0.5);
        sleep(1400);
        drive.move(0);
        bucket.forward();
        sleep(100);
        bucket.returnToStart();
    }

}
