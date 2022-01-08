package com.example.android1_lesson6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_list_fragment_holder, new NotesListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void listPress() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_list_fragment_holder, new NoteFragment())
                .addToBackStack(null)
                .commit();
    }
}
