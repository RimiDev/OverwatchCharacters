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

import java.util.List;

import cs.dawson.entities.OverwatchCharacter;


public class CharacterListAdapter extends BaseAdapter {
    Context context;
    List<OverwatchCharacter> allCharacters;
    private static LayoutInflater inflater=null;


    public CharacterListAdapter(Context activity, List<OverwatchCharacter> allChars) {
        context=activity;
        allCharacters = allChars;
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
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.character_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.characterNames);
        holder.img=(ImageView) rowView.findViewById(R.id.characterImgs);
        holder.tv.setText(allCharacters.get(position).getNameId());
        //holder.img.setImageResource(image[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverwatchCharacter characterClicked = allCharacters.get(position);
                Toast.makeText(context, "You Clicked "+ characterClicked.getNameId(), Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), CharacterListActivity.class);
                Intent.putExtra("nameId",characterClicked.getNameId());
                Intent.putExtra("birthName", characterClicked.getBirthName()); //Puts the character name array into intent
                Intent.putExtra("blurb", characterClicked.getBlurb()); //Puts the character imgs array into intent
                Intent.putExtra("dateOfBirth", characterClicked.getDateOfBirth());
                Intent.putExtra("description", characterClicked.getDescription());
                Intent.putExtra("url", characterClicked.getInfoUrl());
                Intent.putExtra("quotes", characterClicked.getQuotes());
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}