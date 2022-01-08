package com.example.android1_lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1_lesson6.data.Constants;
import com.example.android1_lesson6.data.Note;
import com.example.android1_lesson6.data.NoteList;
import com.example.android1_lesson6.data.Repo;
import com.example.android1_lesson6.recycler.NotesAdapter;

public class NotesListFragment extends Fragment implements NotesAdapter.OnNoteClickListener {
    Repo noteslist = NoteList.getInstance();
    RecyclerView list;
    NotesAdapter adapter;

    interface Controller {
        void listPress();
    }
    private Controller controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new NotesAdapter();
        fillList();


        adapter.setNotes(noteslist.getAll());
        adapter.setOnNoteClickListener(this);
        list = view.findViewById(R.id.fragment_notes_list_recycler);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapter);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.listPress();
            }
        });
    }

    private void fillList() {
//        Intent intent = getIntent();
//        if ((intent != null) && (noteslist.getSizeRepo() != 0)) {
//            Note note = (Note) intent.getSerializableExtra(Constants.NOTE_FOR_EDIT);
//            if (note.getId() == -1) {
//                noteslist.create(note);
//            } else {
//                noteslist.update(note);
//            }
//        } else {

            noteslist.create(new Note("Понедельник", ""));
            noteslist.create(new Note("Вторник", ""));
            noteslist.create(new Note("Среда", ""));
            noteslist.create(new Note("Четверг", ""));
            noteslist.create(new Note("Пятница", ""));
            noteslist.create(new Note("Суббота", ""));
            noteslist.create(new Note("Воскресенье", ""));
        }


    @Override
    public void onNoteClick(Note note) {
        controller.listPress();
    }
}

