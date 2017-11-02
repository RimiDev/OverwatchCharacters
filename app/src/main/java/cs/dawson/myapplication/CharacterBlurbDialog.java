package cs.dawson.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by 1537681 on 11/2/2017.
 */

public class CharacterBlurbDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_blurb) // This would be the blurb from the database
                .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Exit the dialog
                        dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
