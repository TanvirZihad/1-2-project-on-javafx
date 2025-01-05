package project.javafxproject.search_by_player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.javafxproject.Main;
import project.javafxproject.part1.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class searchbynamecontroller {
  private ObjectOutputStream oos;
  private ObjectInputStream ois;
    @FXML
    private TextField nametosearch;
    @FXML
    private TableView<Player> tableView;
    ObservableList<Player> data;
    private Main main;
    private String name;
    private int w=50;
    public void init(){
        TableColumn<Player,String>namecol=new TableColumn<>("Name");
        namecol.setMinWidth(w);
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player,String>clubcol=new TableColumn<>("Club");
        clubcol.setMinWidth(w);
        clubcol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player,String>countrycol=new TableColumn<>("Country");
        countrycol.setMinWidth(w);
        countrycol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player,String>poscol=new TableColumn<>("Position");
        poscol.setMinWidth(w);
        poscol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player,Integer>salcol=new TableColumn<>("Salery");
        salcol.setMinWidth(w);
        salcol.setCellValueFactory(new PropertyValueFactory<>("salery"));

        TableColumn<Player,Integer>agecol=new TableColumn<>("Age");
        agecol.setMinWidth(w);
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player,Double>heigthcol=new TableColumn<>("Height");
        heigthcol.setMinWidth(w);
        heigthcol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player,Integer>jerseycol=new TableColumn<>("Jersey");
        jerseycol.setMinWidth(w);
        jerseycol.setCellValueFactory(new PropertyValueFactory<>("jerseynum"));
        tableView.getColumns().add(namecol);
        tableView.getColumns().add(clubcol);
        tableView.getColumns().add(countrycol);
        tableView.getColumns().add(poscol);
        tableView.getColumns().add(salcol);
        tableView.getColumns().add(agecol);
        tableView.getColumns().add(heigthcol);
        tableView.getColumns().add(jerseycol);
        tableView.setEditable(true);
    }

    public void load() throws IOException, ClassNotFoundException {
        data=FXCollections.observableArrayList();
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois =new ObjectInputStream(socket.getInputStream());
        oos.writeObject("search_by_name");
        ois.readObject();
        oos.writeObject(name);
        List<Player>lis=(List<Player>) ois.readObject();
        if(lis.isEmpty()){
            String a="Player Not found";
            String b="No such player with this name";
            main.showAlert(a,b);
        }
        else {
            for (Player p : lis) {
                data.add(p);
            }
            tableView.setItems(data);
        }
    }
    @FXML
   public void search(ActionEvent event) throws IOException, ClassNotFoundException {
        name=nametosearch.getText();
        load();
    }
    public void setMain(Main main) {
        this.main=main;
    }
    @FXML
    public void reset(ActionEvent actionEvent) {
        nametosearch.setText(null);
    }

    public void quit(ActionEvent actionEvent) throws IOException {
        main.showplayersearch();
    }
}
