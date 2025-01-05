package project.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.part1.*;
import java.io.IOException;

public class playerSearchController {

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
    void clubandcountry(ActionEvent event) {
        try {
            main.showclubandcountrysearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void countrywisecount(ActionEvent event) {
        try {
            main.showcountrywise();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
    void playername(ActionEvent event) {
        try {
            main.shownamesearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void position(ActionEvent event) {
        try {
            main.showpositionsearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void salaryrange(ActionEvent event) {
        try {
            main.showsalaryrange();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
