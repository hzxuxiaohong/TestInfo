package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.GetConfig;



public class DBUtil {
	 private static GetConfig getConfig = new GetConfig();
	    private static String driverClassName = "com.mysql.jdbc.Driver";
	    private static String url = getConfig.getPara("db.properties", "db_url");
	    private static String user = getConfig.getPara("db.properties", "username");
	    private static String password = getConfig.getPara("db.properties", "password");
	    public static Connection getConnection() throws SQLException {
	        Connection con = null;
	        try {
	            Class.forName(driverClassName);
	            con = DriverManager.getConnection(url, user, password);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return con;
	    }


	    public static List<Map<String, Object>> executeQuery(String sql) throws Exception {

	        if (sql.equals("")) {
	            throw new IllegalArgumentException("Query Param Illegal !");
	        }
	        Connection con = getConnection();
	        ResultSet rs = null;
	        Statement statement = con.createStatement();
	        try {
	            rs = statement.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int numberOfColumns = rsmd.getColumnCount();
	            int count = 0;
	            String key;
	            Object value;
	            List<Map<String, Object>> rltList = new ArrayList<Map<String, Object>>();
//	    		遍历数据库查询结果数据集
	            while (rs.next()) {
	                Map<String, Object> expectData = new HashMap<String, Object>();
	                count = 0;
	                while (count++ < numberOfColumns) {

	                    key = rsmd.getColumnLabel(count);
	                    value = rs.getObject(key);
	                    expectData.put(key, value);
	                }
	                rltList.add(expectData);
	            }

	            return rltList;
	        } catch (Exception e) {
	            throw e;
	        } finally {
	            if (null != con) {
	                con.close();
	            }
	            if (null != statement) {
	                statement.close();
	            }
	        }


	    }

	    public static List<Map<String, Object>> executeQuery(String table, String condition) throws Exception {

	        if (table.equals("") || condition.equals("")) {
	            throw new IllegalArgumentException("Query Param Illegal !");
	        }
	        Connection con = getConnection();
	        ResultSet rs = null;
	        Statement statement = con.createStatement();
	        try {

	            String sql = "SELECT * FROM " + table + " WHERE " + condition;
	            rs = statement.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int numberOfColumns = rsmd.getColumnCount();
	            int count = 0;
	            String key;
	            Object value;
	            List<Map<String, Object>> rltList = new ArrayList<Map<String, Object>>();
//	    		遍历数据库查询结果数据集
	            while (rs.next()) {
	                Map<String, Object> expectData = new HashMap<String, Object>();
	                count = 0;
	                while (count++ < numberOfColumns) {

	                    key = rsmd.getColumnLabel(count);
	                    value = rs.getObject(key);
	                    expectData.put(key, value);
	                }
	                rltList.add(expectData);
	            }

	            return rltList;
	        } catch (Exception e) {
	            throw e;
	        } finally {
	            if (null != con) {
	                con.close();
	            }
	            if (null != statement) {
	                statement.close();
	            }
	        }


	    }
	    public static int InsertOrDel(String sql)throws Exception{
	        if(sql.equals("")){
	            throw new IllegalArgumentException("Query Param Illegal !");
	        }
	        Connection con=null;
	        Statement statement=null;
	        try{
	            con=getConnection();
	            statement=con.createStatement();
	            int res= statement.executeUpdate(sql);
	            return  res;
	        }catch (Exception e){
	            throw e;
	        }finally {
	            try{
	                if(null!=con){
	                    con.close();
	                }
	            }catch (SQLException se){
	                se.printStackTrace();
	            }finally {
	                if(null!=statement){
	                    statement.close();
	                }
	            }
	        }
	    }
	    public static int executeUpdate(String sql)throws Exception{
	        if(sql.equals("")){
	            throw new IllegalArgumentException("SQL statement should not be empty!");
	        }
	        Connection con=null;
	        Statement statement=null;
	        try{
	            con=getConnection();
	            statement=con.createStatement();
	            int res=statement.executeUpdate(sql);
	            return res;
	        }catch (Exception e){
	            throw e;
	        }finally {
	            try{
	                if(null!=con){
	                    con.close();
	                }
	            }catch (SQLException se){
	                se.printStackTrace();
	            }finally {
	                if(null!=statement){
	                    statement.close();
	                }
	            }
	        }
	    }

	}
