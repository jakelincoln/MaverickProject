package com.ebe.maverick;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/28/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class shortformReturn {

    private ArrayList<shortformBatchReturn> returnBatch;

    public shortformReturn(){
        returnBatch = new ArrayList<shortformBatchReturn>();
    }

    public shortformReturn(ArrayList<shortformBatchReturn> list){
        returnBatch = list;
    }

    public void addBatch(int batchID, int counter){
        returnBatch.add(new shortformBatchReturn(batchID,counter));
    }

}
