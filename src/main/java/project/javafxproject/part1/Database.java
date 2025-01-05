package project.javafxproject.part1;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Database implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public List<Player> players;

    public Database() {
        players = new ArrayList<>();
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

    public List<Player> searchbyname(String name) {
        List<Player>p=new ArrayList<>();
        for (Player player : players) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
               // System.out.println(player);
                p.add(player);
            }
        }
        
        return p;
    }

    public boolean does_exist(String name) {
        for (Player player : players) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public List<Player> searchbyclubandcountry(String country, String club) {
        List<Player>p=new ArrayList<>();
        if (club.equals("ANY")) {
            for (Player player : players) {
                if (player.getCountry().toLowerCase().equals(country.toLowerCase())) {
                   p.add(player);
                }
            }
        } else {
            for (Player player : players) {
                if (player.getCountry().equalsIgnoreCase(country) && player.getClub().equalsIgnoreCase(club)) {
                    p.add(player);
                }
            }
        }
      return  p;
    }

    public List<Player> searchbyposition(String position) {
        List<Player>p=new ArrayList<>();
        for (Player player : players) {
            if (player.getPosition().toLowerCase().equals(position.toLowerCase())) {
               p.add(player);
            }
        }
       return p;
    }

    public List<Player> searchbysaleryrange(int min, int max) {
        List<Player>p=new ArrayList<>();
        for (Player player : players) {
            if (player.getSalery() >= min && player.getSalery() <= max) {
               p.add(player);
            }
        }
       return p;
    }

    public HashMap<String,Integer> countrywiseplayercount() {
        HashMap<String, Integer> count = new HashMap<>();
        for (Player player : players) {
            if (count.containsKey(player.getCountry().toLowerCase())) {
                count.put(player.getCountry().toLowerCase(), count.get(player.getCountry().toLowerCase()) + 1);
            } else {
                count.put(player.getCountry().toLowerCase(), 1);
            }
        }
        return count;
    }

    public List<Player> playerwithmaxsalery(String club) {
        int mx = 0;
        List<Player>lis=new ArrayList<>();
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                if(player.getSalery()>mx){
                    mx=player.getSalery();
                }
            }
        }
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                if(player.getSalery()==mx){
                   lis.add(player);
                }
            }
        }
        return lis;
       
    }

    public List<Player> playerwithmaxage(String club) {
        int mx = 0;
        List<Player>lis=new ArrayList<>();
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
               if(player.getAge()>mx){
                    mx=player.getAge();
               }
            }
        }
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
               if(player.getAge()==mx){
                   lis.add(player);
               }
            }
        }
        return lis;
        
    }

    public List<Player> playerwithmaxheight(String club) {
        List<Player>lis=new ArrayList<>();
        double mx = 0;
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                if(player.getHeight()>mx){
                    mx=player.getHeight();
                }
            }
        }
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                if(player.getHeight()==mx){
                    lis.add(player);
                }
            }
        }
        return lis;
    }

    public int totalyearlysalary(String club) {
        int ans = 0;
        boolean found = false;
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                ans += player.getSalery();
                found = true;
            }
        }
       if(found)return ans;
       return -1;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    void display() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public void savefile(BufferedWriter bw) {
        try {
            for (Player player : players) {
                bw.write(player.tonewString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving data to file");
        }
    }

    public List<Player> searchspecificclub(String club) {
        List<Player>lis=new ArrayList<>();
        for (Player player : players) {
            if (player.getClub().toLowerCase().equals(club.toLowerCase())) {
                    lis.add(player);
            }
        }
        return lis;
    }
}
