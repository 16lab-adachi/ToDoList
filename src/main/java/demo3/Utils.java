package demo3;

import java.sql.*;

public final class Utils {
    /**
     *
     * @param url 连接路径
     * @param userName 用户名
     * @param password 密码
     * @return 返回连接
     */
    public static Connection getConnection(String url,String userName,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, userName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param connection 连接
     * @param sql SQL语句
     * @return 返回SQL语句改变的行数
     * @throws Exception 抛出异常
     */
    public static int executeUpdate(Connection connection,String sql) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    /**
     *
     * @param connection 连接
     * @param sql SQL语句
     * @return 返回查询语句对应的ResultSet
     * @throws Exception 抛出异常
     */
    public static ResultSet executeQuery(Connection connection, String sql) throws Exception{
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

}
