package project.javafxproject;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import project.javafxproject.part1.*;
public class thread extends Thread{
    private String command,s;
    private Database client;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
//    thread(String club,Socket socket){
//        this.s=club;
//        this.socket=socket;
//        client=new Database();
//    }
   thread(Socket socket){
    this.socket=socket;
    client=new Database();
 }
    @Override
   public void run() {
        System.out.println("found thread");
        try {
            while (true) {
                try {
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    ois = new ObjectInputStream(socket.getInputStream());
                    command = (String) ois.readObject();
                    System.out.println(command);
                    if (command.equals("Add_player")) {
                        oos.writeObject("send player");
                        Player p = (Player) ois.readObject();
                        addplayer(p);
                    } else if (command.equals("max_age")) {
                        oos.writeObject("send club");
                        String s = (String) ois.readObject();
                        max_age(s);
                    } else if (command.equals("max_height")) {
                        oos.writeObject("send club");
                        String s = (String) ois.readObject();
                        max_height(s);
                    } else if (command.equals("max_salary")) {
                        oos.writeObject("send club");
                        String s = (String) ois.readObject();
                        max_salary(s);
                    } else if (command.equals("total_salary")) {
                        oos.writeObject("send club");
                        String s = (String) ois.readObject();
                        total_salary(s);
                    } else if (command.equals("search_by_name")) {
                        oos.writeObject("send name");
                        String s = (String) ois.readObject();
                        search_by_name(s);
                    } else if (command.equals("search_by_position")) {
                        oos.writeObject("send position");
                        String s = (String) ois.readObject();
                        search_by_position(s);
                    } else if (command.equals("search_by_salaryrange")) {
                        oos.writeObject("send min salary");
                        int mn = (Integer) ois.readObject();
                        oos.writeObject("send max salary");
                        int mx = (Integer) ois.readObject();
                        search_by_salaryrange(mn, mx);
                    } else if (command.equals("countrywise_count")) {
                        countrywisecount();
                    } else if (command.equals("search_by_clubandcountry")) {
                        oos.writeObject("send country");
                        String country = (String) ois.readObject();
                        oos.writeObject("send Club");
                        String club = (String) ois.readObject();
                        search_by_clubandcountry(country, club);
                    } else if (command.equals("save_file")) {
                        save_file();
                    } else if (command.equals("new_manager")) {
                        oos.writeObject("send club name");
                        String club = (String) ois.readObject();
                        oos.writeObject("send password");
                        String pass = (String) ois.readObject();
                        add_manager(club, pass);
                    } else if (command.equals("new_login")) {
                        oos.writeObject("send club name");
                        String club = (String) ois.readObject();
                        oos.writeObject("send password");
                        String pass = (String) ois.readObject();
                        new_login(club, pass);
                    }
                    else if (command.equals("specific_club")) {
                        oos.writeObject("send club name");
                        String club = (String) ois.readObject();
                        specific_club(club);
                    }
                    else if (command.equals("add_to_market")) {
                       // System.out.println("nam patha");
                        oos.writeObject("send player name");
                        String name = (String) ois.readObject();
                        add_to_market(name);
                    }
                    else if (command.equals("buy_player")) {
                       // System.out.println("nam patha");
                        oos.writeObject("send club name");
                        String club = (String) ois.readObject();
                        buyplayer(club);
                    }
                    else if (command.equals("remove_from_market")) {
                        // System.out.println("nam patha");
                        oos.writeObject("send  name");
                        String name = (String) ois.readObject();
                        oos.writeObject("Send club");
                        String club=(String)ois.readObject();
                        removefrommarket(name,club);
                    }
                    else {
                        String s = "ANY";
                        club(s);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            try {
                oos.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void removefrommarket(String name,String club) {
       Player p = new Player();
       for(Player pl:server.market){
           if(pl.getName().equalsIgnoreCase(name)){
               p=pl;
               break;
           }
       }
       server.market.remove(p);
       for(Player pl:server.db.players){
           if(pl.getName().equalsIgnoreCase(name)){
               pl.setClub(club);
               break;
           }
       }
    }

    private void buyplayer(String club) throws IOException {
       List<Player>lis=new ArrayList<>();
       for(Player p:server.market){
           if(club.equalsIgnoreCase(p.getClub()))continue;
           lis.add(p);
       }
       oos.writeObject(lis);
    }

    private void add_to_market(String name) throws IOException {
       List<Player>lis=server.db.searchbyname(name);
       Player pl=lis.getFirst();
       boolean ok=true;
       for(Player p:server.market){
           if(p.getName().equalsIgnoreCase(pl.getName())){
               ok=false;
               break;
           }

       }
       if(ok){
           server.market.add(pl);
           oos.writeObject("added");

       }
       else oos.writeObject("present");
    }

    private void specific_club(String club) throws IOException, ClassNotFoundException {
       List<Player>lis=server.db.searchspecificclub(club);
       oos.writeObject(lis);
    }

    private void new_login(String club, String pass) throws IOException {
        if(server.idpass.containsKey(club)&&pass.equals(server.idpass.get(club))){
            oos.writeObject("correct");
        }
        else{
            oos.writeObject("wrong");
        }
    }

    private void add_manager(String club, String pass) throws IOException {
       if(!server.idpass.containsKey(club)){
           server.idpass.put(club,pass);
           oos.writeObject("added");
       }
       else{
           oos.writeObject("exist");
       }
    }


    private void save_file() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/user/javafxproject/src/main/java/project/javafxproject/part1/players.txt"));

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/javafxproject/src/main/java/project/javafxproject/idpass.txt"));
        for (Map.Entry<String, String> entry : server.idpass.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }

        BufferedWriter bw2 = new BufferedWriter(new FileWriter("C:/Users/user/javafxproject/src/main/java/project/javafxproject/market.txt"));
        try {
            for (Player player : server.market) {
                bw2.write(player.tonewString());
                bw2.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving data to file");
        }
        writer.close();
        bw2.close();
        server.db.savefile(bw);
        bw.close();
    }

    private void search_by_clubandcountry(String country, String club) {
        List<Player>lis=server.db.searchbyclubandcountry(country,club);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void countrywisecount() throws IOException {
       HashMap<String,Integer>lis=server.db.countrywiseplayercount();
       oos.writeObject(lis);
    }

    private void search_by_salaryrange(int mn, int mx) {
        List<Player>lis=server.db.searchbysaleryrange(mn,mx);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void search_by_position(String s) {
        List<Player>lis=server.db.searchbyposition(s);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void search_by_name(String s) {
        List<Player>lis=server.db.searchbyname(s);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void total_salary(String s) {
        int tot=server.db.totalyearlysalary(s);
      //  String t=toString();
        System.out.println(tot);
        try {
            oos.writeObject(tot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void max_salary(String s) {
        List<Player>lis=server.db.playerwithmaxsalery(s);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void max_height(String s) {
        List<Player>lis=server.db.playerwithmaxheight(s);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void max_age(String s) {
       List<Player>lis=server.db.playerwithmaxage(s);
        try {
            oos.writeObject(lis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addplayer(Player p) throws IOException {
       boolean f=server.db.does_exist(p.getName());
       if(f){
           oos.writeObject("no");
       }
       else {
           server.db.addPlayer(p);
           oos.writeObject("yes");
       }
    }

    public void club(String s){
            for(Player p:server.db.players){
            if(p.getClub().equals(s)||s.equals("ANY")){
              client.addPlayer(p);
            }
        }
        try {
            oos.writeObject(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }

}
