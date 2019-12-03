package com.example.groupproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_REPORT_ACTIVITY_REQUEST_CODE = 1;

    private ReportViewModel mReportViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReportListAdapter adapter = new ReportListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

        mReportViewModel.getAllReports().observe(this, new Observer<List<Report>>() {
            @Override
            public void onChanged(@Nullable final List<Report> reports) {
                adapter.setReports(reports);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewReportActivity.class);
                startActivityForResult(intent, NEW_REPORT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_REPORT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Report report = new Report(data.getStringExtra(NewReportActivity.EXTRA_REPLY));
            String title = data.getStringExtra("EXTRA_TITLE");
            String desc = data.getStringExtra("EXTRA_DESC");
            String city = data.getStringExtra("EXTRA_CITY");



            Report report = new Report(title,desc,city);

            mReportViewModel.insert(report);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
