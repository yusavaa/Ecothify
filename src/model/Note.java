package model;

import java.time.LocalDate;

public class Note {

    private String username;
    private String tittle;
    private LocalDate date;
    private int targetIndex;

    public Note(String username, String tittle, LocalDate date, int targetIndex) {
        this.username = username;
        this.tittle = tittle;
        this.date = date;
        this.targetIndex = targetIndex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

}
