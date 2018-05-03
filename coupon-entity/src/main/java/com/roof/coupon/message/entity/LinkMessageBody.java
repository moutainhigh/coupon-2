package com.roof.coupon.message.entity;

/**
 * com.roof.coupon.message.entity
 *
 * @author liht
 * @date 2018/5/2
 */
public class LinkMessageBody {

    private String title;
    private String description;
    private String url;

    private String thumb_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }
}
