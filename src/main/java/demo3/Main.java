package demo3;

import java.sql.Connection;
import java.util.Scanner;

/**
 * @author ADACHI
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/mytest?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "123456";
        Connection connection = Utils.getConnection(url, userName, password);
        int userId = LogIn.check(connection);
        System.out.println("登录成功");
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
                    return;
                default:
                    break;
            }
        }
    }
}
