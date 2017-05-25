package com.qqdemo.administrator.gdcp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Tittle {


    /**
     * tittle : 计算机工程学院
     * key : true
     */

    private List<TittlesBean> tittles;

    public List<TittlesBean> getTittles() {
        return tittles;
    }

    public void setTittles(List<TittlesBean> tittles) {
        this.tittles = tittles;
    }

    public static class TittlesBean {
        private String tittle;
        private boolean key;

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }

        public boolean isKey() {
            return key;
        }

        public void setKey(boolean key) {
            this.key = key;
        }
    }
}
