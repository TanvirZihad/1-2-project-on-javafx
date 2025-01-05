package project.javafxproject.search_by_player;
import project.javafxproject.Main;
import project.javafxproject.part1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.javafxproject.part1.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class salaryrangecontroller {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    @FXML
    public TextField maxtosearch,mintosearch;
    @FXML
    private TableView<Player> tableView;
    ObservableList<Player> data;
    private Main main;
    private int mx;
    private  int mn;
    public void init(){
        TableColumn<Player,String>namecol=new TableColumn<>("Name");
        namecol.setMinWidth(20);
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player,String>clubcol=new TableColumn<>("Club");
        clubcol.setMinWidth(20);
        clubcol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player,String>countrycol=new TableColumn<>("Country");
        countrycol.setMinWidth(20);
        countrycol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player,String>poscol=new TableColumn<>("Position");
        poscol.setMinWidth(20);
        poscol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player,Integer>salcol=new TableColumn<>("Salery");
        salcol.setMinWidth(20);
        salcol.setCellValueFactory(new PropertyValueFactory<>("salery"));

        TableColumn<Player,Integer>agecol=new TableColumn<>("Age");
        agecol.setMinWidth(20);
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player,Double>heigthcol=new TableColumn<>("Height");
        heigthcol.setMinWidth(20);
        heigthcol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player,Integer>jerseycol=new TableColumn<>("Jersey");
        jerseycol.setMinWidth(20);
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
        oos.writeObject("search_by_salaryrange");
        ois.readObject();
        oos.writeObject(mn);
        ois.readObject();
        oos.writeObject(mx);
        List<Player>lis=(List<Player>) ois.readObject();
        if(lis.isEmpty()){
            String a="Player Not found";
            String b="No such player with this salary range";
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
        mx=Integer.parseInt(maxtosearch.getText());
        mn=Integer.parseInt(mintosearch.getText());
        load();
    }
    public void setMain(Main main) {
        this.main=main;
    }
    @FXML
    public void reset(ActionEvent actionEvent) {
        data.clear();
        maxtosearch.setText(null);
        mintosearch.setText(null);
    }

    public void quit(ActionEvent actionEvent) throws IOException {
        main.showplayersearch();
    }
}
