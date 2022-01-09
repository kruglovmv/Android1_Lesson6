package com.example.android1_lesson6.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1_lesson6.R;
import com.example.android1_lesson6.data.Note;
import com.example.android1_lesson6.data.Repo;
import com.example.android1_lesson6.recycler.NotesAdapter;

import java.io.Serializable;

import static com.example.android1_lesson6.data.Constants.LIST_NOTES_FOR_EDIT;

public class NotesListLandFragment extends NotesListFragment implements NotesAdapter.OnNoteClickListener{
    Repo noteslist;
    RecyclerView list;
    NotesAdapter adapter;

    public static NotesListLandFragment getInstance(Repo noteslist){
        NotesListLandFragment fragment = new NotesListLandFragment();
        Bundle args = new Bundle();
        args.putSerializable(LIST_NOTES_FOR_EDIT, (Serializable) noteslist);
        fragment.setArguments(args);
        return fragment;
    }
    private Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        if(context instanceof Controller){
            this.controller = (Controller)context;
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_land_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new NotesAdapter();
        Bundle args = getArguments();
        if(args.containsKey(LIST_NOTES_FOR_EDIT)){
            noteslist = (Repo) args.getSerializable(LIST_NOTES_FOR_EDIT);
        }
        adapter.setNotes(noteslist.getAll());
        adapter.setOnNoteClickListener(this);
        list = view.findViewById(R.id.fragment_notes_list_recycler);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapter);

    }

    @Override
    public void onNoteClick(Note note) {
        controller.listPress(note);
    }
}

