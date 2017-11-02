package cs.dawson.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs.dawson.entities.OverwatchCharacter;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;
    private CharacterListAdapter characterListAdapter;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;

    private List<OverwatchCharacter> allCharacters;
//    private FirebaseUser mFirebaseUser;

    public static int[] characterImgs = {R.drawable.dino1,R.drawable.dino2,R.drawable.dino3,R.drawable.dino4,R.drawable.dino5, R.drawable.dino1};

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
        // loads data from database into the private variables
        loadData();


        Log.d("allCharacters size333",allCharacters.size()+"");


        //displayStuff();
        // handle error if login was not successful?
        /*
        mFirebaseAuth.signInWithEmailAndPassword("someuserfordb@hotmail.com", "android123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // do something if login is successfull
                    DatabaseReference birthName = mDatabase.child("genji").child("birthName").getRef();

                    Log.d("TAG", birthName);
                }
            }
        });*/

        //mFirebaseUser = mFirebaseAuth.getCurrentUser();

        /*
        context=this;

        lv=(ListView) findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this, characterNames ,characterImgs, characterQuotes);
        lv.setAdapter(customAdapter);
*/

    }


    public void launchCharacterListActivity(View view) {
        Intent i = new Intent(this, CharacterListActivity.class);
        startActivity(i);
    }


    private void loadData(){
        mDatabase.child("characters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot characterDatasnapshot : dataSnapshot.getChildren()) {
                    // create an OverwatchCharacter object for each child of characters
                    OverwatchCharacter character = createCharacter(characterDatasnapshot);
                    Log.d("character name id :\t", characterDatasnapshot.child("nameId").getValue().toString());
                    allCharacters.add(character);
                    Log.d("allCharacters size",allCharacters.size()+"");
                }
                setData(allCharacters);
                //characterListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void setData(List<OverwatchCharacter> character){

        //Accessing the quotes child
        mDatabase.child("quotes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //List<String> quotes = new ArrayList<>();
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

                displayCharactersAndQuotes();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CharacterListAdapter(this, allCharacters));


        Log.d("allCharacters size2222",allCharacters.size()+"");
    }

    /**
     * // TODO: not sure why...
     * if we dont do this -> allCharacters is null
     * @param overwatchCharacter
     */
    private void addToCharacterList(OverwatchCharacter overwatchCharacter){
        allCharacters.add(overwatchCharacter);
    }

    /**
     * for testing TODO delete this method...
     */
    private void displayAllCharacters(){
        for(OverwatchCharacter c : allCharacters)
            Log.d("character display:\t",  c.getBirthName());
    }

    /**
     * for testing TODO delete this method...
     */
    private void displayCharactersAndQuotes(){
        for(OverwatchCharacter c : allCharacters) {
            Log.d("character display:\t", c.getBirthName());
            for(String quote : c.getQuotes())
                Log.d("\tcharacter quote:\t", quote);
        }
    }

    private OverwatchCharacter createCharacter(DataSnapshot dataSnapshot){
        OverwatchCharacter overwatchCharacter = new OverwatchCharacter();
        overwatchCharacter.setNameId(dataSnapshot.child("nameId").getValue().toString());
        overwatchCharacter.setBirthName(dataSnapshot.child("birthName").getValue().toString());
        overwatchCharacter.setBlurb(dataSnapshot.child("blurb").getValue().toString());
        overwatchCharacter.setDateOfBirth(dataSnapshot.child("dateOfBirth").getValue().toString());
        overwatchCharacter.setDescription(dataSnapshot.child("description").getValue().toString());
        overwatchCharacter.setInfoUrl(dataSnapshot.child("url").getValue().toString());
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

    public static void logIt(String msg) {
        final String TAG = "OverWatchQuotes-------";
        Log.d(TAG, msg);
    }


}
