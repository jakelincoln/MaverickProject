package com.ebe.maverick;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/28/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class shortformBatchReturn {

    private int batchNum;
    private int batchCount;

    public shortformBatchReturn(){
        batchNum = 0;
        batchCount = 0;
    }

    public shortformBatchReturn(int batch){
        batchNum = batch;
        batchCount = 0;
    }

    public shortformBatchReturn(int batch, int count){
        batchNum = batch;
        batchCount = count;
    }

    public void setCount(int count){
        batchCount = count;
    }
}
