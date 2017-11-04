package cs.dawson.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * The following class is the Actvity class for the About page. It will have an Image and
 * texts.
 */

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        // set image to the about page
        ImageView aboutIv = (ImageView) findViewById(R.id.aboutImageView);
        aboutIv.setImageResource(R.drawable.aboutimg);
    }
}
