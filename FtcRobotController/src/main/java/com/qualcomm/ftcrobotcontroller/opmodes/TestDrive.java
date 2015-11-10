package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Drive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by farrellb on 10/27/15.
 */
public class TestDrive extends LinearOpMode {

    private HardwareMap hardwareMap;
    private DcMotor motorRight;
    private DcMotor motorLeft;

    Drive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new Drive(0.5, 5, hardwareMap);

        motorLeft = hardwareMap.dcMotor.get("LEFT");
        motorRight = hardwareMap.dcMotor.get("RIGHT");


        waitForStart();

        motorLeft.setPower(0.5);
        motorRight.setPower(0.5);
        sleep(1000);
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}