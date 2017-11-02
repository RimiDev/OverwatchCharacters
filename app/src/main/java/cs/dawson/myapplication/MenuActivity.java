package cs.dawson.myapplication;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by 1537681 on 11/2/2017.
 */

public class MenuActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quotesmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                showAbout();
                return true;
            case R.id.random:
                doRandom();
                return true;
            case R.id.last:
                doLast();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAbout(){
        //Show the about screen
    }

    private void doRandom(){
        //Do something new
    }

    private void doLast(){
        //Do something
    }



}
