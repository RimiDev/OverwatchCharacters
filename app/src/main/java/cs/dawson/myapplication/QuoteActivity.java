package cs.dawson.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cs.dawson.myapplication.CharacterBlurbDialog;

import org.w3c.dom.Text;

/**
 * Created by Max on 10/23/2017.
 */

public class QuoteActivity extends Activity {

    ListView lv;
    Context context;
    Bundle savedInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstance = savedInstanceState;
        setContentView(R.layout.character_details);

        //Grabbing the position in the array that the user has clicked.
        int position = getIntent().getExtras().getInt("position");
        String[] names = getIntent().getExtras().getStringArray("names");

        //character name
        TextView characterName = (TextView) findViewById(R.id.characterName);
        characterName.setText(names[position]);// MAKE THIS CLICKABLE.
        //image of character
        ImageView characterImg = (ImageView) findViewById(R.id.characterImg);
        characterImg.setImageResource(R.drawable.dino1); //FILLER FOR IMAGE OF CHARACTER
        //data of birth of character
        TextView characterDoB = (TextView) findViewById(R.id.characterDoB);
        characterDoB.setText("1996/08/12"); //FILLER FOR DATE OF BIRTH
        //quote full
        TextView fullQuoteSelected = (TextView) findViewById(R.id.fullQuoteSelected);
        fullQuoteSelected.setText("She shoots, she scores!"); //FILLER FOR QUOTE SELECTED
        //date added
        TextView dateAdded = (TextView) findViewById(R.id.dateAdded);
        dateAdded.setText("2017/11/1"); //FILLER FOR THE DATE WE ADDED THE CHARACTER TO DATABASE????
        //reference
        TextView urlReference = (TextView) findViewById(R.id.urlReference);
        urlReference.setText("www.characters.com/genji"); //FILLER FOR THE URL --> MAKE THIS CLICKABLE AND REDIRECT TO CHROME AT THIS URL? INTENT NEEDED.

    }

    public void openDialog(View v) {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.blurbdialog);
        TextView tv = (TextView) v.findViewById
        dialog.setTitle("title");
        dialog.show();
    }

} // end of QuoteActivity

