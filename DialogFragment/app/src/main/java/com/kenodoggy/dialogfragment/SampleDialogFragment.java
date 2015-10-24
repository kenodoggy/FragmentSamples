package com.kenodoggy.dialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class SampleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "SampleDialogFragment";
    public static final String HELLO = "hello";
    public static final String GOODBYE = "goodbye";
    private static final String WHICH_SALUTATION = "salutation";

    private String mSalutation;
    private OnDialogDismissed mDialogDismissedCallback;

    public interface OnDialogDismissed {
        void onDialogDismissed(String whichSalutation);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mDialogDismissedCallback = (OnDialogDismissed)activity;
        } catch (ClassCastException cce) {
            Log.e("Error", getClass().getSimpleName() + ", calling Activity must implement OnDialogDismissed");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_sample_dialog, null);

        // set values based on which salutation (hello, goodbye) passed when this fragment was instantiated
        String titleText;
        ImageView image = (ImageView) view.findViewById(R.id.fragment_sample_dialog_imageview);
        if (mSalutation.equals(HELLO)) {
            titleText = getResources().getString(R.string.fragment_sample_dialog_txt_hello);
            image.setImageResource(R.drawable.scottish_fold_ds_400w);
        } else {
            titleText = getString(R.string.fragment_sample_dialog_txt_goodbye);
            image.setImageResource(R.drawable.kitten_wave);
        }

        // create custom title TextView
        TextView title = new TextView(getActivity());
        title.setText(titleText);
        title.setPadding(0, 40, 0, 0);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(getResources().getColor(R.color.colorAccent));
        title.setTextSize(30);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return(builder
                .setView(view)
                .setCustomTitle(title)
                .setPositiveButton(R.string.close, this)
                .create());
    }


    public static SampleDialogFragment newInstance(String salutation) {
        SampleDialogFragment fragment = new SampleDialogFragment();
        Bundle args = new Bundle();
        args.putString(WHICH_SALUTATION, salutation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSalutation = getArguments().getString(WHICH_SALUTATION);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // display a toast message on the screen when the dialog is dismissed for GOODBYE dialog only
        if (mSalutation.equals(GOODBYE)) {
            mDialogDismissedCallback.onDialogDismissed(GOODBYE);
        }
    }
}
