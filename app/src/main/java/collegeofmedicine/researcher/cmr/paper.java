package collegeofmedicine.researcher.cmr;

import java.io.Serializable;

/**
 * Created by Nawal on 7/29/2017.
 */

public class paper implements Serializable {

    private String title,Author,sourceTitle,url ,date ,description;

    public paper() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
