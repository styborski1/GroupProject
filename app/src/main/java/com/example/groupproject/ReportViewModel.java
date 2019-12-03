package com.example.groupproject;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ReportViewModel extends AndroidViewModel {

    private ReportRepository repository;

    private LiveData<List<Report>> mAllReports;

    public ReportViewModel (Application application) {
        super(application);
        repository = new ReportRepository(application);
        mAllReports = repository.getAllReports();
    }

    LiveData<List<Report>> getAllReports() { return mAllReports; }

    void insert(Report report) { repository.insert(report); }
}
