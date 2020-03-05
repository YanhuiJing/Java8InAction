package mysqlAction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql操作实践
 *
 * @author gavin
 * @createDate 2020/3/5
 */
public class mysqlOperator {

    public static void main(String[] args) throws SQLException {

//        queryUserByAge(5);

    }

    public static void queryUserByAge(int age) throws SQLException {

        String sql = "select * from user where age > ?";

        Object[] params = new Object[]{age};

        MysqlPool mysqlPool = MysqlPool.getMysqlPool();

        mysqlPool.executeQuery(sql, params, new MysqlPool.QueryCallback() {
            @Override
            public void process(ResultSet rs) throws Exception {
                while(rs.next()){
                    System.out.println(rs.getInt(1)+"-"+
                                        rs.getString(2)+"-"+
                                        rs.getInt(3)+"-"+
                                        rs.getString(4));
                }
            }
        });

    }

    public static void insertUser (User user) throws SQLException {
//        User user = new User(1,"gavin",32,"man");

        String sql = "insert into user values(?,?,?,?)";
        Object[] params = new Object[]{
                user.getUserId(),
                user.getUserName(),
                user.getAge(),
                user.getSex()
        };

        MysqlPool mysqlPool = MysqlPool.getMysqlPool();

        mysqlPool.executeUpdate(sql,params);


    }


    /**
     * 批量插入session明细数据
     * @param users
     */
    public static void insertBatch(List<User> users) throws SQLException {

//        List<User> users = new ArrayList<>();
//        for(int i=2;i<10;i++){
//            users.add(new User(i,Integer.toString(i),i,Integer.toString(i)));
//        }

        String sql = "insert into user values(?,?,?,?)";

        List<Object[]> objects = new ArrayList<>();

        for(User user:users){

            Object[] objectsParam = new Object[]{
                    user.getUserId(),
                    user.getUserName(),
                    user.getAge(),
                    user.getSex()
            };

            objects.add(objectsParam);

        }

        MysqlPool mysqlPool = MysqlPool.getMysqlPool();

        mysqlPool.executeBatch(sql,objects);

    }


}


@Data
@NoArgsConstructor
@AllArgsConstructor
class User{

    private Integer userId;
    private String userName;
    private Integer age;
    private String sex;

}


class MysqlPool implements Serializable {

    private static MysqlPool mysqlPool = null;

    private ComboPooledDataSource comboPooledDataSource = null;

    private MysqlPool(){

        try {

            comboPooledDataSource = new ComboPooledDataSource(true);
            comboPooledDataSource.setJdbcUrl(ConfigManagerUtils.getProperty(Constant.JDBC_URL));
            comboPooledDataSource.setDriverClass(ConfigManagerUtils.getProperty(Constant.JDBC_DRIVER));
            comboPooledDataSource.setUser(ConfigManagerUtils.getProperty(Constant.JDBC_USER));
            comboPooledDataSource.setPassword(ConfigManagerUtils.getProperty(Constant.JDBC_PASSWORD));
            comboPooledDataSource.setMaxPoolSize(200);  //连接池最大连接数量
            comboPooledDataSource.setMinPoolSize(20);   //连接池最小连接数量
            comboPooledDataSource.setAcquireIncrement(5);   //每次递增数量
            comboPooledDataSource.setMaxStatements(180);    //连接池最大空闲时间

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

    }


    public Connection getConnection(){

        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 连接池单例
     * @return
     */
    public static MysqlPool getMysqlPool(){
        if(mysqlPool == null){
            synchronized (MysqlPool.class){
                if(mysqlPool == null){
                    mysqlPool = new MysqlPool();
                }
            }
        }

        return mysqlPool;
    }


    /**
     * 执行增删改SQL语句
     * @param sql
     * @param params
     * @return 影响的行数
     */
    public int executeUpdate(String sql, Object[] params) throws SQLException {
        int rtn = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql);

            if(params != null && params.length > 0) {
                for(int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            rtn = pstmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                conn.close();
            }
        }

        return rtn;
    }

    /**
     * 执行查询SQL语句
     * @param sql
     * @param params
     * @param callback
     */
    public void executeQuery(String sql, Object[] params,
                             QueryCallback callback) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            if(params != null && params.length > 0) {
                for(int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            rs = pstmt.executeQuery();

            callback.process(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 批量执行SQL语句
     *
     * 批量执行SQL语句，是JDBC中的一个高级功能
     * 默认情况下，每次执行一条SQL语句，就会通过网络连接，向MySQL发送一次请求
     *
     * 但是，如果在短时间内要执行多条结构完全一模一样的SQL，只是参数不同
     * 虽然使用PreparedStatement这种方式，可以只编译一次SQL，提高性能，但是，还是对于每次SQL
     * 都要向MySQL发送一次网络请求
     *
     * 可以通过批量执行SQL语句的功能优化这个性能
     * 一次性通过PreparedStatement发送多条SQL语句，比如100条、1000条，甚至上万条
     * 执行的时候，也仅仅编译一次就可以
     * 这种批量执行SQL语句的方式，可以大大提升性能
     *
     * @param sql
     * @param paramsList
     * @return 每条SQL语句影响的行数
     */
    public int[] executeBatch(String sql, List<Object[]> paramsList) throws SQLException {
        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // 第一步：使用Connection对象，取消自动提交
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql);

            // 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
            if(paramsList != null && paramsList.size() > 0) {
                for(Object[] params : paramsList) {
                    for(int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                    pstmt.addBatch();
                }
            }

            // 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
            rtn = pstmt.executeBatch();

            // 最后一步：使用Connection对象，提交批量的SQL语句
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                conn.close();
            }
        }

        return rtn;
    }

    /**
     * 静态内部类：查询回调接口
     * @author Administrator
     *
     */
    public static interface QueryCallback {

        /**
         * 处理查询结果
         * @param rs
         * @throws Exception
         */
        void process(ResultSet rs) throws Exception;

    }
}
