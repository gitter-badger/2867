package com.qualcomm.ftcrobotcontroller.robotclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by zahnerj01 on 10/27/15.
 */
public class GrapplingHook {

    private DcMotor winch;
    private DcMotor release;

    public GrapplingHook(String motorReleaseName, String motorWinchName, HardwareMap hardwareMap){
        winch = hardwareMap.dcMotor.get(motorWinchName);
        release = hardwareMap.dcMotor.get(motorReleaseName);
    }

    public void winch(){
        winch.setPower(0.8);
    }

    public void release(){
        release.setPower(0.3);
    }

}
