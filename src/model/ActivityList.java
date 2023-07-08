package model;

import java.util.ArrayList;
import util.XMLController;

public class ActivityList {

    private ArrayList<Activity> activityList = new ArrayList<>();

    public ActivityList() {
        loadActivityList();
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void saveActivityList() {
        XMLController.saveToXML(activityList, "Activity.xml");
    }

    public void loadActivityList() {
        activityList = (ArrayList<Activity>) XMLController.loadFroamXML(activityList, "Activity.xml");
    }

    public Activity getActivity(int index) {
        return activityList.get(index);
    }

    public int getActivityIndex(int index) {
        return activityList.indexOf(activityList.get(index));
    }

    public void addNewActivity(String creator, String name, String description, String location, String date, String imgPath, int capacity) {
        activityList.add(new Activity(creator, name, description, location, date, imgPath, capacity));
        saveActivityList();
    }

    public void removeActivity(int index) {
        activityList.remove(index);
        saveActivityList();
    }

}
