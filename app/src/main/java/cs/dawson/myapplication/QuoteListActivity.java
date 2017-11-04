package cs.dawson.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import cs.dawson.entities.OverwatchCharacter;

/**
 * The following class is the Activity class for the Quote List.
 * It will set the TextView to the name of the character and lists all the quotes
 * of that character.
 */
public class QuoteListActivity extends AppCompatActivity {

    String nameId;
    String birthName;
    String blurb;
    String dateOfbirth;
    String url;
    String[] quotes;
    String imgUri;
    String dateAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_shortquotes);

        nameId = getIntent().getExtras().getString("nameId");
        birthName = getIntent().getExtras().getString("birthName");
        blurb = getIntent().getExtras().getString("blurb");
        dateOfbirth = getIntent().getExtras().getString("dateOfbirth");
        url = getIntent().getExtras().getString("url");
        quotes = getIntent().getExtras().getStringArray("quotes");
        imgUri = getIntent().getExtras().getString("imageUri");
        dateAdded = getIntent().getExtras().getString("dateAdded");

        TextView tvName = (TextView) findViewById(R.id.characterNames);
        tvName.setText(nameId);

        ListView lvshortQuotes = (ListView) findViewById(R.id.shortQuotesListView);
        lvshortQuotes.setAdapter(new QuoteListAdapter(this, nameId, birthName, blurb, dateOfbirth, url, quotes, imgUri, dateAdded));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                // create intent for aboutActivity
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
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
        // if there is nothing saved, nothing will get loaded.
    }







}
