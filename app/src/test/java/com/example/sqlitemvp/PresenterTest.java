package com.example.sqlitemvp;

import com.example.sqlitemvp.presentation.view.MainActivity;
import com.example.sqlitemvp.presentation.presenter.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    @Mock
    private MainActivity mainActivity;


    private Presenter presenter;


    @Before
    public void setUp() {
        presenter = new Presenter(mainActivity);
    }

    @Test
    public void addTaskToTable() {
        String testNoteName = "test note";
        verify(presenter).addTaskToTable(testNoteName);
        verify(presenter).readTaskTable();
    }

}
