package com.example.groupproject;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Report report);

    @Query("DELETE FROM report_table")
    void deleteAll();

    @Query("SELECT * from report_table ORDER BY title ASC")
    LiveData<List<Report>> getAlphabetizedReports();
}
