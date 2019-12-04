package com.example.sqlitemvp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import com.example.sqlitemvp.data.pojo.Note;
import com.example.sqlitemvp.presentation.activity.MainActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReadFromDBAssyncTask extends AsyncTask<Void, Void, List<Note>> {

    private WeakReference<MainActivity> mainActivityWeakReference;

    public ReadFromDBAssyncTask(MainActivity activity) {
        this.mainActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected List<Note> doInBackground(Void... voids) {
        SQLiteDatabase db = new NotesDBHelper(mainActivityWeakReference.get()).getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                NotesDBSchema.NotesTable.Cols.NOTE,
                NotesDBSchema.NotesTable.Cols.CHECK };
        Cursor cursor = db.query(NotesDBSchema.NotesTable.NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        List<Note> notes = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
                String note = cursor.getString(cursor.getColumnIndex(NotesDBSchema.NotesTable.Cols.NOTE));
                int check = cursor.getInt(cursor.getColumnIndex(NotesDBSchema.NotesTable.Cols.CHECK));
                boolean boolCheck = check == 1;
                Note task = new Note(id, note, boolCheck);
                notes.add(task);
            }
        } finally {
            cursor.close();
        }
        return notes;
    }

    @Override
    protected void onPostExecute(List<Note> notes) {
        mainActivityWeakReference.get().setToDoList(notes);
    }
}

