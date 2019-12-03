package com.example.groupproject;


import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

class ReportRepository {

    private ReportDao reportDao;
    private LiveData<List<Report>> allReports;

    ReportRepository(Application application) {
        ReportRoomDatabase db = ReportRoomDatabase.getDatabase(application);
        reportDao = db.reportDao();
        allReports = reportDao.getAlphabetizedReports();
    }

    LiveData<List<Report>> getAllReports() {
        return allReports;
    }

    void insert(Report report) {
        ReportRoomDatabase.databaseWriteExecutor.execute(() -> {
            reportDao.insert(report);
        });
    }
}
