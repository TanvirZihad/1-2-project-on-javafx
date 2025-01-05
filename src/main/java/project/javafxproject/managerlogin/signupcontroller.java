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

public class signupcontroller {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    @FXML
    private TextField clubname;

    @FXML
    private ImageView image;

    @FXML
    private Label misreenter;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField repass;

    @FXML
    private Label sameclub;
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
       repass.setText(null);
       misreenter.setText(null);
       sameclub.setText(null);
    }

    @FXML
    void signup(ActionEvent event) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
      String club=clubname.getText();
      String s=pass.getText();
      String t=repass.getText();
      if(s.equals(t)){
          misreenter.setText(null);
          sameclub.setText(null);
          oos.writeObject("new_manager");
          ois.readObject();
          oos.writeObject(club);
          ois.readObject();
          oos.writeObject(s);
          String f=(String)ois.readObject();
          if(f.equals("exist")){
              String a="Duplicate Club";
              String b="CLub with this name exist.";
              main.showAlert(a,b);
          }
          else{
              main.showlogin();
          }
      }
      else {
          String a="password mismatch";
          String b="Re entered password doesn't match the original one.";
         main.showAlert(a,b);
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
