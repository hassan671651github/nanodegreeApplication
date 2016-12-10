package com.example.ahmed.teachercalender;

import java.util.Date;

/**
 * Created by Ahmed on 03/12/2016.
 */
public class CustomAbsence {




        private  String name;
        private boolean value;
        private Date date;
        private   int id;

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

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }


