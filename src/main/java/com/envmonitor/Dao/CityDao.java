package com.envmonitor.Dao;

import com.envmonitor.Entity.City;
import com.envmonitor.Entity.Province;
import com.envmonitor.Utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDao {

    // DBCP池管理的数据库连接
    private DataSource dataSource = DBCPUtils.getDatasource();

    private QueryRunner qr = new QueryRunner(dataSource);


    public int addCity(City city) {

        int affected_rows = 0;

        String sql = "insert into City (id, province_id, name, AQI, AQL, AQC)" +
                " values (?, ?, ?, ?, ?, ?)";

        Object[] params = {city.getId(), city.getProvinceId(), city.getName(), city.getAqi(), city.getAql(), city.getAqc()};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }


    public int deleteCity(int id) {

        int affected_rows = 0;

        String sql = "delete from City where id=?";

        Object[] params = {id};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }


    public int updateCity(City city) {

        int affected_rows = 0;

        String sql = "update City set province_id=?, name=?, AQI=?, AQL=?, AQC=? where id=?";

        Object[] params = {city.getProvinceId(), city.getName(), city.getAqi(), city.getAql(), city.getAqc(), city.getId()};

        try {
            affected_rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }


    public Province selectCity(int id) {

        Province result = null;

        String sql = "select * from City where id=?";

        Object[] params = {id};

        try {
            result = qr.query(sql, new BeanHandler<Province>(Province.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public ArrayList<City> selectAllCity() {

        ArrayList<City> result = null;

        String sql = "select * from City";

        try {
            result = (ArrayList<City>) qr.query(sql, new BeanListHandler<City>(City.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int getIdByName(String name) {

        int id = 0;

        String sql = "select id from City where name=?";

        Object[] params = {name};

        try {
            id = Integer.parseInt(qr.query(sql, new ArrayHandler(), params).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
