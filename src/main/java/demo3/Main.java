package demo3;

import java.sql.Connection;
import java.util.Scanner;

/**
 * @author ADACHI
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/mytest?useSSL=false&serverTimezone=UTC";
        String userName = "";
        String password = "";
        //开启连接
        Connection connection = Utils.getConnection(url, userName, password);
        //检查账号密码
        int userId = LogIn.check(connection);
        //读取用户需要进行的操作并调用对应的方法
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请选择你想要的执行的操作");
            System.out.println("1. 查询待办事项 2. 修改待办事项 3. 增加待办事项 4. 删除待办事项 5. 退出");
            switch (scanner.nextInt()){
                case 1:
                    TodoCrud.query(connection,userId);
                    break;
                case 2:
                    TodoCrud.modify(connection,userId);
                    break;
                case 3:
                    TodoCrud.add(connection,userId);
                    break;
                case 4:
                    TodoCrud.delete(connection,userId);
                    break;
                case 5:
                    //关闭连接
                    connection.close();
                    return;
                default:
                    break;
            }
        }
    }
}
