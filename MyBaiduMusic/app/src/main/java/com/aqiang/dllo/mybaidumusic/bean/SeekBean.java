package com.aqiang.dllo.mybaidumusic.bean;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/14.
 */

public class SeekBean {

    /**
     * error_code : 22000
     * result : [{"strong":1,"linkurl":"","linktype":0,"word":"周杰伦"},{"strong":0,"linkurl":"","linktype":0,"word":"《你的名字。》电影原声带"},{"strong":0,"linkurl":"","linktype":0,"word":"G.E.M.邓紫棋"},{"strong":0,"linkurl":"","linktype":0,"word":"Taylor Swift"},{"strong":0,"linkurl":"","linktype":0,"word":"王菲"},{"strong":0,"linkurl":"","linktype":0,"word":"父亲"},{"strong":0,"linkurl":"","linktype":0,"word":"陈嘉桦"},{"strong":0,"linkurl":"","linktype":0,"word":"好妹妹乐队"},{"strong":0,"linkurl":"","linktype":0,"word":"凤凰传奇"},{"strong":0,"linkurl":"","linktype":0,"word":"告白气球"},{"strong":0,"linkurl":"","linktype":0,"word":"丑八怪-薛之谦"},{"strong":0,"linkurl":"","linktype":0,"word":"刀郎"},{"strong":0,"linkurl":"","linktype":0,"word":"青春修炼手册-TFBOYS"},{"strong":0,"linkurl":"","linktype":0,"word":"小幸运"},{"strong":0,"linkurl":"","linktype":0,"word":"凤凰传奇"}]
     */

    private int error_code;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * strong : 1
         * linkurl :
         * linktype : 0
         * word : 周杰伦
         */

        private int strong;
        private String linkurl;
        private int linktype;
        private String word;

        public int getStrong() {
            return strong;
        }

        public void setStrong(int strong) {
            this.strong = strong;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public int getLinktype() {
            return linktype;
        }

        public void setLinktype(int linktype) {
            this.linktype = linktype;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}
