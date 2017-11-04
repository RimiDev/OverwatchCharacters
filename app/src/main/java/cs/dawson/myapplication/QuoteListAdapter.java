package cs.dawson.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import cs.dawson.entities.OverwatchCharacter;

import static android.content.Context.MODE_PRIVATE;


/**
 * The following class is the Adapter for the QuoteListActivity.
 * It will display the quotes of the given character and each quote
 * is clickable which will start another activity.
 */
public class QuoteListAdapter extends BaseAdapter {
    private Context context;
    private String nameId;
    private String birthName;
    private String blurb;
    private String dateOfbirth;
    private String url;
    private String[] quotes;
    private String imgUri;
    private String dateAdded;

    private static LayoutInflater inflater=null;


    public QuoteListAdapter(Context activity, String name, String bName, String blurby, String dateOfB, String urlLink, String[] quotez, String imageUri, String dateAdd) {
        context=activity;
        nameId = name;
        birthName = bName;
        blurb = blurby;
        dateOfbirth = dateOfB;
        url = urlLink;
        quotes = quotez;
        imgUri = imageUri;
        dateAdded = dateAdd;

        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {

        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.character_shortquotes, null);
        holder.tv= (TextView) rowView.findViewById(R.id.shortQuotes);
        // using a String variable that will hold the first 30 characters of a
        // quote if the quote is greater than 30 characters in total.
        String thirtyCharsQuote = quotes[position];

        if (quotes[position].length() > 30)
            thirtyCharsQuote = quotes[position].substring(0, 30) + "...";

        holder.tv.setText(thirtyCharsQuote); // Sets up the character name that you selected to title
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(v.getContext(), QuoteActivity.class);
                Intent.putExtra("nameId",nameId);
                Intent.putExtra("birthName", birthName); //Puts the character name array into intent
                Intent.putExtra("blurb", blurb); //Puts the character imgs array into intent
                Intent.putExtra("dateOfBirth", dateOfbirth);
                Intent.putExtra("url", url);
                Intent.putExtra("quote", quotes[position]); // puts the selected quote into intent
                Intent.putExtra("imageUri", imgUri);
                Intent.putExtra("dateAdded", dateAdded);
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}