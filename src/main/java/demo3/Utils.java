package demo3;

import java.sql.*;

/**
 * @author ADACHI
 */
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
        int i = statement.executeUpdate(sql);
        statement.close();
        return i;
    }

    /**
     *
     * @param connection 连接
     * @param sql 要执行的SQL语句
     * @return 返回带有参数的预编译PreparedStatement
     * @throws Exception 抛出异常
     */
    public static PreparedStatement prepareStatement(Connection connection, String sql) throws Exception{
        return connection.prepareStatement(sql);
    }

}
