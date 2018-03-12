package com.wan.grace.mvpapplication.bean;

import java.util.List;

/**
 * 天气数据实体类
 * WeatherData
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/8 9:34
 */
public class WeatherData {
    public String date;
    public String error;
    public List<Results> results;
    public String status;

    public class Results {
        public String currentCity;
        public List<Index> index;
        public String pm25;
        public List<Weather_data> weather_data;
    }

    public class Index {
        public String des;
        public String tipt;
        public String title;
        public String zs;
    }

    public class Weather_data {
        public String date;
        public String dayPictureUrl;
        public String nightPictureUrl;
        public String temperature;
        public String weather;
        public String wind;
    }
}
