package com.ebe.maverick;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/28/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class shortformSendBatch {

    private int batchNum;
    private ArrayList<shortformSendApplicant> applicants;

    public shortformSendBatch(){
    }

    public shortformSendBatch(int batchID){
        batchNum = batchID;
        applicants = new ArrayList<shortformSendApplicant>();
    }

    public shortformSendBatch(int batchID, ArrayList<shortformSendApplicant> applicantList){
        batchNum = batchID;
        applicants = applicantList;
    }

    public void addApplicant(shortformSendApplicant newApplicant){
        applicants.add(newApplicant);
    }

    public int getBatchNum() {
        return batchNum;
    }

    public ArrayList<shortformSendApplicant> getApplicants() {
        return applicants;
    }
}
