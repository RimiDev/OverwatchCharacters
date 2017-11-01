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

import static cs.dawson.myapplication.MainActivity.logIt;


public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] names;
    String[] quotes;
    int [] image;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, String[] characterNames, int[] characterImgs, String[] characterQuotes) {
        context=mainActivity;
        names=characterNames;
        quotes=characterQuotes;
        image=characterImgs;
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
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.character_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(names[position]);
        holder.img.setImageResource(image[position]);
        logIt(image[position]+"");
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Toast.makeText(context, "You Clicked "+names[position], Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), characterWindow.class);
                Intent.putExtra("position", position);
                Intent.putExtra("names", names ); //Puts the character name array into intent
                Intent.putExtra("images", image); //Puts the character imgs array into intent
                Intent.putExtra("quotes", quotes);
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}