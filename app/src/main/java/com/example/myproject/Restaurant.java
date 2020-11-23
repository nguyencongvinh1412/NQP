package com.example.myproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity

public class Restaurant implements Serializable {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("picture")
    @ColumnInfo(name = "picture")
    private String picture;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

    @SerializedName("Rating")
    @ColumnInfo(name = "rating")
    private String rating;

    @SerializedName("menu")
    @ColumnInfo(name = "menu")
    private String menu;

    @SerializedName("Contact")
    @ColumnInfo(name = "contact")
    private String contact;

    @SerializedName("Opened")
    @ColumnInfo(name = "opened")
    private String open;

    @SerializedName("Closed")
    @ColumnInfo(name = "closed")
    private String close;

    @SerializedName("Comment")
    @ColumnInfo(name = "comment")
    private String comment;

    // show menu
    public int showMenu = 1;

    public int isShowMenu()
    {
        return  showMenu;
    }

    // thiết lập trạng thái hiển thị
    // nếu là 1 : hiển thị danh sách các nhà hàng
    // nếu là 2 : hiển thị danh sách các menu trong nhà hàng
    // nếu là 3 : hiển thị danh sách các comment trong nhà hàng
    public void setShowMenu(int showMenu)
    {
        this.showMenu = showMenu;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant(int id,String picture, String name, String address, String rating, String menu, String contact, String open, String close, String comment) {
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.menu = menu;
        this.contact = contact;
        this.open = open;
        this.close = close;
        this.comment = comment;
    }
}