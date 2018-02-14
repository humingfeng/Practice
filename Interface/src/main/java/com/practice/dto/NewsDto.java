package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd on 2018/2/14 11:08
 */
public class NewsDto implements Serializable {

    private String id;
    private String title;
    private String source;
    private String content;
    private String pic;
    private List<String> imgArry;
    private String pubDate;
    private String link;

    public NewsDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<String> getImgArry() {
        return imgArry;
    }

    public void setImgArry(List<String> imgArry) {
        this.imgArry = imgArry;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
