package com.example.sqlitemvp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class UpdateDBAssyncTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> contextWeakReference;
    private int position;
    private boolean b;

    public UpdateDBAssyncTask(Context context, int position, boolean b) {
        this.contextWeakReference = new WeakReference<>(context);
        this.position = position;
        this.b = b;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SQLiteDatabase database = new NotesDBHelper(contextWeakReference.get()).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesDBSchema.NotesTable.Cols.CHECK, b ? 1 : 0);
        String[] selectionArgs = { String.valueOf(position) };
        int count = database.update(
                NotesDBSchema.NotesTable.NAME,
                values,
                "_id = ?",
                selectionArgs);
        database.close();
        return null;
    }
}
