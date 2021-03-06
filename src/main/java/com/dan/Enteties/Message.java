package com.dan.Enteties;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    Long message_id;
    Integer user_id_who_write;
    Integer user_id_to_whom_write;
    String message_text;
    LocalDate message_data;
    Time message_time;

    public Message(Long message_id, Integer user_id_who_write, Integer user_id_to_whom_write, String message_text, LocalDate message_data, Time message_time) {
        this.message_id = message_id;
        this.user_id_who_write = user_id_who_write;
        this.user_id_to_whom_write = user_id_to_whom_write;
        this.message_text = message_text;
        this.message_data = message_data;
        this.message_time = message_time;
    }
    public Message(Long message_id, Integer user_id_who_write, Integer user_id_to_whom_write, String message_text, LocalDate message_data) {
        this.message_id = message_id;
        this.user_id_who_write = user_id_who_write;
        this.user_id_to_whom_write = user_id_to_whom_write;
        this.message_text = message_text;
        this.message_data = message_data;
    }
    public Message( Integer user_id_who_write, Integer user_id_to_whom_write, String message_text, LocalDate message_data) {
        this.user_id_who_write = user_id_who_write;
        this.user_id_to_whom_write = user_id_to_whom_write;
        this.message_text = message_text;
        this.message_data = message_data;
    }

    public Time getMessage_time() {
        return message_time;
    }

    public void setMessage_time(Time message_time) {
        this.message_time = message_time;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public Integer getUser_id_who_write() {
        return user_id_who_write;
    }

    public Integer getUser_id_to_whom_write() {
        return user_id_to_whom_write;
    }

    public String getMessage_text() {
        return message_text;
    }

    public LocalDate getMessage_data() {
        return message_data;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public void setUser_id_who_write(Integer user_id_who_write) {
        this.user_id_who_write = user_id_who_write;
    }

    public void setUser_id_to_whom_write(Integer user_id_to_whom_write) {
        this.user_id_to_whom_write = user_id_to_whom_write;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public void setMessage_data(LocalDate message_data) {
        this.message_data = message_data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", user_id_who_write=" + user_id_who_write +
                ", user_id_to_whom_write=" + user_id_to_whom_write +
                ", message_text='" + message_text + '\'' +
                ", message_data=" + message_data +
                ", message_time=" + message_time +
                '}';
    }
}
