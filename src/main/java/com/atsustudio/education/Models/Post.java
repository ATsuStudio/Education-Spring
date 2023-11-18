package com.atsustudio.education.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.awt.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    private String _title;
    private String _content;
    private String _author;
    private Time _created = new Time(System.currentTimeMillis());

    public void setId(Long Id){
        this._id = Id;
    }
    public void setTitle(String Title){
        this._title = Title;
    }
    public void setContent(String Content){
        this._content = Content;
    }
    public void setAuthor(String Author){
        this._author = Author;
    }
    public void setCreated(Time Created){
        this._created = Created;
    }



    public Long getId(){
        return this._id;
    }

    public String getTitle(){
        return this._title;
    }
    public String getContent(){
        return this._content;
    }
    public String getAuthor(){
        return this._author;
    }
    public Time getCreated(){
        return this._created;
    }


    public Post(){
    }
    public Post(String Title, String Content, String Author){
        this._title = Title;
        this._author = Author;
        this._content = Content;
    }


}
