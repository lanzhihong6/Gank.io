package com.lanzhihong.gankio.bean;

import java.util.List;

/**
 * Created by lanzhihong on 2016/3/4.
 */
public class Meizhi {


    /**
     * error : false
     * results : [{"_id":"56d8eb3a67765967efcbd69c","_ns":"ganhuo","createdAt":"2016-03-04T09:56:10.964Z","desc":"3.4","publishedAt":"2016-03-04T12:44:51.926Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f1klhuc8w5j20d30h9gn8.jpg","used":true,"who":"张涵宇"},{"_id":"56d7b07f6776595ac3e5cf92","_ns":"ganhuo","createdAt":"2016-03-03T11:33:19.706Z","desc":"3.3","publishedAt":"2016-03-03T12:12:56.684Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f1jionqvz6j20hs0qoq7p.jpg","used":true,"who":"张涵宇"},{"_id":"56d6481e6776592a03e624a4","_ns":"ganhuo","createdAt":"2016-03-02T09:55:42.63Z","desc":"3.2","publishedAt":"2016-03-02T12:06:37.242Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f1ia8qj5qbj20nd0zkmzp.jpg","used":true,"who":"张涵宇"},{"_id":"56d4f4f9421aa9647be5f908","_ns":"ganhuo","createdAt":"2016-03-01T09:48:41.472Z","desc":"3.1","publishedAt":"2016-03-01T12:09:30.687Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f1h4f51wbcj20f00lddih.jpg","used":true,"who":"张涵宇"},{"_id":"56d3c506421aa93cbc97e25c","_ns":"ganhuo","createdAt":"2016-02-29T12:11:50.467Z","desc":"2.29-1","publishedAt":"2016-02-29T12:19:00.15Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f1g2xpx9ehj20ez0mi0vc.jpg","used":true,"who":"张涵宇"},{"_id":"56cfcaba421aa909db4d6ab0","_ns":"ganhuo","createdAt":"2016-02-26T03:37:44.552Z","desc":"2.26","publishedAt":"2016-02-26T11:58:08.172Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f1cl3c7rfgj20dw0ku76t.jpg","used":true,"who":"张涵宇"},{"_id":"56ce83f8421aa910e89d4379","_ns":"ganhuo","createdAt":"2016-02-25T02:22:18.992Z","desc":"2.25","publishedAt":"2016-02-25T12:34:54.0Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f1bdal8i3nj20f00lf77g.jpg","used":true,"who":"张涵宇"},{"_id":"56ccf89e421aa92e1da900be","_ns":"ganhuo","createdAt":"2016-02-24T00:22:24.855Z","desc":"2.24","publishedAt":"2016-02-24T04:42:32.66Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f1a47fpjacj20f00imtam.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708336","_ns":"ganhuo","createdAt":"2016-02-23T02:24:19.518Z","desc":"2.23","publishedAt":"2016-02-23T05:08:46.837Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f19241kkpwj20f00hfabt.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa70831a","_ns":"ganhuo","createdAt":"2016-02-22T02:48:39.585Z","desc":"2.22","publishedAt":"2016-02-22T04:20:23.542Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f17x6wmh09j20f00m1mzh.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    /**
     * _id : 56d8eb3a67765967efcbd69c
     * _ns : ganhuo
     * createdAt : 2016-03-04T09:56:10.964Z
     * desc : 3.4
     * publishedAt : 2016-03-04T12:44:51.926Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/7a8aed7bjw1f1klhuc8w5j20d30h9gn8.jpg
     * used : true
     * who : 张涵宇
     */

    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        private String _id;
        private String _ns;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void set_ns(String _ns) {
            this._ns = _ns;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public String get_ns() {
            return _ns;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getWho() {
            return who;
        }
    }
}
