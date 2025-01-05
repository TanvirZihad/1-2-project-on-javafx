package project.javafxproject;
import javafx.scene.control.Alert;
import project.javafxproject.addplayer.addplayercontroller;
import project.javafxproject.managerlogin.*;
import project.javafxproject.search_by_club.*;
import project.javafxproject.search_by_player.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import project.javafxproject.part1.*;
import project.javafxproject.search_by_player.countrywisecontroller;

public class Main extends Application{
    private Stage stage;
    public Stage getStage() {
        return stage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showmenu();

    }
   public void showmenu() throws IOException {
       System.out.println("yey");
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("menu.fxml"));
       Parent root = loader.load();

       MenuController controller = loader.getController();
       controller.init();
       controller.setMain(this);
       stage.setTitle("Menu");
       stage.setScene(new Scene(root, 700, 390));
       stage.show();
   }
    public void showplayersearch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("search_player.fxml"));
        Parent root = loader.load();
        playerSearchController controller = loader.getController();
        controller.init();
        controller.setMain(this);
        stage.setTitle("Search By Player");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showclubsearch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("search_club.fxml"));
        Parent root = loader.load();
        clubSearchController controller = loader.getController();
        controller.init();
        controller.setMain(this);
        stage.setTitle("Search By Club");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void shownamesearch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("player_name.fxml"));
        Parent root = loader.load();
        searchbynamecontroller controller = loader.getController();
        controller.setMain(this);
        controller.init();
        stage.setTitle("Search Player By Name");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showclubandcountrysearch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("clubandcountry.fxml"));
        Parent root = loader.load();

        searchclubandcountrycontroller controller = loader.getController();
        controller.setMain(this);
        controller.init();
        // Set the primary stage
        stage.setTitle("Search Player By Club and Country");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showpositionsearch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("position_search.fxml"));
        Parent root = loader.load();

        // Loading the controller
        positionsearchcontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Search Player By Position");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showsalaryrange() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("salary_search.fxml"));
        Parent root = loader.load();

        // Loading the controller
        salaryrangecontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Search Player By Salery");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showcountrywise() throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("countrywise.fxml"));
        Parent root = loader.load();

        // Loading the controller
        countrywisecontroller controller = loader.getController();
//        controller.load();
        controller.setMain(this);
        controller.init();
        // Set the primary stage
        stage.setTitle("Countrywise count");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showmaxsalary() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("max_salary.fxml"));
        Parent root = loader.load();

        // Loading the controller
        maxsalarycontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Player with Max salary");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showmaxage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("max_age.fxml"));
        Parent root = loader.load();

        // Loading the controller
        maxagecontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Player with Max age");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showmaxheight() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("max_height.fxml"));
        Parent root = loader.load();

        // Loading the controller
        maxheightcontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Player with Max height");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showtotalsalary() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("total_salary.fxml"));
        Parent root = loader.load();

        // Loading the controller
        totalsalarycontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Total salary of a club");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showaaddplayer() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("add_player.fxml"));
        Parent root = loader.load();

        // Loading the controller
        addplayercontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Add Player");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showlogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        logincontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Manager Login");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showsignup() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("signup.fxml"));
        Parent root = loader.load();

        // Loading the controller
        signupcontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showsignin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("signin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        signincontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init();
        // Set the primary stage
        stage.setTitle("Sign In");
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showclublogin(String club) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("clublogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        managercontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init(club);
        // Set the primary stage
        stage.setTitle(club);
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }
    public void showAlert(String title,String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
      //  alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlert2(String title,String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
     //   alert.setContentText("Player was added successfully");
        alert.showAndWait();
    }
    public void showbuyplayer(String club) throws IOException, ClassNotFoundException, InterruptedException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("buyplayer.fxml"));
        Parent root = loader.load();

        // Loading the controller
        buyplayercontroller controller = loader.getController();
        controller.setMain(this);
//        controller.load();
        controller.init(club);
        // Set the primary stage
        stage.setTitle(club);
        stage.setScene(new Scene(root, 700, 390));
        stage.show();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }



}
