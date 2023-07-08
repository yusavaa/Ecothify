package dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import model.AccountList;
import model.ActivityList;
import util.OpenImage;

public class DashboardController implements Initializable {

    private ActivityList activityList = new ActivityList();
    private AccountList accountList = new AccountList();
    private ObservableList<PieChart.Data> locations = FXCollections.observableArrayList();

    @FXML
    private ImageView heroImg, accountImg, top1Img, top2Img, top3Img, top4Img;

    @FXML
    private Label nameLabel, pointLabel, activeUsersLabel;

    @FXML
    private PieChart pieChart;

    public void setChart() {
        locations.add(new PieChart.Data("Bantul", 0));
        locations.add(new PieChart.Data("Gunung Kidul", 0));
        locations.add(new PieChart.Data("Kulon Progo", 0));
        locations.add(new PieChart.Data("Sleman", 0));
        locations.add(new PieChart.Data("Yogyakarta", 0));

        for (int i = 0; i < activityList.getActivityList().size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                if (activityList.getActivity(i).getLocation().equals(locations.get(j).getName())) {
                    locations.get(j).setPieValue(locations.get(j).getPieValue() + 1);
                }
            }
        }
        pieChart.setData(locations);
    }

    public void setAccountContributor() {
        if (AccountList.getLoginAccount() != null) {
            OpenImage.setImage(accountImg, "desain/profile/iconl3.png");
            nameLabel.setText(AccountList.getLoginAccount().getName());
            pointLabel.setText(Integer.toString(AccountList.getLoginAccount().getPoint()) + " Points");
        } else {
            OpenImage.setImage(accountImg, "desain/profile/guest.png");
        }
    }

    public void setTopContributor() {
        OpenImage.setImage(top1Img, "desain/profile/iconl1.png");
        OpenImage.setImage(top2Img, "desain/profile/iconp1.png");
        OpenImage.setImage(top3Img, "desain/profile/iconl2.png");
        OpenImage.setImage(top4Img, "desain/profile/iconp2.png");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenImage.setImage(heroImg, "desain/hero.jpg");
        int totalUsers = 275 + accountList.getAccountList().size();
        activeUsersLabel.setText(Integer.toString(totalUsers) + " Users");
        setChart();
        setAccountContributor();
        setTopContributor();
    }

}
