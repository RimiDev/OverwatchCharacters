package cs.dawson.myapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class QuoteListAdapter extends BaseAdapter {
    Context context;
    String[] names;
    String[] shortQuotes;
    int position;
    private static LayoutInflater inflater=null;


    public QuoteListAdapter(Context activity, int pos, String[] characterNames, String[] characterShortQuotes) {
        context=activity;
        position = pos;
        names = characterNames;
        shortQuotes = characterShortQuotes;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return names.length;
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
        holder.tv.setText(shortQuotes[position]); // Sets up the character name that you selected to title
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Toast.makeText(context, "You Clicked "+shortQuotes[position], Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), QuoteListActivity.class);
                Intent.putExtra("position", position); //Grabs the position in the array, the user clicked on.
                Intent.putExtra("names", names ); //Puts the character name array into intent
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}