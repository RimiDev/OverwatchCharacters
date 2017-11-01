package cs.dawson.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Max on 10/23/2017.
 */

public class shortQuoteWindow extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_shortquotes);

        int position = getIntent().getExtras().getInt("position");
        String[] names = getIntent().getExtras().getStringArray("names");
        String[] quotes = getIntent().getExtras().getStringArray("quotes");
        int[] images = getIntent().getExtras().getIntArray("images");


        TextView tvName = (TextView) findViewById(R.id.characterNames);
        tvName.setText(names[position]);

        //ImageView img = (ImageView) findViewById(R.id.characterImgs);
        //img.setImageResource(images[position]);

        ListView lvshortQuotes = (ListView) findViewById(R.id.shortQuotesListView);

    }








        }
