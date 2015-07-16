package prince.wifiselector;

/**
 * Created by vishruitprince on 16-Jul-15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = (WebView) findViewById(R.id.webView1);
        String ipaddr = "192.168.1.5";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://" + ipaddr +"/gpio/0");   // the
    }
}