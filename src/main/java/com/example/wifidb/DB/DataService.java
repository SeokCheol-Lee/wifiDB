package com.example.wifidb.DB;

import com.example.wifidb.model.LocHty;
import com.example.wifidb.model.WifiInfo;
import java.sql.*;

public class DataService {
    public void dbSearch() {

        String url = "jdbc:mariadb://localhost:3306/study_db";
        String user_name = "root";
        String password = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,user_name,password);

            String sql = " select * from wifi_infomation; ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement!= null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void dbInsert(WifiInfo wifiInfo) {

        String url = "jdbc:mariadb://localhost:3306/study_db";
        String user_name = "root";
        String password = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,user_name,password);

            String sql = " insert into wifi_infomation (distance, wifino, gu, wifinm, adres1, adres2, floor, instl_ty," +
                    " instl_mby, svc_se, wifi_cmcwr, instl_year, inout_door, wifi_remars, x, y, work_date) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1,wifiInfo.getDistance());
            preparedStatement.setString(2,wifiInfo.getWifino());
            preparedStatement.setString(3,wifiInfo.getGu());
            preparedStatement.setString(4,wifiInfo.getWifinm());
            preparedStatement.setString(5,wifiInfo.getAdres1());
            preparedStatement.setString(6,wifiInfo.getAdres2());
            preparedStatement.setString(7,wifiInfo.getFloor());
            preparedStatement.setString(8,wifiInfo.getInstl_ty());
            preparedStatement.setString(9,wifiInfo.getInstl_mby());
            preparedStatement.setString(10,wifiInfo.getSvc_se());
            preparedStatement.setString(11,wifiInfo.getWifi_cmcwr());
            preparedStatement.setInt(12,wifiInfo.getInstl_year());
            preparedStatement.setString(13,wifiInfo.getInout_door());
            preparedStatement.setString(14,wifiInfo.getWifi_remars());
            preparedStatement.setFloat(15,wifiInfo.getX());
            preparedStatement.setFloat(16,wifiInfo.getY());
            preparedStatement.setString(17,wifiInfo.getWork_date());

            int afffected = preparedStatement.executeUpdate();

            if(afffected > 0){
                //성공
            }else{
                //실패
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement!= null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void historySearch() {

        String url = "jdbc:mariadb://localhost:3306/study_db";
        String user_name = "root";
        String password = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,user_name,password);

            String sql = " select * from history; ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement!= null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void historyInsert(LocHty locHty){

        String url = "jdbc:mariadb://localhost:3306/study_db";
        String user_name = "root";
        String password = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,user_name,password);

            String sql = "insert into study_db.history(x, y, search_date) values(?,?,NOW())";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1,locHty.getX());
            preparedStatement.setFloat(2,locHty.getY());

            int afffected = preparedStatement.executeUpdate();

            if(afffected > 0){
                //성공
            }else{
                //실패
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement!= null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
