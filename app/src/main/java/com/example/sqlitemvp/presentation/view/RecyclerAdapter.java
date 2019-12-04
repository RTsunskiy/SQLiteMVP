package com.example.sqlitemvp.presentation.view;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitemvp.R;
import com.example.sqlitemvp.data.CheckListener;
import com.example.sqlitemvp.data.DeleteFromDBAssyncTask;
import com.example.sqlitemvp.data.pojo.Note;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ToDoHolder> {

    private Context context;
    private List<Note> noteList;

    public RecyclerAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = new ArrayList<>(noteList);
    }

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToDoHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.statusCheckBox.setChecked(note.getStatus());
        holder.statusCheckBox.setOnCheckedChangeListener(new CheckListener(context, note));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class ToDoHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener{

        TextView titleTextView;
        CheckBox statusCheckBox;

        ToDoHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            titleTextView = itemView.findViewById(R.id.file_name_tv);
            statusCheckBox = itemView.findViewById(R.id.checkBox);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.menu1) {
                new DeleteFromDBAssyncTask(context, noteList.get(getAdapterPosition()).getId()).execute();
                return true;
            }
            return false;
        }
    }
}

