package cs.dawson.myapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CharacterListAdapter extends BaseAdapter {
    Context context;
    String[] names;
    String[] shortQuotes;
    int [] image;
    private static LayoutInflater inflater=null;


    public CharacterListAdapter(Context activity, String[] characterNames, int[] characterImgs, String[] quotes) {
        context=activity;
        names=characterNames;
        image=characterImgs;
        shortQuotes = quotes;
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
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.character_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.characterNames);
        holder.img=(ImageView) rowView.findViewById(R.id.characterImgs);
        holder.tv.setText(names[position]);
        holder.img.setImageResource(image[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Toast.makeText(context, "You Clicked "+ names[position], Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), CharacterListActivity.class);
                Intent.putExtra("position", position);
                Intent.putExtra("names", names ); //Puts the character name array into intent
                Intent.putExtra("images", image); //Puts the character imgs array into intent
                Intent.putExtra("shortquotes", shortQuotes); //Puts the character short quotes array into Intent
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}