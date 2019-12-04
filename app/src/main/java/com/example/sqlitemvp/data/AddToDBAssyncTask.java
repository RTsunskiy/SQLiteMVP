package com.example.sqlitemvp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.sqlitemvp.presentation.activity.MainActivity;

import java.lang.ref.WeakReference;

    public class AddToDBAssyncTask extends AsyncTask<Void, Void, Void> {

        private String title;
        private WeakReference<MainActivity> mainActivityWeakReference;
        private final static int DEFAULT_STATUS = 0;

        public AddToDBAssyncTask(MainActivity activity, String title) {
            this.mainActivityWeakReference = new WeakReference<>(activity);
            this.title = title;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase db = new NotesDBHelper(mainActivityWeakReference.get()).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NotesDBSchema.NotesTable.Cols.NOTE, title);
            values.put(NotesDBSchema.NotesTable.Cols.CHECK, DEFAULT_STATUS);
            long newRowId = db.insert(NotesDBSchema.NotesTable.NAME, null, values);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mainActivityWeakReference.get().updateRecyclerView();
        }
    }

