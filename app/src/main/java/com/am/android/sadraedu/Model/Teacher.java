package com.am.android.sadraedu.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Teacher  implements Serializable {
    private int id;
    private String name;
    private String father_name , birth_place , spouse_no , address , national_code;

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getSpouse_no() {
        return spouse_no;
    }

    public void setSpouse_no(String spouse_no) {
        this.spouse_no = spouse_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNational_code() {
        return national_code;
    }

    public void setNational_code(String national_code) {
        this.national_code = national_code;
    }

    @SerializedName("education_degree")
    private String degree;

    @SerializedName("education_major")
    private String major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
