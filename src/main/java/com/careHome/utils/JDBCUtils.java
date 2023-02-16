package com.careHome.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource ds = null;

    static {
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pp = new Properties();
        try {
            pp.load(is);
            ds = DruidDataSourceFactory.createDataSource(pp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Statement getStatement(Connection con) {
        Statement sta = null;
        try {
            sta = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sta;
    }

    public static <T> List<T> selectData(String sql, Class<T> clazz, Object... params) {
        Connection con = getConnection();
        // 预编译
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                ps.setObject(i, params[i - 1]);
            }
            rs = ps.executeQuery();
            list = analyticalData(rs, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, ps, rs);
        }
        return list;
    }

    /**
     * 执行查询操作
     *
     * @param sql
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T> List<T> selectData(String sql, Class<T> clazz, Object id) {

        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        List<T> list = null;
        try {
            conn = getConnection();
            prst = conn.prepareStatement(sql);
            prst.setObject(1, id);
            rs = prst.executeQuery();
            System.out.println(rs);
            list = analyticalData(rs, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, prst, rs);
        }

        return list;

    }

    public static <T> List<T> selectData(String sql, Class<T> clazz) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        List<T> list = null;
        try {
            conn = getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            list = analyticalData(rs, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, stat, rs);
        }

        return list;

    }

    /**
     * 处理结果集
     *
     * @param rs
     * @param clazz
     * @return
     */
    public static <T> List<T> analyticalData(ResultSet rs, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        T item = null;
        String fieldName = null;
        Class fieldClass = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        try {
            while (rs.next()) {
                T e = clazz.newInstance();
                for (Field field : fields) {
                    fieldName = field.getName();
                    fieldClass = field.getType();
                    if (fieldClass == int.class || fieldClass == Integer.class) {
                        int fieldValue = rs.getInt(fieldName);
                        field.set(e, fieldValue);
                    } else if (fieldClass == String.class) {
                        String fieldValue = rs.getString(fieldName);
                        field.set(e, fieldValue);
                    } else if (fieldClass == Date.class) {
                        Date fieldValue = rs.getDate(fieldName);
                        field.set(e, fieldValue);
                    } else {
                        Object fieldValue = rs.getObject(fieldName);
                        field.set(e, fieldValue);
                    }

                }
                result.add(e);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    // 执行查询的方法
    public static ResultSet select(Statement sta, String sql) {
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int getInt(String sql) {
        Connection con = getConnection();
        Statement sta = getStatement(con);
        ResultSet rs = select(sta, sql);
        int result = -1;
        try {
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, sta, rs);
        }
        return result;
    }

    public static int getPreparedInt(String sql,Object... params) {
        Connection con = getConnection();
        PreparedStatement ps=null;
        ResultSet rs =null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                ps.setObject(i, params[i - 1]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int result = -1;
        try {
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, ps, rs);
        }
        return result;
    }


    /**
     * 修改数据库
     *
     * @param sql
     * @param params
     * @return
     */
    public static int updateData(String sql, Object... params) {

        Connection conn = null;
        PreparedStatement prst = null;
        int rowCount = 0;
        try {
            conn = getConnection();
            prst = conn.prepareStatement(sql);

            for (int i = 1; i <= params.length; i++) {
                prst.setObject(i, params[i - 1]);
            }

            rowCount = prst.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(conn, prst);
        }

        return rowCount;

    }

    /**
     * 插入数据库
     *
     * @param sql
     * @param params
     * @return
     */
    public static int insertData(String sql, Object... params) {
        return updateData(sql, params);
    }

    /**
     * 删除数据库
     *
     * @param sql
     * @param params
     * @return
     */
    public static int deleteData(String sql, Object... params) {
        return updateData(sql, params);
    }

    public static void close(Connection con, Statement sta, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, PreparedStatement sta, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, PreparedStatement sta) {
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, Statement sta) {
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
