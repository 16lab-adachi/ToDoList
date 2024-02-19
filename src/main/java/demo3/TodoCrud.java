package demo3;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ADACHI
 */
public final class TodoCrud {
    /**
     *
     * @param connection 连接
     * @param userId 登录用户的用户id
     */
    public static void add(Connection connection, int userId) {
        //创建SQL语句并进行预编译,使用try-with-resource保证资源关闭
        String sql = "insert into todo(user_id,title,content,create_time,ddl) values(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = Utils.prepareStatement(connection, sql);) {
            //设置对应待办事项的用户id
            preparedStatement.setInt(1, userId);
            //获取标题内容(title)
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入标题内容:");
            preparedStatement.setString(2, scanner.nextLine());
            //获取待办事项内容(content)
            System.out.println("请输入待办事项内容:");
            preparedStatement.setString(3, scanner.nextLine());
            //获取截止时间(ddl),将用户输入的字符串转换为 LocalDateTime 对象
            System.out.println("请输入截止时间(格式：YYYY-MM-DD HH:mm:ss)");
            System.out.println("示例:2024-02-04 06:02:03");
            LocalDateTime ddl;
            //获取当前时间,检测截止时间是否早于创建时间
            LocalDateTime now;
            while (true) {
                ddl = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                now = LocalDateTime.now();
                //如果创建时间早于截止时间则写入对应创建时间和截止时间
                if (now.isBefore(ddl)) {
                    preparedStatement.setObject(4, now);
                    preparedStatement.setObject(5, ddl);
                    break;
                }
                //创建时间晚于截止时间则提示重新输入
                System.out.println("截止时间不能早于创建时间,请重新输入截止时间");
            }
            //完成写入
            preparedStatement.executeUpdate();
            System.out.println("增加待办事项成功");
        } catch (Exception e) {
            //提示出错
            System.out.println("操作失败");
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param connection 连接
     * @param userId 登录用户的用户id
     * @return 返回对应表中的数据对象封装的集合
     */
    public static List<ToDo> query(Connection connection, int userId){
        List<ToDo> list = new ArrayList<>();
        //创建SQL语句
        String sql = "select id,title,content,create_time,ddl from todo where user_id = " + userId;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            //如果查询结果为空
            if (!resultSet.next()) {
                System.out.println("您没有待办事项");
                return list;
            }
            //将每一个待办事项封装到对象ToDo中,而后将对象加入集合中
            do {
                ToDo todo = new ToDo();
                //设置id
                todo.setRealId(resultSet.getInt("id"));
                //设置userId
                todo.setUserId(userId);
                //设置title
                todo.setTitle(resultSet.getString("title"));
                //设置content
                todo.setContent(resultSet.getString("content"));
                //设置createTime
                todo.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
                //设置ddl
                todo.setDdl(resultSet.getTimestamp("ddl").toLocalDateTime());
                //将封装的一个数据对象添加到集合
                list.add(todo);
            } while (resultSet.next());
            //输出每个数据对象对应的id及内容
            for (int i = 0; i < list.size(); i++) {
                System.out.print("id: " + (i + 1) + " ");
                System.out.println(list.get(i));
            }
            return list;
        } catch (SQLException e) {
            //如果出错则提示操作失败
            System.out.println("操作失败");
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param connection 连接
     * @param userId 登录用户的id
     * @throws Exception 抛出异常
     */
    public static void modify(Connection connection, int userId) throws Exception {
        //调用查询方法,显示有多少待办事项,并返回封装的list
        List<ToDo> query = query(connection, userId);
        //提示输入要修改的id
        System.out.println("请输入要修改的待办事项的id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        //从list集合中获取对应的数据对象
        ToDo todo = query.get(id - 1);
        //提示输入要修改的内容
        System.out.println("请输入要修改的选项对应的数字: 1.title 2. content 3. createTime 4. ddl");
        int choose = scanner.nextInt();
        //提示输入修改后的内容
        System.out.println("请输入修改后的内容:(时间修改格式示例:2024-2-14 06:02:03)");
        //消耗掉换行符
        scanner.nextLine();
        //对对应的修改内容进行修改
        switch (choose) {
            case 1:
                String sql1 = "update todo set title = \"" + scanner.nextLine() + "\" where id = " + todo.getRealId();
                Utils.executeUpdate(connection, sql1);
                break;
            case 2:
                String sql2 = "update todo set content = \"" + scanner.nextLine() + "\" where id = " + todo.getRealId();
                Utils.executeUpdate(connection, sql2);
                break;
            case 3:
                String sql3 = "update todo set create_time = \"" + scanner.nextLine() + "\" where id = " + todo.getRealId();
                Utils.executeUpdate(connection, sql3);
                break;
            case 4:
                String sql4 = "update todo set ddl = \"" + scanner.nextLine() + "\" where id = " + todo.getRealId();
                Utils.executeUpdate(connection, sql4);
                break;
            default:
                System.out.println("无对应选项,请重新选择");
                break;
        }
        System.out.println("修改成功");
    }

    /**
     *
     * @param connection 连接
     * @param userId 登录用户的用户id
     * @throws Exception 抛出异常
     */
    public static void delete(Connection connection, int userId) throws Exception {
        //通过query方法获取表中的数据对象并封装到list集合
        List<ToDo> query = query(connection, userId);
        System.out.println("请输入要删除的待办事项的id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        //获取对应要删除的待办事项的id
        ToDo todo = query.get(id - 1);
        String sql = "delete from todo where id = " + todo.getRealId();
        Utils.executeUpdate(connection, sql);
        System.out.println("删除成功");
    }
}
