package collegeofmedicine.researcher.cmr;

import java.io.Serializable;

/**
 * Created by Nawal on 8/18/2017.
 */

public class Protocol implements Serializable {
    String title,path;

    public Protocol() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
