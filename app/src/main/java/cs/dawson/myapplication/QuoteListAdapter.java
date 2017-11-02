package cs.dawson.myapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import cs.dawson.entities.OverwatchCharacter;


public class QuoteListAdapter extends BaseAdapter {
    Context context;
    String nameId;
    String birthName;
    String blurb;
    String dateOfbirth;
    String description;
    String url;
    String[] quotes;

    private static LayoutInflater inflater=null;


    public QuoteListAdapter(Context activity, String name, String bName,  String blurby,  String dateOfB,  String desc,  String urlLink, String[] quotez) {
        context=activity;
        nameId = name;
        birthName = bName;
        blurb = blurby;
        dateOfbirth = dateOfB;
        description = desc;
        url = urlLink;
        quotes = quotez;

        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 0;
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
        holder.tv.setText(quotes[position]); // Sets up the character name that you selected to title
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked "+ quotes[position], Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), QuoteActivity.class);
                Intent.putExtra("nameId",nameId);
                Intent.putExtra("birthName", birthName); //Puts the character name array into intent
                Intent.putExtra("blurb", blurb); //Puts the character imgs array into intent
                Intent.putExtra("dateOfBirth", dateOfbirth);
                Intent.putExtra("description", description);
                Intent.putExtra("url", url);
                Intent.putExtra("quote", quotes[position]);
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}