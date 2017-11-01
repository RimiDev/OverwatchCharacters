package cs.dawson.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;

    public static int[] characterImgs = {R.drawable.dino1,R.drawable.dino2,R.drawable.dino3,R.drawable.dino4,R.drawable.dino5, R.drawable.dino1};

    //CANT SEEM TO BE ABLE TO GET REFERENCE TO STRING RESOURCES (SINCE ITS STATIC)...
    public static String[] characterNames = {"Pharah","McCree","Genji","Reaper","Lucio", "Tracer"};

    public static String[] characterShortQuotes = {"PharahDESC","McCreeDESC","GenjiDESC","ReaperDESC","LucioDESC", "TracerDESC"};

    public static String[] characterFullQuotes = {"PharahDESCF","McCreeDESCF","GenjiDESCF","ReaperDESCF","LucioDESCF", "TracerDESCF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        //Setting the listview in the main activity to an adapter
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CharacterListAdapter(this, characterNames ,characterImgs, characterShortQuotes));

    }


    public void launchCharacterWindow(View view) {
        Intent i = new Intent(this, CharacterListActivity.class);
        startActivity(i);
    }


    public static void logIt(String msg) {
        final String TAG = "OverWatchQuotes-------";
        Log.d(TAG, msg);
    }


}
