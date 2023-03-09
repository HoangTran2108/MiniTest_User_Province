package com.example.user_province.model;

public class Province {
    int p_id;
    String p_name;

    public Province() {
    }

    public Province(int p_id, String p_name) {
        this.p_id = p_id;
        this.p_name = p_name;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
}
