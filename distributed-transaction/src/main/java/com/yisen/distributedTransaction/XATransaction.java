package com.yisen.distributedTransaction;

import com.mysql.cj.jdbc.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

public class XATransaction {

    public static MysqlXADataSource getDatasource(String connString, String user, String passwd) {
        try {
            MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
            mysqlXADataSource.setUrl(connString);
            mysqlXADataSource.setUser(user);
            mysqlXADataSource.setPassword(passwd);
            return mysqlXADataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String connString1 = "jdbc:mysql://localhost:3306/eason?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String connString2 = "jdbc:mysql://localhost:3306/eason?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        MysqlXADataSource datasource1 = getDatasource(connString1, "root", "root");
        MysqlXADataSource datasource2 = getDatasource(connString2, "root", "root");
        //获取xa事务1
        XAConnection xaConnection = datasource1.getXAConnection();
        XAResource xaResource = xaConnection.getXAResource();
        Connection connection = xaConnection.getConnection();
        Statement statement = connection.createStatement();
        //获取xa事务2
        XAConnection xaConnection2 = datasource2.getXAConnection();
        XAResource xaResource2 = xaConnection2.getXAResource();
        Connection connection2 = xaConnection2.getConnection();
        Statement statement2 = connection2.createStatement();


        Xid myXid = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
        Xid myXid2 = new MyXid(100, new byte[]{0x11}, new byte[]{0x12});

        xaResource.start(myXid, XAResource.TMNOFLAGS);
        statement.execute("UPDATE eason.mac_vod SET d_name  ='彼得的龙1' where d_id ='2'"); //-100
        xaResource.end(myXid, XAResource.TMSUCCESS);

        xaResource2.start(myXid2, XAResource.TMNOFLAGS);
        statement2.execute("UPDATE eason.mac_vod SET d_directed  ='大卫·洛维1' where d_id ='2'");//+100
        xaResource2.end(myXid2, XAResource.TMSUCCESS);

        int ret2 = xaResource2.prepare(myXid2);
        int ret = xaResource.prepare(myXid);

        if (ret == XAResource.XA_OK && ret2 == XAResource.XA_OK) {
            xaResource.commit(myXid, false);
            xaResource2.commit(myXid2, false);
        }
    }
}