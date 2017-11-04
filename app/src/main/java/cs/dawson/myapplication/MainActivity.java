package cs.dawson.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs.dawson.entities.OverwatchCharacter;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;
    private CharacterListAdapter characterListAdapter;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;

    private ArrayList<OverwatchCharacter> allCharacters;
    private List<String> iconImgsUri;
    private List<String> imgsUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        context=this;

        //Setting the listview in the main activity to an adapter


        // log in to database
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // login as user
        mFirebaseAuth.signInWithEmailAndPassword("someuserfordb@hotmail.com", "android123");

        allCharacters = new ArrayList<>();
        iconImgsUri = new ArrayList<>();
        imgsUri = new ArrayList<>();

        // loads data from database into the private variables
        loadData();

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
            case R.id.random:
                getRandomCharacterAndQuote();
                return true;
            case R.id.last:
                loadCharacterAndLastQuote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * The following method gets a random character from the list of all the characters
     * and will get a random quote of that character.
     */
    private void getRandomCharacterAndQuote(){
        Random rand = new Random();
        int randNum = rand.nextInt(allCharacters.size());
        OverwatchCharacter overwatchCharacter = allCharacters.get(randNum);
        String[] allQuotes = overwatchCharacter.getQuotes();
        int randQuoteNum = rand.nextInt(allQuotes.length);
        String randQuote = allQuotes[randQuoteNum];

        Intent i = new Intent(this, QuoteActivity.class);
        i.putExtra("nameId",overwatchCharacter.getNameId());
        i.putExtra("birthName", overwatchCharacter.getBirthName()); //Puts the character name array into intent
        i.putExtra("blurb", overwatchCharacter.getBlurb()); //Puts the character imgs array into intent
        i.putExtra("dateOfBirth", overwatchCharacter.getDateOfBirth());
        i.putExtra("url", overwatchCharacter.getInfoUrl());
        i.putExtra("quote", randQuote); // puts the selected quote into intent
        i.putExtra("imageUri", imgsUri.get(randNum));
        i.putExtra("dateAdded", overwatchCharacter.getDateAdded());

        startActivity(i);

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
        // else nothing happens since there are no saved data
    }

    /**
     * The following method will load the characters from the database and create an
     * OverwatchCharacter object and add it to a private variable of type ArrayList
     * which will hold all the characters read from the database.
     */
    private void loadData(){
        mDatabase.child("characters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot characterDatasnapshot : dataSnapshot.getChildren()) {
                    // create an OverwatchCharacter object for each child of characters
                    OverwatchCharacter character = createCharacter(characterDatasnapshot);
                    iconImgsUri.add(characterDatasnapshot.child("iconImageUrl").getValue().toString());
                    imgsUri.add(characterDatasnapshot.child("imageUrl").getValue().toString());
                    allCharacters.add(character);
                }
                setData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    /**
     * The following method will set the quotes taken from the database of each character
     * to their appropriate OverwatchCharacter object.
     */
    private void setData(){

        //Accessing the quotes child
        mDatabase.child("quotes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot quotesDatasnapshot : dataSnapshot.getChildren()) {
                    // create String[] object that will hold quotes for each character
                    String[] quotes = createCharacterQuotes(quotesDatasnapshot);
                    // iterates through all OverwatchCharacter objects in the list looking for the
                    // OverwatchCharacter object which has the nameId that matches the given name
                    for (OverwatchCharacter c : allCharacters){
                        if(c.getNameId().equals(quotesDatasnapshot.child("nameId").getValue().toString()))
                            c.setQuotes(quotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CharacterListAdapter(this, allCharacters, iconImgsUri, imgsUri));
    }

    /**
     * The following method will return an OverwatchCharacter object based on the
     * DataSnapshot given which was read from the database. The DataSnapshot is
     * a child of characters which holds the data of a single character.
     * @param dataSnapshot
     * @return
     */
    private OverwatchCharacter createCharacter(DataSnapshot dataSnapshot){
        OverwatchCharacter overwatchCharacter = new OverwatchCharacter();
        overwatchCharacter.setNameId(dataSnapshot.child("nameId").getValue().toString());
        overwatchCharacter.setBirthName(dataSnapshot.child("birthName").getValue().toString());
        overwatchCharacter.setBlurb(dataSnapshot.child("blurb").getValue().toString());
        overwatchCharacter.setDateOfBirth(dataSnapshot.child("dateOfBirth").getValue().toString());
        overwatchCharacter.setInfoUrl(dataSnapshot.child("url").getValue().toString());
        overwatchCharacter.setDateAdded(dataSnapshot.child("dateAdded").getValue().toString());
        return overwatchCharacter;
    }

    /**
     * The following method creates a String array containing all the quotes
     * in the given DataSnapshot.
     * @param dataSnapshot
     * @return String[] containing 5 quotes from the DataSnapshot
     */
    private String[] createCharacterQuotes(DataSnapshot dataSnapshot){
        String[] quotes = new String[5];

        quotes[0] = dataSnapshot.child("quote1").getValue().toString();
        quotes[1] = dataSnapshot.child("quote2").getValue().toString();
        quotes[2] = dataSnapshot.child("quote3").getValue().toString();
        quotes[3] = dataSnapshot.child("quote4").getValue().toString();
        quotes[4] = dataSnapshot.child("quote5").getValue().toString();

        return quotes;
    }

}
