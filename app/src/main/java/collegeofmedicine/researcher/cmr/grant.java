package collegeofmedicine.researcher.cmr;


import java.io.Serializable;

/**
 * Created by Nawal on 8/10/2017.
 */

public class grant implements Serializable {

    private String name, Sdate, Edate, url;


    public grant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdate() {
        return Sdate;
    }

    public void setSdate(String sdate) {
        Sdate = sdate;
    }

    public String getEdate() {
        return Edate;
    }

    public void setEdate(String edate) {
        Edate = edate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
