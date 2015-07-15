package prince.wifiselector;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;


public class Splash extends Activity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle titanWifi) {
        super.onCreate(titanWifi);
        setContentView(R.layout.activity_splash);
        ourSong = MediaPlayer.create(Splash.this, R.raw.tone_ganesh);
        ourSong.start();
        Thread timer = new Thread() {

            public void run() {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openActivityMain = new Intent("android.intent.action.MENU");
                    startActivity(openActivityMain);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }


}
