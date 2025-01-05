package project.javafxproject.managerlogin;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import project.javafxproject.Main;
import project.javafxproject.part1.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class managercontroller {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    ObservableList<player2> data= FXCollections.observableArrayList();
    private Main main;
    private String club;
    private ScheduledExecutorService scheduler;
    @FXML
    private ImageView image;

    @FXML
    private TableView<player2> tableview;
    public void init(String s) throws IOException, ClassNotFoundException {
        Image img = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("d.png")));
        image.setImage(img);
        club=s;
        setupTableColumns();
        load();
        startScheduler();
    }
    private void startScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::load, 0, 1, TimeUnit.SECONDS);
    }
    private void stopScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
    private void setupTableColumns() {
        TableColumn<player2,String>namecol=new TableColumn<>("Name");
        namecol.setMinWidth(20);
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<player2,String>clubcol=new TableColumn<>("Club");
        clubcol.setMinWidth(20);
        clubcol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<player2,String>countrycol=new TableColumn<>("Country");
        countrycol.setMinWidth(20);
        countrycol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<player2,String>poscol=new TableColumn<>("Position");
        poscol.setMinWidth(20);
        poscol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<player2,Integer>salcol=new TableColumn<>("Salery");
        salcol.setMinWidth(20);
        salcol.setCellValueFactory(new PropertyValueFactory<>("salery"));

        TableColumn<player2,Integer>agecol=new TableColumn<>("Age");
        agecol.setMinWidth(20);
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<player2,Double>heigthcol=new TableColumn<>("Height");
        heigthcol.setMinWidth(20);
        heigthcol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<player2,Integer>jerseycol=new TableColumn<>("Jersey");
        jerseycol.setMinWidth(20);
        jerseycol.setCellValueFactory(new PropertyValueFactory<>("jerseynum"));
        TableColumn<player2, String> buttonCol = new TableColumn<>("Sell Player");
        buttonCol.setMinWidth(20);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));
        tableview.getColumns().add(namecol);
        tableview.getColumns().add(clubcol);
        tableview.getColumns().add(countrycol);
        tableview.getColumns().add(poscol);
        tableview.getColumns().add(salcol);
        tableview.getColumns().add(agecol);
        tableview.getColumns().add(heigthcol);
        tableview.getColumns().add(jerseycol);
        tableview.getColumns().add(buttonCol);
        tableview.setEditable(true);
    }




    private void load(){
        Platform.runLater(() -> {
            try {
                Socket socket=new Socket("localhost",1988);
                oos=new ObjectOutputStream(socket.getOutputStream());
                ois=new ObjectInputStream(socket.getInputStream());
                oos.writeObject("specific_club");
                ois.readObject();
                oos.writeObject(club);
                List<Player>lis = (List<Player>) ois.readObject();
                socket.close();
                if(oos!=null)   oos.close();
                if(ois!=null) ois.close();
                refreshTable(lis);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        });
    }

    private void refreshTable(List<Player> lis) {
        data.clear();
        for (Player p : lis) {
           String name=p.getName();
           String clb=p.getClub();
           String coutntry=p.getCountry();
           String pos=p.getPosition();
           int age=p.getAge();
           int salary=p.getSalery();
           int jersey=p.getJerseynum();
           double height=p.getHeight();
           player2 np=new player2(name,coutntry,age,height,clb,pos,jersey,salary,"Sell",event -> confirmsell(name));
           data.add(np);
        }
        tableview.setItems(data);
    }

    private void confirmsell(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Sale");
        alert.setHeaderText("Sell Player");
        alert.setContentText("Do you want to sell " + name);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    addToMarket(name);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public void setMain(Main main) {
        this.main=main;
    }
    public void addToMarket(String name) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
        oos.writeObject("add_to_market");
        ois.readObject();
       oos.writeObject(name);
       String s=(String)ois.readObject();
       if(s.equals("present")){
           String a="Duplicate sell";
           String b="Player is already in market";
           main.showAlert(a,b);
       }
        socket.close();
        if(oos!=null)   oos.close();
        if(ois!=null) ois.close();
    }

    public void logout(ActionEvent actionEvent) {
        stopScheduler();
        try {
            main.showlogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buyplayer(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        try {
            main.showbuyplayer(club);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
