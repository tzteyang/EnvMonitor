package com.envmonitor.Dao;

import com.envmonitor.Entity.Province;
import com.envmonitor.Utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceDao {

    // DBCP池管理的数据库连接
    private DataSource dataSource = DBCPUtils.getDatasource();

    private QueryRunner qr = new QueryRunner(dataSource);

//    private String AQI2AQL(int AQI) {
//
//        if (AQI >= 0 && AQI <= 50) {
//            return "优";
//        } else if (AQI >= 51 && AQI <= 100) {
//            return "良";
//        } else if (AQI >= 101 && AQI <= 150) {
//            return "轻度污染";
//        } else if (AQI >= 151 && AQI <= 200) {
//            return "中度污染";
//        } else if (AQI >= 201 && AQI <= 300) {
//            return "重度污染";
//        } else {
//            return "严重污染";
//        }
//    }
//
//    private String AQI2AQC(int AQI) {
//
//        if (AQI >= 0 && AQI <= 50) {
//            return "绿色";
//        } else if (AQI >= 51 && AQI <= 100) {
//            return "黄色";
//        } else if (AQI >= 101 && AQI <= 150) {
//            return "橙色";
//        } else if (AQI >= 151 && AQI <= 200) {
//            return "红色";
//        } else if (AQI >= 201 && AQI <= 300) {
//            return "紫红色";
//        } else {
//            return "褐红色";
//        }
//    }

    /**
     * 向数据库内写入Province对象
     * @param province
     * @return
     */
    public int addProvince(Province province) {

        int affected_rows = 0;

        String sql = "insert into Province (id, name, AQI, AQL, AQC)" +
                " values (?, ?, ?, ?, ?)";

        Object[] params = {province.getId(), province.getName(), province.getAqi(), province.getAql(), province.getAqc()};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    /**
     * 根据提供的省份id 删除对应省份的记录
     * @param name
     * @return
     */
    public int deleteProvince(int id) {

        int affected_rows = 0;

        String sql = "delete from Province where id=?";

        Object[] params = {id};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    /**
     * 根据传入的Province对象修改其在库中对应的记录
     * @param province
     * @return
     */
    public int updateProvince(Province province) {

        int affected_rows = 0;

        String sql = "update Province set name=?, AQI=?, AQL=?, AQC=? where id=?";

        Object[] params = {province.getName(), province.getAqi(), province.getAql(), province.getAqc(), province.getId()};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    /**
     * 根据用户键入的id查询对应省份
     * @param name
     * @return
     */
    public Province selectProvince(int id) {

        Province result = null;

        String sql = "select * from Province where id=?";

        Object[] params = {id};

        try {
            result = qr.query(sql, new BeanHandler<Province>(Province.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询出所有的省份的记录
     * @return
     */
    public ArrayList<Province> selectAllProvince() {

        ArrayList<Province> result = null;

        String sql = "select * from Province";

        try {
            result = (ArrayList<Province>) qr.query(sql, new BeanListHandler<Province>(Province.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int getIdByName(String name) {

        int id = 0;

        String sql = "select id from Province where name=?";

        Object[] params = {name};

        try {
            id = Integer.parseInt(qr.query(sql, new ArrayHandler(), params).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
