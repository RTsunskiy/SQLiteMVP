package com.example.sqlitemvp.presentation.presenter;

import com.example.sqlitemvp.data.AddToDBAssyncTask;
import com.example.sqlitemvp.data.ReadFromDBAssyncTask;
import com.example.sqlitemvp.presentation.activity.MainActivity;

import java.lang.ref.WeakReference;

public class Presenter {
    private WeakReference<MainActivity> mainActivityWeakReference;

    public void clear() {
        mainActivityWeakReference.clear();
    }

    public Presenter(MainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    public void addTaskToTable(String title) {
        new AddToDBAssyncTask(mainActivityWeakReference.get(), title).execute();
    }

    public void readTaskTable() {
        new ReadFromDBAssyncTask(mainActivityWeakReference.get()).execute();
    }
}
