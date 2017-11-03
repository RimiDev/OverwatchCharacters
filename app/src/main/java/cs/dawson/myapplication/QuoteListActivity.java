package cs.dawson.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Max on 10/23/2017.
 */

public class QuoteListActivity extends MenuActivity {

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



        ListView lvshortQuotes = (ListView) findViewById(R.id.shortQuotesListView);
        lvshortQuotes.setAdapter(new QuoteListAdapter(this, nameId, birthName, blurb, dateOfbirth, url, quotes));

    }

    public void launchQuoteActivity(View view) {
        Intent i = new Intent(this, QuoteActivity.class);
        startActivity(i);
    }







}
