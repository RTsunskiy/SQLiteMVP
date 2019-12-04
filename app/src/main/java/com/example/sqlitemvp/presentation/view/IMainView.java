package com.example.sqlitemvp.presentation.view;

import com.example.sqlitemvp.data.pojo.Note;

import java.util.List;

public interface IMainView {

    void setToDoList(List<Note> notes);

    void updateRecyclerView();
}
