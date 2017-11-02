package cs.dawson.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Max on 10/23/2017.
 */

public class CharacterListActivity extends Activity {

    ListView lv;
    Context context;
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
        setContentView(R.layout.character_shortquotes);

        nameId = getIntent().getExtras().getString("nameId");
        birthName = getIntent().getExtras().getString("birthName");
        blurb = getIntent().getExtras().getString("blurb");
        dateOfbirth = getIntent().getExtras().getString("dateOfbirth");
        url = getIntent().getExtras().getString("url");
        quotes = getIntent().getExtras().getStringArray("quotes");


        TextView tvName = (TextView) findViewById(R.id.characterNames);
        tvName.setText(nameId);

        //Setting the listview in the main activity to an adapter
        lv=(ListView) findViewById(R.id.shortQuotesListView);
        lv.setAdapter(new QuoteListAdapter(this, nameId, birthName, blurb, dateOfbirth, url, quotes));

    }


    public void launchQuoteListActivity(View view) {
        Intent i = new Intent(this, QuoteListActivity.class);
        startActivity(i);
    }








        }
