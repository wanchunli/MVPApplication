package com.wan.grace.mvpapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wanchun on 16/11/9.
 */

public class MovieSubject implements Serializable{
   private int count;
   private int start;
   private int total;
   private List<Movie> subjects;
   private String title;

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getStart() {
      return start;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public List<Movie> getSubjects() {
      return subjects;
   }

   public void setSubjects(List<Movie> subjects) {
      this.subjects = subjects;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
