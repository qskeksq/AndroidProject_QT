package com.example.administrator.memosqver20.Model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017-06-01.
 */

public class Memo {

    private String week;
    private String qt;
    private String thanks;
    private String prayer;
    private String journal;
    private Date date;
    private UUID uuid;

    // 모든 데이터 객체는 1.구분자  2.날짜가 자동으로 주어진다.
    public Memo(){
        uuid = UUID.randomUUID();
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public String getPrayer() {
        return prayer;
    }

    public void setPrayer(String prayer) {
        this.prayer = prayer;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
