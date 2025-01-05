package project.javafxproject.managerlogin;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import project.javafxproject.Main;
import project.javafxproject.part1.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class buyplayercontroller {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    ObservableList<player2> data= FXCollections.observableArrayList();
    @FXML
    private ImageView image;

    @FXML
    private TableView<player2> tableview;
    private Main main;
    private boolean ok=true;
    private String club;
    private ScheduledExecutorService scheduler;
    @FXML
    void back(ActionEvent event) throws IOException, ClassNotFoundException {
        stopScheduler();
      main.showclublogin(club);
    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void init(String s) throws IOException, ClassNotFoundException, InterruptedException {
        club=s;
        setupTableColumns();

        socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
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
        TableColumn<player2,String> namecol=new TableColumn<>("Name");
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
        TableColumn<player2, String> buttonCol = new TableColumn<>("Buy Player");
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

    private void load()  {
        Platform.runLater(() -> {
            try {
                Socket socket=new Socket("localhost",1988);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
                oos.writeObject("buy_player");
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
            player2 np=new player2(name,coutntry,age,height,clb,pos,jersey,salary,"Buy",event -> {
                try {
                    buy(name);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            data.add(np);
        }
        tableview.setItems(data);
    }

    public void buy(String name) throws IOException, ClassNotFoundException {
       Socket socket=new Socket("localhost",1988);
       oos=new ObjectOutputStream(socket.getOutputStream());
       ois=new ObjectInputStream(socket.getInputStream());
       oos.writeObject("remove_from_market");
       ois.readObject();
       oos.writeObject(name);
       ois.readObject();
       oos.writeObject(club);
       socket.close();
       if(oos!=null)   oos.close();
       if(ois!=null) ois.close();
   }

}
