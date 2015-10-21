package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.robotclasses.Bucket;
import com.qualcomm.ftcrobotcontroller.robotclasses.ZipLineTrigger;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by zahnerj01 on 10/18/15.
 */
public class ServoTester extends OpMode {

    ZipLineTrigger leftTrigger;
    ZipLineTrigger rightTrigger;
    Bucket bucket;

    @Override
    public void init(){
        leftTrigger = new ZipLineTrigger("leftTrigger", 1.0, 0.05, hardwareMap);
        rightTrigger = new ZipLineTrigger("rightTrigger", 1.0, 0.05, hardwareMap);
        bucket = new Bucket("bucket", 0, 0.5, hardwareMap);
    }

    @Override
    public void loop(){

        telemetry.addData("LeftTrigger", leftTrigger.toString());
        telemetry.addData("RightTrigger", rightTrigger.toString());
        telemetry.addData("Bucket", bucket.toString());

    }

}
