package com.example.demo.models;

import com.example.demo.Validation.GoodMovie;
import com.example.demo.Validation.Priority;
import com.example.demo.Validation.Rating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@GoodMovie
public class WatchlistItem {


    private Integer id;

    @NotBlank( message="Please enter the title")
    private String title;
    @Rating
    private String rating;
    @Priority
    private String priority;

    @Size(max=50,  message="Comment should be maximum 50 characters")
    private String comment;

    public Date getWatchingDate() {
        return watchingDate;
    }

    public void setWatchingDate(Date watchingDate) {
        this.watchingDate = watchingDate;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        WatchlistItem.index = index;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Value("#{new java.util.Date()}")
    private Date watchingDate;

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    private  String filmType;
    public static int index = 0;

    public WatchlistItem() {
        this.id = index ++;
        this.watchingDate=	new java.util.Date();
    }

    public WatchlistItem(String title, String rating, String priority, String comment) {
        super();
        this.id = index ++;
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
    }
    public WatchlistItem(Date watchingDate) {
        super();
        this.id = index ++;
        this.watchingDate=watchingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        }

}
