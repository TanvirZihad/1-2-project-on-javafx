package project.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.part1.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MenuController {
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
    void addplayer(ActionEvent event) {
        try {
            main.showaaddplayer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clubsearch(ActionEvent event) {
        try{
            main. showclubsearch();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        Socket socket=new Socket("localhost",1988);
         oos=new ObjectOutputStream(socket.getOutputStream());
         ois=new ObjectInputStream(socket.getInputStream());
         oos.writeObject("save_file");
         Platform.exit();
    }

    @FXML
    void managerlogin(ActionEvent event)  {
        try {
            main.showlogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void playersearch(ActionEvent event) {
        try{
            main. showplayersearch();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
