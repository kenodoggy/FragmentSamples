package com.kenodoggy.dialogfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SampleDialogFragment.OnDialogDismissed {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button hello = (Button) findViewById(R.id.activity_main_btn_hello);
        Button goodbye = (Button) findViewById(R.id.activity_main_btn_goodbye);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(SampleDialogFragment.HELLO);
            }
        });

        goodbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(SampleDialogFragment.GOODBYE);
            }
        });
    }

    public void showDialog(String salutation) {
        SampleDialogFragment.newInstance(salutation).show(getFragmentManager(), SampleDialogFragment.TAG);
    }

    @Override
    public void onDialogDismissed(String whichSalutation) {
        if (whichSalutation.equals(SampleDialogFragment.GOODBYE)) {
            Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
        }
    }
}
