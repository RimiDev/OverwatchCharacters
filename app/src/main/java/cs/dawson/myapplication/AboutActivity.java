package cs.dawson.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import cs.dawson.myapplication.MainActivity;

/**
 * The following class is the Actvity class for the About page. It will have an Image and
 * texts.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        // set image to the about page
        ImageView aboutIv = (ImageView) findViewById(R.id.aboutImageView);
        aboutIv.setImageResource(R.drawable.aboutimg);
    }


    /**
     * Inflates the menu into a Menu object.
     * @param menu
     * @return true/false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    /**
     * Receives the selected menu item and depending on which one will grant a different outcome.
     * random --> nothing so far.
     * last --> loadCharacterAndLastQuote which loads the last character and quote that was shown.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                // create intent for aboutActivity
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;
            case R.id.random:
                return true;
            case R.id.last:
                loadCharacterAndLastQuote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Loads the last clicked character and quote of that character from SharedPreferences.
     */
    private void loadCharacterAndLastQuote(){
        // load from sharedPrefs
        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        if(prefs.contains("nameId")) {
            String nameId = prefs.getString("nameId", "");
            String birthName = prefs.getString("birthName", "");
            String blurb = prefs.getString("blurb", "");
            String dateOfBirth = prefs.getString("dateOfbirth", "");
            String url = prefs.getString("url", "");
            String imageUri = prefs.getString("imageUri", "");
            String dateAdded = prefs.getString("dateAdded", "");
            String quote = prefs.getString("lastQuote", "");

            Intent i = new Intent(this, QuoteActivity.class);
            i.putExtra("nameId", nameId);
            i.putExtra("birthName", birthName); //Puts the character name array into intent
            i.putExtra("blurb", blurb); //Puts the character imgs array into intent
            i.putExtra("dateOfBirth", dateOfBirth);
            i.putExtra("url", url);
            i.putExtra("quote", quote); // puts the selected quote into intent
            i.putExtra("imageUri", imageUri);
            i.putExtra("dateAdded", dateAdded);
            startActivity(i);
        }
        // else we could display a dialog saying there is no recent selections
    }


}
