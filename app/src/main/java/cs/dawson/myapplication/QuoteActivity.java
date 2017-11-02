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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cs.dawson.myapplication.CharacterBlurbDialog;

import org.w3c.dom.Text;

/**
 * Created by Max on 10/23/2017.
 */

public class QuoteActivity extends Activity {

    Bundle savedInstance;

    String nameId;
    String birthName;
    String blurb;
    String dateOfbirth;
    String description;
    String url;
    String[] quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstance = savedInstanceState;
        setContentView(R.layout.character_details);

        //Grabbing the position in the array that the user has clicked.
        String nameId = getIntent().getExtras().getString("nameId");
        String birthName = getIntent().getExtras().getString("birthName");
        String blurb = getIntent().getExtras().getString("blurb");
        String dateOfbirth = getIntent().getExtras().getString("dateOfbirth");
        String url = getIntent().getExtras().getString("url");
        String quote = getIntent().getExtras().getString("quote");

        //character name
        TextView characterName = (TextView) findViewById(R.id.characterName);
        characterName.setText(nameId);// MAKE THIS CLICKABLE.


//        characterName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage(R.string.dialog_blurb) // This would be the blurb from the database
//                .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //Exit the dialog
//
//                    }
//                });

        //image of character
        ImageView characterImg = (ImageView) findViewById(R.id.characterImg);
        characterImg.setImageResource(R.drawable.dino1); //FILLER FOR IMAGE OF CHARACTER
        //data of birth of character
        TextView characterDoB = (TextView) findViewById(R.id.characterDoB);
        characterDoB.setText(dateOfbirth); //FILLER FOR DATE OF BIRTH
        //quote full
        TextView fullQuoteSelected = (TextView) findViewById(R.id.fullQuoteSelected);
        fullQuoteSelected.setText(quote); //FILLER FOR QUOTE SELECTED
        //date added
        TextView dateAdded = (TextView) findViewById(R.id.dateAdded);
        dateAdded.setText("2017/11/1"); //FILLER FOR THE DATE WE ADDED THE CHARACTER TO DATABASE????
        //reference
        TextView urlReference = (TextView) findViewById(R.id.urlReference);
        urlReference.setText(url); //FILLER FOR THE URL --> MAKE THIS CLICKABLE AND REDIRECT TO CHROME AT THIS URL? INTENT NEEDED.

    }

//    public void openDialog(View v) {
//        final Dialog dialog = new Dialog(this); // Context, this, etc.
//        //dialog.setContentView(R.layout.blurbdialog);
//        LinearLayout mainLinear = new LinearLayout(this);
//        TextView charName = new TextView(this);
//        charName.setText(nameId);
//        charName.setTextSize(22);
//        mainLinear.addView(charName);
//        dialog.setContentView(mainLinear);
//        dialog.setTitle("title");
//        dialog.show();
//    }
//
//
} // end of QuoteActivity


