package cs.dawson.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * The following class will display the chosen character's details and
 * a large image of that character followed by links and a menu bar.
 * Authors: Zahraa and Maxime
 */
public class QuoteActivity extends AppCompatActivity {


    Bundle savedInstance;

    String nameId;
    String birthName;
    String blurb;
    String dateOfbirth;
    String url;
    String imgUri;
    String dateAdded;
    String quote;
    ArrayList<String> imgsUri;
    TextView characterNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstance = savedInstanceState;
        setContentView(R.layout.character_details);

        // Get the data from the intent
        nameId = getIntent().getExtras().getString("nameId");
        birthName = getIntent().getExtras().getString("birthName");
        blurb = getIntent().getExtras().getString("blurb");
        dateOfbirth = getIntent().getExtras().getString("dateOfbirth");
        url = getIntent().getExtras().getString("url");
        imgUri = getIntent().getExtras().getString("imageUri");
        dateAdded = getIntent().getExtras().getString("dateAdded");
        quote = getIntent().getExtras().getString("quote");
        // getting a reference to the characterName TextView in onCreate in order to be able
        // to use it in other methods.
        characterNameTv = (TextView) findViewById(R.id.characterName);
        setClickableCharacterName();

        saveToSharedPref(quote);
        setTextsToTextViews();
        setCharacterImage();

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
            i.putExtra("birthName", birthName);
            i.putExtra("blurb", blurb);
            i.putExtra("dateOfBirth", dateOfBirth);
            i.putExtra("url", url);
            i.putExtra("quote", quote);
            i.putExtra("imageUri", imageUri);
            i.putExtra("dateAdded", dateAdded);
            startActivity(i);
        }
        // else we could display a dialog saying there is no recent selections
    }

    /**
     * The following method saves the clicked Character and quote on the disk
     * using SharedPreferences.
     * @param quote
     */
    private void saveToSharedPref(String quote){
        // save the clicked quote
        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("nameId", nameId);
        editor.putString("birthName", birthName);
        editor.putString("blurb", blurb);
        editor.putString("dateOfbirth", dateOfbirth);
        editor.putString("url", url);
        editor.putString("imageUri", imgUri);
        editor.putString("dateAdded", dateAdded);
        editor.putString("lastQuote", quote);
        editor.commit();
    }


    /**
     * The following method sets the character's data to the TextViews which was
     * taken from the database and passed to this class.
     */
    private void setTextsToTextViews(){
        //character name
        characterNameTv.setText(nameId);

        //data of birth of character
        TextView characterDoBTv = (TextView) findViewById(R.id.characterDoB);
        characterDoBTv.setText(dateOfbirth);
        //quote full
        TextView fullQuoteSelectedTv = (TextView) findViewById(R.id.fullQuoteSelected);
        fullQuoteSelectedTv.setText(quote);
        //date added
        TextView dateAddedTv = (TextView) findViewById(R.id.dateAdded);
        dateAddedTv.setText("Added: " + dateAdded);
        //reference
        TextView urlReference = (TextView) findViewById(R.id.urlReference);
        urlReference.setText(url);
    }

    /**
     * The following method sets the passed image to the imageView
     */
    private void setCharacterImage(){
        ImageView characterImg = (ImageView) findViewById(R.id.characterImg);
        // get image of character
        FirebaseAuth mFirebaseAuth  =  FirebaseAuth.getInstance();
        // login as user
        mFirebaseAuth.signInWithEmailAndPassword("someuserfordb@hotmail.com", "android123");

        // get image from database
        StorageReference sref = FirebaseStorage.getInstance().getReferenceFromUrl(imgUri);
        Glide.with(this).using(new FirebaseImageLoader()).load(sref).into(characterImg);
    }

    /**
     * The following method sets the Character's name to clickable
     * and when it is clicked, a popup dialog will show up which will
     * contain the blurb of that character. The dialog also has a button which
     * will close the dialog.
     */
    private void setClickableCharacterName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(blurb).setTitle(R.string.characterDialogTitle);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        final AlertDialog dialog = builder.create();

        characterNameTv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialog.show();
            }
        });


    }

    /**
     * The following method gets the class' Context.
     * @return context of this class
     */
    private Context getActivity(){
        return QuoteActivity.this;
    }
} // end of QuoteActivity


