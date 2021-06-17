package collegeofmedicine.researcher;

import android.app.Application;
import android.content.Context;

/**
 * Created by Hissa on 03/08/17.
 */

public class ResearchApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        ResearchApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ResearchApp.context;
    }
}
