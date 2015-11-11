package com.qualcomm.ftcrobotcontroller.robotclasses;

/**
 * Created by zahnerj01 on 11/10/15.
 */

import com.qualcomm.robotcore.hardware.I2cDevice;

public class MRGyroHelper {

    private I2cDevice gyroSensor;

    public MRGyroHelper(I2cDevice modernRoboticsGyro) {
        this.gyroSensor = modernRoboticsGyro;
    }

    public byte getZRotationLSB() {
        gyroSensor.readI2cCacheFromController();
        byte[] values = gyroSensor.getCopyOfReadBuffer();
        return values[0x06];
    }

    public byte getZRotationMSB() {
        gyroSensor.readI2cCacheFromController();
        byte[] values = gyroSensor.getCopyOfReadBuffer();
        return values[0x07];
    }

    public byte getHeadingLSB(){
        gyroSensor.readI2cCacheFromController();
        byte[] values = gyroSensor.getCopyOfReadBuffer();
        return values[0x04];
    }

    public byte getHeadingMSB(){
        gyroSensor.readI2cCacheFromController();
        byte[] values = gyroSensor.getCopyOfReadBuffer();
        return values[0x05];
    }



}
