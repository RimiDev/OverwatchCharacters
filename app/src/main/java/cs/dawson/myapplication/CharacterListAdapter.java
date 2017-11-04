package cs.dawson.myapplication;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StringLoader;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cs.dawson.entities.OverwatchCharacter;

/**
 * The following class is the Adapter for the MainActivity.
 * It will display each image icon with the name of the character on the image
 * that we have read from the database and passed to the contructor.
 */
public class CharacterListAdapter extends BaseAdapter {
    Context context;
    private List<OverwatchCharacter> allCharacters;
    private List<String> iconImgsUri;
    private List<String> imgsUri;


    private static LayoutInflater inflater=null;


    public CharacterListAdapter(Context activity, List<OverwatchCharacter> allChars, List<String> iconImgsUrl, List<String> imgsUrl) {
        context=activity;
        allCharacters = allChars;
        iconImgsUri = iconImgsUrl;
        imgsUri = imgsUrl;

        Log.d("AllcharsSize", allCharacters.size()+"");
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return allCharacters.size();
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
        Log.d("Reach getView" , allCharacters.size()+"");
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.character_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.characterNames);
        holder.tv.setTextColor(Color.WHITE);
        holder.img=(ImageView) rowView.findViewById(R.id.characterImgs);
        holder.tv.setText(allCharacters.get(position).getNameId());
        Log.d("holderTV",allCharacters.get(position).getNameId());

        FirebaseAuth mFirebaseAuth  =  FirebaseAuth.getInstance();
        // login as user
        mFirebaseAuth.signInWithEmailAndPassword("someuserfordb@hotmail.com", "android123");

        StorageReference sref = FirebaseStorage.getInstance().getReferenceFromUrl(iconImgsUri.get(position));
        Glide.with(context).using(new FirebaseImageLoader()).load(sref).into(holder.img);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverwatchCharacter characterClicked = allCharacters.get(position);
                Intent Intent = new Intent(v.getContext(), CharacterListActivity.class);
                Intent.putExtra("nameId",characterClicked.getNameId());
                Intent.putExtra("birthName", characterClicked.getBirthName()); //Puts the character name array into intent
                Intent.putExtra("blurb", characterClicked.getBlurb()); //Puts the character imgs array into intent
                Intent.putExtra("dateOfbirth", characterClicked.getDateOfBirth());
                Intent.putExtra("url", characterClicked.getInfoUrl());
                Intent.putExtra("quotes", characterClicked.getQuotes());
                Intent.putExtra("dateAdded", characterClicked.getDateAdded());
                Intent.putExtra("imageUri", imgsUri.get(position));
                v.getContext().startActivity(Intent);
            }

        });
        return rowView;
    }

}