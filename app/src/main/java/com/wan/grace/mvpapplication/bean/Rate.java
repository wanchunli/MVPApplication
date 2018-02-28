package com.wan.grace.mvpapplication.bean;

import java.io.Serializable;

/**
 * Created by 开发部 on 2018/2/28.
 */

public class Rate implements Serializable{
    private int max;
    private float average;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
