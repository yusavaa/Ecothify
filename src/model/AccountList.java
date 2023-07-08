package model;

import java.util.ArrayList;
import util.XMLController;

public class AccountList {

    private ArrayList<Member> accounList = new ArrayList<>();
    private static Member loginAccount;

    public AccountList() {
        loadAccountList();
    }

    public ArrayList<Member> getAccountList() {
        return accounList;
    }

    public void saveAccountList() {
        XMLController.saveToXML(accounList, "Account.xml");
    }

    public void loadAccountList() {
        accounList = (ArrayList<Member>) XMLController.loadFroamXML(accounList, "Account.xml");
    }

    public Member getAccount(int index) {
        return accounList.get(index);
    }

    public void addNewAccount(String name, String city, String username, String password) {
        accounList.add(new Member(name, city, username, password));
        XMLController.saveToXML(accounList, "Account.xml");
    }

    public static Member getLoginAccount() {
        return loginAccount;
    }

    public static void setLoginAccount(Member loginAccount) {
        AccountList.loginAccount = loginAccount;
    }

    public String getRole() {
        return loginAccount.getROLE();
    }

}
