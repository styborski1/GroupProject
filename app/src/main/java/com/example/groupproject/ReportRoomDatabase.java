package com.example.groupproject;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Report.class}, version = 1, exportSchema = false)
public abstract class ReportRoomDatabase extends RoomDatabase {

    public abstract ReportDao reportDao();

    private static volatile ReportRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ReportRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReportRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReportRoomDatabase.class, "report_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more reports, just add them.
                ReportDao dao = INSTANCE.reportDao();
                dao.deleteAll();

                Report report = new Report("Hello", "Description 1", "Columbus", "22");
                dao.insert(report);
                report = new Report("World", "Description 2", "Dayton", "25");
                dao.insert(report);

            });
        }
    };
}
