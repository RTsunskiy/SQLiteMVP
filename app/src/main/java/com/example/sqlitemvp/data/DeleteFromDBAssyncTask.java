package com.example.sqlitemvp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.sqlitemvp.presentation.view.MainActivity;

import java.lang.ref.WeakReference;

public class DeleteFromDBAssyncTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> contextWeakReference;
    private int id;

    public DeleteFromDBAssyncTask(Context context, int id) {
        this.contextWeakReference = new WeakReference<>(context);
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SQLiteDatabase db = new NotesDBHelper(contextWeakReference.get()).getWritableDatabase();
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.delete(
                NotesDBSchema.NotesTable.NAME,
                "_id = ?",
                selectionArgs);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ((MainActivity) contextWeakReference.get()).updateRecyclerView();
    }
}
