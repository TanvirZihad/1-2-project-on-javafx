package project.javafxproject.search_by_club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.Main;
import project.javafxproject.part1.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class totalsalarycontroller {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
   private Main main;
    @FXML
    private TextField clubtosearch;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtfield1;
    private String club;
    @FXML
    private TextField txtfield2;
    public void init() {
        //     message.setText("Main Menu");
        Image img = new Image(Main.class.getResourceAsStream("f.png"));
        image.setImage(img);
    }
    @FXML
    void calculate(ActionEvent event) throws IOException, ClassNotFoundException {
        club=clubtosearch.getText();
       //int x=main.user.totalyearlysalary(club);
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois =new ObjectInputStream(socket.getInputStream());
        oos.writeObject("total_salary");
        ois.readObject();
        oos.writeObject(club);
        int x=(Integer)ois.readObject();
        if(x==-1){
                String a="Club Not found";
                String b="No such club with this name";
                main.showAlert(a,b);
        }
        else {
            txtfield1.setText("Total Yearly Salary");
            txtfield2.setText(x + " $");
        }
    }

    @FXML
    void quit(ActionEvent event) {
        try {
            main.showclubsearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reset(ActionEvent event) {
        clubtosearch.setText(null);
        txtfield1.setText(null);
        txtfield2.setText(null);
    }
    public void setMain(Main main){
        this.main=main;
    }

}
