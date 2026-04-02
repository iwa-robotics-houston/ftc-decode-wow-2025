package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class Limelight{
    public enum LimelightMode{
        GOAL_TAG,
        OBELISK_TAG
    }
    private LimelightMode currentMode;
    Limelight3A limelight;
    private LLResult result;
    private boolean limelightIsOff;
    private boolean motifDetected;
    public int detectedTagId;
    private double ta;
    private double tx;
    private boolean resultWorks;
    public void initLimelight(HardwareMap hweM){
        limelight = hweM.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(40);
        limelight.start();
        limelightIsOff = false;
        result = null;
        detectedTagId = -1;
        motifDetected = false;
    }

    public LLResult updateLimelight(){
        if(limelight != null){
            if(limelightIsOff){
                limelight.start();
                limelightIsOff = !limelightIsOff;
            }
            result = limelight.getLatestResult();
            return result;
        }

        return null;
    }
    public void setPipeline(int ppSwitch){
        if(ppSwitch == 8){
            limelight.pipelineSwitch(8);
        }else if(ppSwitch == 9){
            limelight.pipelineSwitch(9);
        }else if(ppSwitch == 0){
            limelight.pipelineSwitch(0);
        }else if(ppSwitch==2){
            limelight.pipelineSwitch(2);
        }
    }
    public int scanMotif(LLResult result){
        if(detectedTagId > 20){
            return detectedTagId;
        }
        if(result != null && result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (int ID = 21; ID <= 23; ID++) {
                if (!motifDetected) {
                    for (LLResultTypes.FiducialResult fr : fiducialResults) {
                        if (fr.getFiducialId() == ID) {
                            detectedTagId = ID;
                            motifDetected = true;
                            return ID;
                        }
                    }
                }
            }
        }
        return -1;
    }
    public void scanGoal(){
        if(result != null && result.isValid()) {
            setTa(result.getTa());
            setTx(result.getTx());
            resultWorks = true;
        }else{
            resultWorks = false;
        }
    }
    public void stop(){
        limelight.stop();
        limelightIsOff = true;
    }
    public boolean resultWorks(){
        return resultWorks;
    }

    public void setTa(double ta){
        this.ta = ta;
    }
    public void setTx(double tx){
        this.tx = tx;
    }
    public double getTa(){
        return ta;
    }
    public double getTx(){
        return tx;
    }
    //Calcs distance from goal using limelight identified april tag area
    public double getDistance(double ta){
        double scale = 30665.950;
        return (Math.pow((scale/ta),0.5));
    }
    public void setLimelightMode(LimelightMode newMode){
        currentMode = newMode;
    }
    public int getDetectedTagId(){
        return detectedTagId;
    }



}