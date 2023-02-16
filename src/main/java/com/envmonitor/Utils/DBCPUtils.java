package com.envmonitor.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DBCPUtils {
    public static DataSource datasource = null;

    static {
        // 新建一个配置文件对象
        Properties props = new Properties();
        try {
            // 通过类加载器找到文件路径，读配置文件
            InputStream in = DBCPUtils.class.getClassLoader()
                    .getResourceAsStream("dbcp.properties");
            // 把文件以输入流的形式加载到配置对象中
            props.load(in);
            // 创建数据源对象
            datasource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DataSource getDatasource() {
        return datasource;
    }

//    public static void main(String[] args) throws SQLException {
//
//        try {
//            QueryRunner qr = new QueryRunner(DBCPUtils.getDatasource());
//
//            String sql = "delete from Province";
//
//            int ans = qr.update(sql);
//
//            System.out.println(ans);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
