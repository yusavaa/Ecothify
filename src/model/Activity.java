package model;

public class Activity {

    private String creator;
    private String name;
    private String description;
    private String location;
    private String date;
    private String imgPath;
    private Member[] participant;
    private boolean validated = false;

    public Activity(String creator, String name, String description, String location, String date, String imgPath, int capacity) {
        this.creator = creator;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.imgPath = imgPath;
        participant = new Member[capacity];
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Member[] getParticipantList() {
        return participant;
    }

    public Member getParticipant(int index) {
        return participant[index];
    }

    public void addParticipant(int index, Member member) {
        participant[index] = member;
    }

    public int getTotalParticipant() { // Return total not null participant
        int total = 0;
        for (int i = 0; i < participant.length; i++) {
            if (participant[i] != null) {
                total++;
            }
        }
        return total;
    }

    public int getParticipantLenght() { // Return total participant include null participant
        return participant.length;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

}
