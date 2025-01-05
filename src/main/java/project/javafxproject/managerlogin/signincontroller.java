package project.javafxproject.managerlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class signincontroller {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public Label wrongidpass;
    @FXML
    private TextField clubname;

    @FXML
    private ImageView image;
    @FXML
    private PasswordField pass;
    private Main main;

    @FXML
    void back(ActionEvent event) {
        try {
            main.showlogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reset(ActionEvent event) {
        clubname.setText(null);
        pass.setText(null);
        wrongidpass.setText(null);
    }

    @FXML
    void signin(ActionEvent event) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
        String club=clubname.getText();
        String s=pass.getText();
        oos.writeObject("new_login");
        ois.readObject();
        oos.writeObject(club);
        ois.readObject();
        oos.writeObject(s);
        String msg=(String)ois.readObject();
        System.out.println(msg);
        if(msg.equals("wrong")){
            String a="Incorrect Credentials";
            String b="The username and password you provided is not correct.";
            main.showAlert(a,b);
        }
        else{
          main.showclublogin(club);
        }
    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void init() {
        Image img = new Image(Main.class.getResourceAsStream("f.png"));
        image.setImage(img);
    }
}
