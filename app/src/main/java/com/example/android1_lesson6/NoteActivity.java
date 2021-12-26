package com.example.android1_lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android1_lesson6.data.Constants;
import com.example.android1_lesson6.data.Note;
import com.example.android1_lesson6.data.NoteList;
import com.google.android.material.button.MaterialButton;

import static com.example.android1_lesson6.data.Constants.NOTE_FOR_EDIT;

public class NoteActivity extends AppCompatActivity {
    Note note = new Note();
    EditText titleNote;
    EditText noteDescription;
    MaterialButton saveButton;
    MaterialButton exitButton;
    private Integer id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initNote();
        initButton();
    }

    private void initNote() {
        Intent intent = getIntent();
        if (intent != null) {
            note = (Note) intent.getSerializableExtra(NOTE_FOR_EDIT);
            if(note!=null) {
                titleNote = findViewById(R.id.title_note);
                titleNote.setText(note.getTitle());
                noteDescription = findViewById(R.id.note);
                noteDescription.setText(note.getNote());
                id = note.getId();
            }
        }
    }

    private void initButton() {
        saveButton = findViewById(R.id.button_save);
        exitButton = findViewById(R.id.button_exit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setId(id);
                note.setNote(noteDescription.getText().toString());
                note.setTitle(titleNote.getText().toString());
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleNote = findViewById(R.id.title_note);
                noteDescription = findViewById(R.id.note);
                Note note = new Note (titleNote.getText().toString(), noteDescription.getText().toString(), id);
                Intent backIntent = new Intent(NoteActivity.this, NoteListActivity.class);
                backIntent.putExtra(NOTE_FOR_EDIT, note);
                startActivity(backIntent);
            }
        });
    }
}