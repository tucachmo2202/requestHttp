package http_database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class DatabaseConnecting {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DatabaseConnecting(String driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        ArrayList<String> db_config;
        db_config = Configuration.getConfiguration();
        connect = DriverManager.getConnection("jdbc:mysql://"+ db_config.get(3) + "/"+ db_config.get(0), db_config.get(1), db_config.get(2));
    }

    public ArrayList<String> readUrl() throws Exception{
        ArrayList<String> list = new ArrayList<String>();

        DatabaseConnecting s = new DatabaseConnecting("com.mysql.jdbc.Driver");
        try {

//            //Goi toi Driver ket noi Mysql
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //Thiet lap ket noi toi DB
//            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");

            //Thuc thi cau truy van
            s.statement = s.connect.createStatement();
            s.resultSet = s.statement.executeQuery("select url from table_1");

            //Lay du lieu tu truy van
            while (s.resultSet.next()){
                String url  = s.resultSet.getString("url");
                list.add(url);
            }
            s.resultSet.close();
            s.statement.close();
            s.connect.close();
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
                if(s.statement!=null)
                    s.statement.close();
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
    public void updateDatabases(String url, java.sql.Timestamp date, int time, int http_code) throws Exception {
        DatabaseConnecting s = new DatabaseConnecting("com.mysql.jdbc.Driver");
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Intern_1","root", "12345679");

            s.statement = s.connect.createStatement();

            s.preparedStatement = s.connect.prepareStatement("update table_1 set time = ?, time_response =?, http_response_code =? where url =?");
            s.preparedStatement.setTimestamp(1, date);
            s.preparedStatement.setInt(2, time);
            s.preparedStatement.setInt(3, http_code);
            s.preparedStatement.setString(4, url);
            s.preparedStatement.executeUpdate();

            s.preparedStatement.close();

            s.statement.close();
            s.connect.close();
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
                if(s.statement!=null)
                    s.statement.close();
            }catch(SQLException se2){
            }
            try{
                if(s.connect!=null)
                    s.connect.close();
            }catch(SQLException se){
                se.printStackTrace();
            }// Ket thuc khoi finally

        }
    }
}