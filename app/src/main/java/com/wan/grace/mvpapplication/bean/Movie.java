package com.wan.grace.mvpapplication.bean;

import java.io.Serializable;

/**
 * Created by wanchun on 18/02/28.
 */

public class Movie implements Serializable{
    private Rate rating;
    private String title;
    private String collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private MovieImage images;

    public Rate getRating() {
        return rating;
    }

    public void setRating(Rate rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public MovieImage getImages() {
        return images;
    }

    public void setImages(MovieImage images) {
        this.images = images;
    }

//    public static class Rate {
//        private int max;
//        private float average;
//        private String stars;
//        private int min;
//
//        public int getMax() {
//            return max;
//        }
//
//        public void setMax(int max) {
//            this.max = max;
//        }
//
//        public float getAverage() {
//            return average;
//        }
//
//        public void setAverage(float average) {
//            this.average = average;
//        }
//
//        public String getStars() {
//            return stars;
//        }
//
//        public void setStars(String stars) {
//            this.stars = stars;
//        }
//
//        public int getMin() {
//            return min;
//        }
//
//        public void setMin(int min) {
//            this.min = min;
//        }
//    }

//    public static class MovieImage {
//        private String small;
//        private String large;
//        private String medium;
//
//        public String getSmall() {
//            return small;
//        }
//
//        public void setSmall(String small) {
//            this.small = small;
//        }
//
//        public String getLarge() {
//            return large;
//        }
//
//        public void setLarge(String large) {
//            this.large = large;
//        }
//
//        public String getMedium() {
//            return medium;
//        }
//
//        public void setMedium(String medium) {
//            this.medium = medium;
//        }
//    }
}
