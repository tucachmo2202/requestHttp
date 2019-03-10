import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class Database {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public ArrayList<String> readUrl() throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        try {

            //Goi toi Driver ket noi Mysql
            Class.forName("com.mysql.jdbc.Driver");

            //Thiet lap ket noi toi DB
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");

            //Thuc thi cau truy van
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select url from table_1");

            //Lay du lieu tu truy van
            while (resultSet.next()){
                String url  = resultSet.getString("url");
                list.add(url);
            }
            resultSet.close();
            statement.close();
            connect.close();
        }
        catch (SQLException se){
            //Xu li cac loi cua JDBC
            se.printStackTrace();
        }
        catch (Exception e){
            //Xu ly cac loi cua Class.forName
            e.printStackTrace();
        }
        finally {
            //dong cac resource
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }
            try{
                if(connect!=null)
                    connect.close();
            }catch(SQLException se){
                se.printStackTrace();
            }// Ket thuc khoi finally

        }
        return list;
    }
    //Ham them du lieu vao database:
    public void updateDatabases(String url, java.sql.Date date, int time, int http_code) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");

            statement = connect.createStatement();
            //int a = statement.executeUpdate("update table_1 set time = "+ date + ", time_response ="+ time +", http_response_code =" + http_code + " where url =" + url );

            preparedStatement = connect.prepareStatement("update table_1 set time = ?, time_response =?, http_response_code =? where url =?");
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, time);
            preparedStatement.setInt(3, http_code);
            preparedStatement.setString(4, url);
            preparedStatement.executeUpdate();

            preparedStatement.close();

            statement.close();
            connect.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //dong cac resource
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }
            try{
                if(connect!=null)
                    connect.close();
            }catch(SQLException se){
                se.printStackTrace();
            }// Ket thuc khoi finally

        }
    }
}
