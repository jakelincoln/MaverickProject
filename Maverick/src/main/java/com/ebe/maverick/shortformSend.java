package com.ebe.maverick;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/28/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class shortformSend {

    private String machineID;
    private ArrayList<shortformSendBatch> batchList;

    public shortformSend(){
        machineID = "";
        batchList = new ArrayList<shortformSendBatch>();
    }

    public shortformSend(String MachineCode){
        machineID = MachineCode;
        batchList = new ArrayList<shortformSendBatch>();
    }

    public void addBatch(shortformSendBatch batch){
        batchList.add(batch);
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getMachineID() {
        return machineID;
    }

    public ArrayList<shortformSendBatch> getBatchList() {
        return batchList;
    }
}
