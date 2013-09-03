package com.ebe.maverick;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/28/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShortformPreSend {


    private int machineName;
    private ArrayList<Integer> batchNum;

    public ShortformPreSend(){
        batchNum = new ArrayList<Integer>();
        machineName = 0;
    }

    public ShortformPreSend(int MachName){
       batchNum = new ArrayList<Integer>();
        machineName = MachName;
    }

    public ShortformPreSend(int MachName, ArrayList<Integer> batches){
        batchNum = batches;
        machineName = MachName;
    }

    public void addBatch(int batchID){
        batchNum.add(batchID);
    }
}
