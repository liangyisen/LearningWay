package com.yisen.distributedTransaction;

import javax.transaction.xa.Xid;

public class MyXid implements Xid {
    public int formatId;
    public byte gtrid[];
    public byte bqual[];

    public MyXid(int formatId, byte[] gtrid, byte[] bqual) {
        this.formatId = formatId;
        this.gtrid = gtrid;
        this.bqual = bqual;
    }


    @Override
    public int getFormatId() {
        return 0;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return new byte[0];
    }

    @Override
    public byte[] getBranchQualifier() {
        return new byte[0];
    }
}
