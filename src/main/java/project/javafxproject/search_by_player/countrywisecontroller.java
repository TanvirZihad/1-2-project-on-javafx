package project.javafxproject.search_by_player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.javafxproject.Main;
import project.javafxproject.part1.*;
public class countrywisecontroller {
    private ObjectOutputStream oos;
   private ObjectInputStream ois;
   private  Main main;
    @FXML
    private TableView<Pair<String,Integer>> tableView;
    ObservableList<Pair<String,Integer>> data;
    public void init() throws IOException, ClassNotFoundException {
        TableColumn<Pair<String,Integer>,String>namecol=new TableColumn<>("Country");
        namecol.setPrefWidth(350);;
        namecol.setCellValueFactory(new PropertyValueFactory<>("key"));
        TableColumn<Pair<String,Integer>,String>clubcol=new TableColumn<>("Count");
        clubcol.setPrefWidth(350);
        clubcol.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableView.getColumns().add(namecol);
        tableView.getColumns().add(clubcol);
        tableView.setEditable(true);
        load();
    }
    public void load() throws IOException, ClassNotFoundException {
        data=FXCollections.observableArrayList();
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois =new ObjectInputStream(socket.getInputStream());
        oos.writeObject("countrywise_count");
        HashMap<String,Integer>lis=(HashMap<String, Integer>) ois.readObject();
        for(Map.Entry<String,Integer>entry:lis.entrySet()){
           data.add(new Pair<>(convertstring(entry.getKey()), entry.getValue()));
        }
        tableView.setItems(data);
    }
    public void setMain(Main main) {
        this.main=main;
    }
    @FXML
    public void quit(ActionEvent actionEvent) throws IOException {
        main.showplayersearch();
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
