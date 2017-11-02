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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_shortquotes);

        int position = getIntent().getExtras().getInt("position");
        String[] names = getIntent().getExtras().getStringArray("names");
        String[] quotes = getIntent().getExtras().getStringArray("shortquotes");
        int[] images = getIntent().getExtras().getIntArray("images");


        TextView tvName = (TextView) findViewById(R.id.characterNames);
        tvName.setText(names[position]);

        //Setting the listview in the main activity to an adapter
        lv=(ListView) findViewById(R.id.shortQuotesListView);
        lv.setAdapter(new QuoteListAdapter(this, position, names, quotes));

    }


    public void launchQuoteListActivity(View view) {
        Intent i = new Intent(this, QuoteListActivity.class);
        startActivity(i);
    }








        }
