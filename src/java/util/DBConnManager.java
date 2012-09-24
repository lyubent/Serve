package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

//@author lyubentodorov
public class DBConnManager
{
    static Connection rtnConn = null;

    public static Connection getConnection()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("serve");
        dataSource.setPassword("ac41004");
        dataSource.setServerName("arlia.computing.dundee.ac.uk");

        try
        {
            rtnConn = dataSource.getConnection();
        }
        catch(Exception ex){ex.printStackTrace();}

        return rtnConn;
    }
}