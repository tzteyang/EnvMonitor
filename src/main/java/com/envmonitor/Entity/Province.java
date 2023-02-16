package com.envmonitor.Entity;


public class Province {

    private long id;
    private String name;
    private long aqi;
    private String aql;
    private String aqc;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getAqi() {
        return aqi;
    }

    public void setAqi(long aqi) {
        this.aqi = aqi;
    }


    public String getAql() {
        return aql;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }


    public String getAqc() {
        return aqc;
    }

    public void setAqc(String aqc) {
        this.aqc = aqc;
    }

}