package com.example.sqlitemvp.presentation.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sqlitemvp.data.pojo.Note;
import com.example.sqlitemvp.presentation.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.sqlitemvp.R;
import com.example.sqlitemvp.presentation.IMainView;
import com.example.sqlitemvp.presentation.presenter.Presenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements IMainView {

    private Presenter presenter;
    private RecyclerView toDoRecyclerView;
    private FloatingActionButton addTaskFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }

    @Override
    public void setToDoList(List<Note> notes) {
        if (!notes.isEmpty()) {
            RecyclerAdapter adapter = new RecyclerAdapter(this, notes);
            toDoRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void updateRecyclerView() {
        presenter.readTaskTable();
    }

    private void initPresenter() {
        presenter = new Presenter(this);
        presenter.readTaskTable();
    }

    private void initView() {
        toDoRecyclerView = findViewById(R.id.main_recycler);
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addTaskFab = findViewById(R.id.fab);
        addTaskFab.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == addTaskFab.getId()) {
            createAlertDialog();
        }
    }

    private void createAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(getResources().getString(R.string.alert_dialog_title));
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton(getResources().getString(R.string.yes), (dialogInterface, i) -> {
            if (!input.getText().toString().isEmpty()) {
                presenter.addTaskToTable(input.getText().toString());
                dialogInterface.cancel();
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_edit_text), Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.cancel());
        alertDialog.show();
    }

}
