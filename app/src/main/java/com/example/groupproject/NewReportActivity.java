package com.example.groupproject;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewReportActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.reportlistsql.REPLY";

    private EditText mEditTitleView;
    private EditText mEditDescView;
    private EditText mEditCityView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);
        mEditTitleView = findViewById(R.id.edit_title);
        mEditDescView = findViewById(R.id.edit_desc);
        mEditCityView = findViewById(R.id.edit_city);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                Bundle extras = new Bundle();

                if (TextUtils.isEmpty(mEditTitleView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTitleView.getText().toString();
                    String desc = mEditDescView.getText().toString();
                    String city = mEditCityView.getText().toString();

                    extras.putString("EXTRA_TITLE", title);
                    extras.putString("EXTRA_DESC", desc);
                    extras.putString("EXTRA_CITY", city);

                    replyIntent.putExtras(extras);

                    //replyIntent.putExtra(EXTRA_REPLY, report);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
