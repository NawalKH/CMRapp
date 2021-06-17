package collegeofmedicine.researcher.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

        WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setTitle("Source");


        webview =(WebView)findViewById(R.id.web);
        webview.setWebViewClient(new MyWebViewClient());
        openURL();
    }

        private void openURL() {
            webview.getSettings().setJavaScriptEnabled(true);


            if( getIntent().getStringExtra("PDF") != null)
            {
                webview.loadUrl("http://docs.google.com/gview?embedded=true&url="+ getIntent().getStringExtra("PDF"));
                webview.requestFocus();
            }
            else
            {
                String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
                webview.loadUrl(s);
                webview.requestFocus();

            }

        }
    }

