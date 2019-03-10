
import java.sql.*;

public class JdbcMTSample extends Thread
{
    // Default no of threads to 10
    private static int NUM_OF_THREADS = 4;

    int m_myId;

    static  int c_nextId = 1;
    static  Connection s_conn = null;
    static  Connection conn = null;
    static  ResultSet rs   = null;
    static  Statement stmt = null;

    synchronized static int getNextId()
    {
        return c_nextId++;
    }

    public static void main (String args [])
    {
        try
        {
            /* Goi toi JDBC driver */
            Class.forName("com.mysql.jdbc.Driver");

            // tạo connect
            s_conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");
            stmt = s_conn.createStatement ();
            rs = stmt.executeQuery ("select url from table_1");
            // Khoi tao luong
            Thread[] threadList = new Thread[NUM_OF_THREADS];

            // Sinh luong
            for (int i = 0; i < NUM_OF_THREADS; i++)
            {
                threadList[i] = new JdbcMTSample();
                threadList[i].start();
            }

            for (int i = 0; i < NUM_OF_THREADS; i++)
            {
                threadList[i].join();
            }
            rs.close();
            rs = null;

            stmt.close();
            stmt = null;

            if (conn != null)
            {
                conn.close();
                conn = null;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public JdbcMTSample()
    {
        super();
        m_myId = getNextId();
    }

    public void run()
    {

        try
        {
                yield();

            synchronized (rs){
                if (rs.next());
                {
                    String url = rs.getString("url");
                    System.out.println("Thread " + m_myId +
                            " Employee Id : " + url);
                    GetRes r = new GetRes();
                    r.getResp(url);
                    yield();  // Yield To other threads
                }
                System.out.println("Thread " + m_myId +  " is finished. ");
            }

        }
        catch (Exception e)
        {
            System.out.println("Thread " + m_myId + " got Exception: " + e);
            e.printStackTrace();
            return;
        }
    }

}
