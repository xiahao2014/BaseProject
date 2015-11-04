package com.summerhao.bs.activity.frame_common.okhttp;

/**
 * 项目名称：BaseProject
 * 类描述：
 * 创建人：xiahao
 * 创建时间：2015/10/25 16:00
 * 修改人：xiahao
 * 修改时间：2015/10/25 16:00
 * 修改备注：
 */
public class Data {


    public String id;
    public String author;
    public String url;
    public String content;

//    public Data() {
//        // TODO Auto-generated constructor stub
//    }
//
//    public Data(String id, String author, String url, String content) {
//        this.id = id;
//        this.author = author;
//        this.url = url;
//        this.content = content;
//    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
