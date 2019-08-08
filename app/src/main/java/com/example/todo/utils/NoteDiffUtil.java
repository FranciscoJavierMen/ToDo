package com.example.todo.utils;

import androidx.recyclerview.widget.DiffUtil;

import com.example.todo.entities.noteEntity;

import java.util.List;

public class NoteDiffUtil extends DiffUtil.Callback{
    List<noteEntity> oldNoteList;
    List<noteEntity> newNoteList;
    public NoteDiffUtil(List<noteEntity> oldNoteList, List<noteEntity> newNoteList) {
        this.oldNoteList = oldNoteList;
        this.newNoteList = newNoteList;
    }

    @Override
    public int getOldListSize() {
        return oldNoteList.size();
    }

    @Override
    public int getNewListSize() {
        return newNoteList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNoteList.get(oldItemPosition).getId() == newNoteList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNoteList.get(oldItemPosition).equals(newNoteList.get(newItemPosition));
    }

    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
