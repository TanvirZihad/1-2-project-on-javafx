package project.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.part1.*;
import java.io.IOException;

public class clubSearchController {
    private Main main;
    public void setMain(Main main){
        this.main=main;
    }

    @FXML
    private ImageView image;
    public void init() {
        //     message.setText("Main Menu");
        Image img = new Image(Main.class.getResourceAsStream("f.png"));
        image.setImage(img);
    }

    @FXML
    void mainmenu(ActionEvent event) {
        try {
            main.showmenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void maxage(ActionEvent event) {
        try {
            main.showmaxage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void maxheight(ActionEvent event) {
        try {
            main.showmaxheight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void maxsalary(ActionEvent event) {
        try {
            main.showmaxsalary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void totalsalary(ActionEvent event) {
        try {
            main.showtotalsalary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
