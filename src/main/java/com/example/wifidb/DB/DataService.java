package com.example.wifidb.DB;

import com.example.wifidb.model.BookMark;
import com.example.wifidb.model.BookMarkGroup;
import com.example.wifidb.model.LocHty;
import com.example.wifidb.model.WifiInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    public List<WifiInfo> dbSearch(float a, float b) {
        List<WifiInfo> wifiInfoList = new ArrayList<>();

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

            String sql = " SELECT *, haversine("+ a +", "+b +", x, y) AS distance " +
                    " FROM wifi_infomation " +
                    " ORDER BY distance " +
                    " LIMIT 20; ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setDistance(rs.getFloat("distance"));
                wifiInfo.setWifino(rs.getString("wifino"));
                wifiInfo.setGu(rs.getString("gu"));
                wifiInfo.setWifinm(rs.getString("wifinm"));
                wifiInfo.setAdres1(rs.getString("adres1"));
                wifiInfo.setAdres2(rs.getString("adres2"));
                wifiInfo.setFloor(rs.getString("floor"));
                wifiInfo.setInstl_ty(rs.getString("instl_ty"));
                wifiInfo.setInstl_mby(rs.getString("instl_mby"));
                wifiInfo.setSvc_se(rs.getString("svc_se"));
                wifiInfo.setWifi_cmcwr(rs.getString("wifi_cmcwr"));
                wifiInfo.setInstl_year(rs.getString("instl_year"));
                wifiInfo.setInout_door(rs.getString("inout_door"));
                wifiInfo.setWifi_remars(rs.getString("wifi_remars"));
                wifiInfo.setX(rs.getFloat("x"));
                wifiInfo.setY(rs.getFloat("y"));
                wifiInfo.setWork_date(rs.getString("work_date"));
                wifiInfoList.add(wifiInfo);
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
        return wifiInfoList;
    }

    public void dbInsert(List<WifiInfo> wifiInfoList) {

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

            String sql = " insert into wifi_infomation (wifino, gu, wifinm, adres1, adres2, floor, instl_ty," +
                    " instl_mby, svc_se, wifi_cmcwr, instl_year, inout_door, wifi_remars, x, y, work_date) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
            preparedStatement = connection.prepareStatement(sql);

            int cnt = 0;
            for(WifiInfo wifiInfo : wifiInfoList){
                ++cnt;
                preparedStatement.setString(1,wifiInfo.getWifino());
                preparedStatement.setString(2,wifiInfo.getGu());
                preparedStatement.setString(3,wifiInfo.getWifinm());
                preparedStatement.setString(4,wifiInfo.getAdres1());
                preparedStatement.setString(5,wifiInfo.getAdres2());
                preparedStatement.setString(6,wifiInfo.getFloor());
                preparedStatement.setString(7,wifiInfo.getInstl_ty());
                preparedStatement.setString(8,wifiInfo.getInstl_mby());
                preparedStatement.setString(9,wifiInfo.getSvc_se());
                preparedStatement.setString(10,wifiInfo.getWifi_cmcwr());
                preparedStatement.setString(11,wifiInfo.getInstl_year());
                preparedStatement.setString(12,wifiInfo.getInout_door());
                preparedStatement.setString(13,wifiInfo.getWifi_remars());
                preparedStatement.setFloat(14,Float.valueOf(wifiInfo.getX()));
                preparedStatement.setFloat(15,Float.valueOf(wifiInfo.getY()));
                preparedStatement.setString(16,wifiInfo.getWork_date());

                preparedStatement.addBatch();
                preparedStatement.clearParameters();

                if ((cnt % 100) == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    connection.commit();
                }
            }
            preparedStatement.executeBatch();
            connection.commit();

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

    public List<LocHty> historySearch() {
        List<LocHty> locHtyList = new ArrayList<>();
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

            String sql = " SELECT * " +
                    " FROM history " +
                    " ORDER BY ID  desc" +
                    " LIMIT 20; ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){
                LocHty locHty = new LocHty();
                locHty.setId(rs.getInt("ID"));
                locHty.setX(rs.getFloat("x"));
                locHty.setY(rs.getFloat("y"));
                locHty.setDate(rs.getString("search_date"));
                locHtyList.add(locHty);
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
        return locHtyList;
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

    public WifiInfo getWifiDetail(String wifino) {
        WifiInfo wifiInfo = null;
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

            String sql = " SELECT * " +
                    " FROM wifi_infomation " +
                    " where wifino = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifino);

            rs = preparedStatement.executeQuery();

            if(rs.next()){
                wifiInfo = new WifiInfo();
                wifiInfo.setWifino(rs.getString("wifino"));
                wifiInfo.setGu(rs.getString("gu"));
                wifiInfo.setWifinm(rs.getString("wifinm"));
                wifiInfo.setAdres1(rs.getString("adres1"));
                wifiInfo.setAdres2(rs.getString("adres2"));
                wifiInfo.setFloor(rs.getString("floor"));
                wifiInfo.setInstl_ty(rs.getString("instl_ty"));
                wifiInfo.setInstl_mby(rs.getString("instl_mby"));
                wifiInfo.setSvc_se(rs.getString("svc_se"));
                wifiInfo.setWifi_cmcwr(rs.getString("wifi_cmcwr"));
                wifiInfo.setInstl_year(rs.getString("instl_year"));
                wifiInfo.setInout_door(rs.getString("inout_door"));
                wifiInfo.setWifi_remars(rs.getString("wifi_remars"));
                wifiInfo.setX(rs.getFloat("x"));
                wifiInfo.setY(rs.getFloat("y"));
                wifiInfo.setWork_date(rs.getString("work_date"));
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
        return wifiInfo;
    }

    public List<BookMarkGroup> SearchBmg(){
        List<BookMarkGroup> bookMarkGroupList = new ArrayList<>();
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

            String sql = " SELECT * " +
                    " FROM bookmark_group ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){
                BookMarkGroup bookMarkGroup = new BookMarkGroup();
                bookMarkGroup.setId(rs.getInt("ID"));
                bookMarkGroup.setBMGNmae(rs.getString("BookNm"));
                bookMarkGroup.setOrd(rs.getInt("Orders"));
                bookMarkGroup.setRdate(rs.getString("Rdate"));
                if(rs.getString("Mdate")!=null){
                    bookMarkGroup.setMdate(rs.getString("Mdate"));
                }else{
                    bookMarkGroup.setMdate("");
                }
                bookMarkGroupList.add(bookMarkGroup);
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
        return bookMarkGroupList;
    }

    public void BookMarkGroupInsert(String bmgName, int ord){

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

            String sql = "insert into study_db.bookmark_group(BookNM, Orders,Rdate) values(?,?,NOW())";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bmgName);
            preparedStatement.setInt(2,ord);

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

    public void ModBmg(String bmgName, int ord, int bmgId){

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

            String sql = "update study_db.bookmark_group set BookNm = ?, Orders =?, Mdate=Now() where ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bmgName);
            preparedStatement.setInt(2,ord);
            preparedStatement.setInt(3,bmgId);

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

    public void DelteBmg(int bmgId){

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

            String sql = "delete from study_db.bookmark_group  where ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bmgId);

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

    public void BkInsert(String wifinm, String bmgNm){

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

            /*"insert into study_db.history(x, y, search_date) values(?,?,NOW())";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1,locHty.getX());
            preparedStatement.setFloat(2,locHty.getY());*/

            String sql = "insert into study_db.bookmark(Wifinm,BmgNm,Rdate) values(?,?,NOW())";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,wifinm);
            preparedStatement.setString(2,bmgNm);

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

    public void BkDelete(int bmgId){

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

            String sql = "delete from study_db.bookmark_group  where ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bmgId);

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

    public List<BookMark> SearchBookMark(){
        List<BookMark> bookMarkList = new ArrayList<>();
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

            String sql = " SELECT * " +
                    " FROM bookmark ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){
                BookMark bookMark = new BookMark();
                bookMark.setId(rs.getInt("BookId"));
                bookMark.setBmgNm(rs.getString("BmgNm"));
                bookMark.setWifiNm(rs.getString("Wifinm"));
                bookMark.setRdate(rs.getString("Rdate"));
                bookMarkList.add(bookMark);
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
        return bookMarkList;
    }

    public List<String> BmgNm(){
        List<String> BmgNmlist = new ArrayList<>();
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

            String sql = " SELECT BookNm " +
                    " FROM bookmark_group ";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery(sql);

            while (rs.next()){
                BmgNmlist.add(rs.getString("BookNm"));
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
        return BmgNmlist;
    }


}
