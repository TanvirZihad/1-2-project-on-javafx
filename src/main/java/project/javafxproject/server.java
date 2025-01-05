package project.javafxproject;
import project.javafxproject.part1.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class server {

   public static Database db;
   private ServerSocket serverSocket;
  public static HashMap<String,String>idpass;
  public static List<Player> market;
   public server() throws IOException {
       System.out.println("Server shuru");
     db=new Database();
     idpass=new HashMap<>();
     market=new ArrayList<>();
     data_entry();
       try {
           serverSocket = new ServerSocket(1988);
           while (true) {
               Socket clientSocket = serverSocket.accept();
               serve(clientSocket);
           }
       } catch (Exception e) {
           System.out.println("Server starts:" + e);
       }
    }
   public void data_entry() throws IOException {
       BufferedReader br = new BufferedReader(new FileReader("C:/Users/user/javafxproject/src/main/java/project/javafxproject/part1/players.txt"));
       while(true){
           String line = br.readLine();
           if (line == null)
               break;
           String[] data = line.split(",");
           String name, country, club, pos;
           int age, jersey, salery;
           double height;
           name = data[0];
           country = data[1];
           age = Integer.parseInt(data[2]);
           height = Double.parseDouble(data[3]);
           club = data[4];
           pos = data[5];
           if (data[6].isEmpty()) {
               jersey = -1;
           } else
               jersey = Integer.parseInt(data[6]);
           salery = Integer.parseInt(data[7]);
           Player player = new Player(name, country, age, height, club, pos, jersey, salery);
           db.addPlayer(player);
       }
       BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/javafxproject/src/main/java/project/javafxproject/idpass.txt"));

           while (true) {
               String line= reader.readLine();
               if(line==null)break;
               String[] parts = line.split(",");
               if (parts.length == 2) {
                   idpass.put(parts[0], parts[1]);
               }
           }
       BufferedReader br2 = new BufferedReader(new FileReader("C:/Users/user/javafxproject/src/main/java/project/javafxproject/market.txt"));
       while(true){
           String line = br2.readLine();
           if (line == null)
               break;
           String[] data = line.split(",");
           String name, country, club, pos;
           int age, jersey, salery;
           double height;
           name = data[0];
           country = data[1];
           age = Integer.parseInt(data[2]);
           height = Double.parseDouble(data[3]);
           club = data[4];
           pos = data[5];
           if (data[6].isEmpty()) {
               jersey = -1;
           } else
               jersey = Integer.parseInt(data[6]);
           salery = Integer.parseInt(data[7]);
           Player player = new Player(name, country, age, height, club, pos, jersey, salery);
           market.add(player);
       }
           br.close();
           reader.close();
           br2.close();
   }
    public void serve(Socket clientSocket) throws IOException {
   //    thread t=new thread("ANY",clientSocket);
        thread t=new thread(clientSocket);
       t.start();
    }

    public static void main(String[] args) throws IOException {
        new server();
    }
}
