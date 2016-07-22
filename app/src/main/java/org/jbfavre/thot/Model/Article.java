package org.jbfavre.thot.Model;

import java.util.Date;

/**
 * Created by jbfavre on 22/07/16.
 */
public class Article {
    private int id;
    private Date datetime;
    private String title;
    private String content;
    private boolean unread;
    private boolean starred;
    private int source;
    private String thumbnail;
    private String icon;
    private String uid;
    private String link;
    private String sourcetitle;
    private String tags;

    public int getId() {
        return id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isUnread() {
        return unread;
    }

    public boolean isStarred() {
        return starred;
    }

    public int getSource() {
        return source;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getIcon() {
        return icon;
    }

    public String getUid() {
        return uid;
    }

    public String getLink() {
        return link;
    }

    public String getSourcetitle() {
        return sourcetitle;
    }

    public String getTags() {
        return tags;
    }
}
