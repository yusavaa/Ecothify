package model;

import java.time.LocalDate;
import java.util.ArrayList;
import util.XMLController;

public class NoteList {
    
    private ArrayList<Note> noteList = new ArrayList<>();

    public NoteList() {
        loadNoteList();
    }

    public ArrayList<Note> getNoteList() {
        return noteList;
    }

    public void saveNoteList() {
        XMLController.saveToXML(noteList, "Note.xml");
    }

    public void loadNoteList() {
        noteList = (ArrayList<Note>) XMLController.loadFroamXML(noteList, "Note.xml");
    }
    
    public Note getNote(int index) {
        return noteList.get(index);
    }

    public void addNewNote(String username, String tittle, LocalDate date, int index) {
        noteList.add(new Note(username, tittle, date, index));
        saveNoteList();
    }

    public void removeNote(int index) {
        noteList.remove(index);
        saveNoteList();
    }

}
