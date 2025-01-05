package project.javafxproject.addplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.javafxproject.Main;
import project.javafxproject.part1.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class addplayercontroller {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
   private Main main;
    @FXML
    private TextField agebox;

    @FXML
    private TextField clubbox;

    @FXML
    private TextField countrybox;

    @FXML
    private TextField heightbox;

    @FXML
    private ImageView image;

    @FXML
    private TextField jerseynumbox;

    @FXML
    private TextField namebox;

    @FXML
    private TextField posbox;

    @FXML
    private TextField salarybox;
    @FXML
    void addplayer(ActionEvent event) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois =new ObjectInputStream(socket.getInputStream());
        String name, country, club, position;
        int age, jerseynum, salary;
        double height;
        name=namebox.getText();
        country=countrybox.getText();
        club=clubbox.getText();
        position=posbox.getText();
        age=Integer.parseInt(agebox.getText());
        jerseynum=Integer.parseInt(jerseynumbox.getText());
        salary=Integer.parseInt(salarybox.getText());
        height=Double.parseDouble(heightbox.getText());
        name=convertstring(name);
        country=convertstring(country);
        position=convertstring(position);
        club=convertstring(club);
        Player player = new Player(name, country, age, height, club, position, jerseynum, salary);
        oos.writeObject("Add_player");
        ois.readObject();
        oos.writeObject(player);
        String s=(String)ois.readObject();
        if(s.equalsIgnoreCase("no")){
            String a="Player Exists";
            String b="Player with this name already exists";
            main.showAlert(a,b);
        }
        else{
            String a="Player Added";
            String b="Player was added successfully";
            main.showAlert2(a,b);
        }
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
     main.showmenu();
    }

    @FXML
    void reset(ActionEvent event) {
      namebox.setText(null);
        salarybox.setText(null);
        clubbox.setText(null);
        posbox.setText(null);
        countrybox.setText(null);
        agebox.setText(null);
        jerseynumbox.setText(null);
        heightbox.setText(null);
    }
    public void setMain(Main main){
        this.main=main;
    }
    public void init() {
        //     message.setText("Main Menu");
        Image img = new Image(Main.class.getResourceAsStream("f.png"));
        image.setImage(img);
    }
    public String convertstring(String s) {
        int f = 1;
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char y = s.charAt(i);
            if (f == 1) {

                if (y >= 'a' && y <= 'z')
                    y = (char) (y - 'a' + 'A');
                f = 0;
            } else if (f == 0) {
                if (y >= 'A' && y <= 'Z')
                    y = (char) (y + 'a' - 'A');
            }
            if (y == ' ')
                f = 1;
            t.append(y);

        }
        return t.toString();
    }
}
