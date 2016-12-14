package com.aqiang.dllo.mybaidumusic.bean.sonBean;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */

public class KFragmentBeanLunBo {


    /**
     * error_code : 22000
     * result : [{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_6c6235323f1108945f544184294b27a1.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_18367beb80404223e53ef2510d355f13.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20161123/"},{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_e7dde5d0cd0ee7ac6fa9a01b528b6909.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_6ff733d902e8142ec33f7b2037626900.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20161025/"}]
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
         * type : learn
         * picture : http://business.cdn.qianqian.com/qianqian/pic/bos_client_6c6235323f1108945f544184294b27a1.jpg
         * picture_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_18367beb80404223e53ef2510d355f13.jpg
         * web_url : http://music.baidu.com/cms/webview/ktv_activity/20161123/
         */

        private String type;
        private String picture;
        private String picture_iphone6;
        private String web_url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPicture_iphone6() {
            return picture_iphone6;
        }

        public void setPicture_iphone6(String picture_iphone6) {
            this.picture_iphone6 = picture_iphone6;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
