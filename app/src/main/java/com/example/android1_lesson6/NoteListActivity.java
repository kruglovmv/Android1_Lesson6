package com.example.android1_lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1_lesson6.data.Constants;
import com.example.android1_lesson6.data.Note;
import com.example.android1_lesson6.data.NoteList;
import com.example.android1_lesson6.data.Repo;
import com.example.android1_lesson6.recycler.NotesAdapter;

public class NoteListActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {

    Repo noteslist = NoteList.getInstance();
    RecyclerView list;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        adapter = new NotesAdapter();
        fillList();

        adapter.setNotes(noteslist.getAll());
        adapter.setOnNoteClickListener(this);
        list = findViewById(R.id.notes_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

    }

    private void fillList() {
        Intent intent = getIntent();
        if ((intent != null) && (noteslist.getSizeRepo() != 0)) {
            Note note = (Note) intent.getSerializableExtra(Constants.NOTE_FOR_EDIT);
            if (note.getId() == -1) {
                noteslist.create(note);
            } else {
                noteslist.update(note);
            }
        } else {

            noteslist.create(new Note("Понедельник", ""));
            noteslist.create(new Note("Вторник", ""));
            noteslist.create(new Note("Среда", ""));
            noteslist.create(new Note("Четверг", ""));
            noteslist.create(new Note("Пятница", ""));
            noteslist.create(new Note("Суббота", ""));
            noteslist.create(new Note("Воскресенье", ""));
        }
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
        intent.putExtra(Constants.NOTE_FOR_EDIT, note);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}