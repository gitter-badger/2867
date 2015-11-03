package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.DriveDirectionEnum;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by zahnerj01 on 10/31/15.
 */
public class GyroTester extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        Drive drive  = new Drive(0.5, 5, hardwareMap);

        drive.move();
        sleep(1000);

        drive.turnForAngle(90, DriveDirectionEnum.LEFT);

        drive.move();
        sleep(500);

        drive.turnForAngle(180, DriveDirectionEnum.RIGHT);

        drive.move();
        sleep(1000);

        drive.turnForAngle(90, DriveDirectionEnum.LEFT);


    }

}
