package epm.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLITEConnection {
    private static Connection c=null;
    public static Connection getConnection(String dbUrl, String dbName)
    {
        try {
            if(c==null) {
                Class.forName("org.sqlite.JDBC");
               c = DriverManager.getConnection(dbUrl + dbName);
            }
            return  c;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
