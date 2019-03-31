package http_database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;

class MultithreadJdbc extends Thread{
    private Connection s_conn = null;
    public static ResultSet rs = null;
    private Statement stmt = null;

    public Connection getS_conn() {
        return s_conn;
    }

    public void setS_conn(Connection s_conn) {
        this.s_conn = s_conn;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    private final Log log = LogFactory.getLog(JdbcMt.class);

    public MultithreadJdbc(String driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        ArrayList<String> db_config;
        db_config = Configuration.getConfiguration();
        s_conn = DriverManager.getConnection("jdbc:mysql://"+ db_config.get(3) + "/"+ db_config.get(0), db_config.get(1), db_config.get(2));
        stmt = s_conn.createStatement ();
        //rs = stmt.executeQuery ("select url from table_1");
    }

    public MultithreadJdbc(ResultSet rs){
        this.setRs(rs);
    }

    public void run(){
        try {
            yield();


            synchronized (this.getRs()){
                if (this.getRs().next()) {
                    String url = this.getRs().getString("url");
                    System.out.println("Thread " +
                            " URL : " + url);
                    ResponseHttp r = new ResponseHttp();
                    r.getResp(url);
                    log.info("done for " +url);
                    yield();
                }
                System.out.println("Thread " +  " is finished. ");
            }

        }
        catch (Exception e) {
            System.out.println("Thread " + " got Exception: " + e);
            e.printStackTrace();
            return;
        }
    }
}

public class JdbcMt{
//    //synchronized static int getNextId()
//    {
//        return c_nextId++;
//    }
    public static int NUM_OF_THREADS = 4;


    public static void main (String args []) {
        try {
            /* Goi toi JDBC driver */
            //Class.forName("com.mysql.jdbc.Driver");

            // tạo connect
//            s_conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");
//            stmt = s_conn.createStatement ();
//            rs = stmt.executeQuery ("select url from table_1");

            MultithreadJdbc s = new MultithreadJdbc("com.mysql.jdbc.Driver");

            s.setStmt(s.getS_conn().createStatement());
            s.setRs(s.getStmt().executeQuery("select url from table_1"));
            // Khoi tao luong
            Thread[] threadList = new Thread[NUM_OF_THREADS];
            // Sinh luong
            for (int i = 0; i < NUM_OF_THREADS; i++) {
                threadList[i] = new MultithreadJdbc(s.getRs());
                threadList[i].start();
            }

            for (int i = 0; i < NUM_OF_THREADS; i++) {
                threadList[i].join();
            }
            s.getRs().close();
            s.setRs(null);

            s.getStmt().close();
            s.setStmt(null);

            if (s.getS_conn() != null) {
                s.getS_conn().close();
                s.setS_conn(null);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public JdbcMt() {
//        super();
//        m_myId = getNextId();
//    }

//    public void run() {
//
//        try {
//            yield();
//
//            synchronized (rs){
//                if (rs.next()) {
//                    String url = rs.getString("url");
//                    System.out.println("Thread " +
//                            " URL : " + url);
//                    ResponseHttp r = new ResponseHttp();
//                    r.getResp(url);
//                    log.info("done for " +url);
//                    yield();
//                }
//                System.out.println("Thread " +  " is finished. ");
//            }
//
//        }
//        catch (Exception e) {
//            System.out.println("Thread " + " got Exception: " + e);
//            e.printStackTrace();
//            return;
//        }
//    }

}
