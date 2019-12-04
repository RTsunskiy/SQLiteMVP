package com.example.sqlitemvp.data;

import android.content.Context;
import android.widget.CompoundButton;

import com.example.sqlitemvp.data.pojo.Note;

public class CheckListener implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private Note note;

    public CheckListener(Context context, Note note) {
        this.context = context;
        this.note = note;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        new UpdateDBAssyncTask(context, note.getId(), b).execute();
    }
}
