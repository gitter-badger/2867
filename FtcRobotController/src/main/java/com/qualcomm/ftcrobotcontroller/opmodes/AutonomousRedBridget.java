package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.ButtonPresser;
import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.ftcrobotcontroller.robotclasses.DriveDirectionEnum;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by farrellb on 10/20/15.
 */
public class AutonomousRedBridget extends LinearOpMode {

    Drive drive;
    ButtonPresser buttonPresserLeft;
    ButtonPresser buttonPresserRight;

    @Override
    public void runOpMode() throws InterruptedException{

        drive = new Drive(0.5, 5, hardwareMap);


//move 5 feet
//turn 90 degrees
//move 5 feet five inches

        waitForStart();

        drive.move(0.5);
        sleep(2100);
        drive.turn(DriveDirectionEnum.RIGHT, 0.5);
        sleep(1350);
        drive.move(0.5);
        sleep(2000);



        drive.turn(DriveDirectionEnum.RIGHT, 0.5);
        sleep(1350);
        drive.move(0.5);
        sleep(350);

    }

}

