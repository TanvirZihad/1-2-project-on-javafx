package project.javafxproject.managerlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.Main;

import java.io.IOException;

public class logincontroller {

    @FXML
    private ImageView image;
    private Main main;

    @FXML
    void signin(ActionEvent event) {
        try {
            main.showsignin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void signup(ActionEvent event) {
        try {
            main.showsignup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void init() {
        Image img = new Image(Main.class.getResourceAsStream("f.png"));
        image.setImage(img);
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        main.showmenu();
    }
}
