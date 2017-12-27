package com.bwei.www.drycargo.bean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public class ListBean {

    /**
     * error : false
     * results : [{"_id":"5a03ff66421aa90fef203514","createdAt":"2017-11-09T15:10:30.389Z","desc":"微信个人网页版API的微信机器人","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"App","url":"http://www.jianshu.com/p/ce911828dec5","used":true,"who":"joehe_cn"},{"_id":"59d9a059421aa94e0053bdcd","createdAt":"2017-10-08T11:49:45.386Z","desc":"一个功能强大的进制计算器","publishedAt":"2017-10-10T12:41:34.882Z","source":"chrome","type":"App","url":"https://github.com/youlingwangzi/CalculatorForProgrammer","used":true,"who":"galois"},{"_id":"59ad6186421aa901c1c0a8df","createdAt":"2017-09-04T22:21:58.464Z","desc":"MacOS版微信小助手 功能: 自动回复、消息防撤回、远程控制、微信多开","images":["http://img.gank.io/879d6bdd-a345-44a9-8870-1a96fb883f0c"],"publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"App","url":"https://github.com/TKkk-iOSer/WeChatPlugin-MacOS","used":true,"who":"TK"},{"_id":"59a53723421aa901c1c0a8a3","createdAt":"2017-08-29T17:42:59.847Z","desc":"360°全景图 DroidVR，这是一个值得把玩的APP","images":["http://img.gank.io/54608c97-7557-4633-ae79-51712f4f413c"],"publishedAt":"2017-09-01T12:55:52.582Z","source":"web","type":"App","url":"https://github.com/sfsheng0322/DroidVR","used":true,"who":"孙福生"},{"_id":"59959e11421aa9672cdf081f","createdAt":"2017-08-17T21:45:53.883Z","desc":"收集多种翻译接口并用同一个 API 调用 (Good Job! )","publishedAt":"2017-08-23T12:12:15.166Z","source":"chrome","type":"App","url":"https://github.com/Selection-Translator/translation.js/","used":true,"who":"咕咚"},{"_id":"59798512421aa90ca209c4f9","createdAt":"2017-07-27T14:15:46.258Z","desc":"安卓音频截取及转换","images":["http://img.gank.io/5fd6fa1e-c5b4-44fc-bfa6-9cb367fa1c39"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"App","url":"https://github.com/google/ringdroid","used":true,"who":"jp1017"},{"_id":"59793ea1421aa90ca209c4f3","createdAt":"2017-07-27T09:15:13.183Z","desc":"轻量 Youtube 客户端","images":["http://img.gank.io/04e6c264-b0e4-4c1d-a5cd-88d770a13605"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"App","url":"https://github.com/TeamNewPipe/NewPipe","used":true,"who":"jp1017"},{"_id":"5976a465421aa97de5c7c9a7","createdAt":"2017-07-25T09:52:37.993Z","desc":"MusicDNA Android版","images":["http://img.gank.io/2dbdb3b3-b547-4518-8263-22fd2de316cd"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"App","url":"https://github.com/harjot-oberai/MusicDNA","used":true,"who":"galois"},{"_id":"5967a39c421aa97de5c7c92e","createdAt":"2017-07-14T00:45:16.87Z","desc":"View more, just Now | NowView设计篇","publishedAt":"2017-07-14T13:24:31.177Z","source":"web","type":"App","url":"https://mp.weixin.qq.com/s?__biz=MzA3MTA2MDM2Mg==&mid=2247483655&idx=1&sn=a0b6b26975f9219b1430c2ca519f82a6&chksm=9f321fe2a84596f430862f769ff2ed4bd7b1ba24f0d4d137b01c198be5b94f1d37366053dfb2#rd","used":true,"who":null},{"_id":"595f442f421aa90cb4724b88","createdAt":"2017-07-07T16:19:59.682Z","desc":"Mac 上做好的 GitHub 加星项目管理工具","images":["http://img.gank.io/16e48558-1545-43c7-a36f-3a6982d2834a"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"App","url":"https://itunes.apple.com/us/app/ohmystar2/id1218642292?ls=1&mt=8","used":true,"who":"ChenYu Xiao"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a03ff66421aa90fef203514
         * createdAt : 2017-11-09T15:10:30.389Z
         * desc : 微信个人网页版API的微信机器人
         * publishedAt : 2017-11-10T08:10:02.838Z
         * source : web
         * type : App
         * url : http://www.jianshu.com/p/ce911828dec5
         * used : true
         * who : joehe_cn
         * images : ["http://img.gank.io/879d6bdd-a345-44a9-8870-1a96fb883f0c"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
