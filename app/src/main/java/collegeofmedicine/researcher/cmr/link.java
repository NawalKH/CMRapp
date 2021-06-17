package collegeofmedicine.researcher.cmr;

import java.io.Serializable;

/**
 * Created by Hissa on 11/08/17.
 */

public class link implements Serializable {
    private String u_title, url, img;
    public link(String title,String urll, String img){
        this.u_title = title;
        this.url = urll;
        this.img = img;
    }

    public String getU_title() {
        return u_title;
    }


    public String getUrl() {
        return url;
    }


    public String getImg() {
        return img;
    }

}
