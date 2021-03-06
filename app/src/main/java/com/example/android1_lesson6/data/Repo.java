package com.example.android1_lesson6.data;

import com.example.android1_lesson6.data.Note;

import java.util.List;

public interface Repo {
    public Integer create(Note note);
    public Note read(Integer id);
    public void update(Note note);
    public void delete(Integer id);
    public List<Note> getAll();
    public Integer getSizeRepo();
}
