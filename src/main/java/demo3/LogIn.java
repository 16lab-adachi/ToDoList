package demo3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author ADACHI
 */
public final class LogIn {
    /**
     *
     * @param connection 连接
     * @return 返回对应用户的用户id
     * @throws Exception 抛出异常
     */
    public static int check(Connection connection) throws Exception {
        //创建对应SQL语句并进行预编译PreparedStatement设置
        String sql = "select id from user where user_name = ? and password = ?";
        try (PreparedStatement preparedStatement = Utils.prepareStatement(connection, sql);) {
            //提示输入账号密码
            while (true) {
                System.out.println("请输入账号");
                preparedStatement.setString(1, new Scanner(System.in).nextLine());
                System.out.println("请输入密码");
                preparedStatement.setString(2, new Scanner(System.in).nextLine());
                //在表中查询,如果查询不到对应用户则提示重新输入账号密码
                try(ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (!resultSet.next()) {
                        System.out.println("不存在该用户,请您重新输入账号和密码");
                        continue;
                    }
                    System.out.println("登录成功");
                    return resultSet.getInt("id");
                } catch (SQLException e) {
                    System.out.println("操作失败");
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            System.out.println("操作失败");
            throw new RuntimeException(e);
        }
    }
}
