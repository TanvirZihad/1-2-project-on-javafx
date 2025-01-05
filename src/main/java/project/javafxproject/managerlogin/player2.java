package project.javafxproject.managerlogin;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.Serial;
import java.io.Serializable;

public class player2 implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name,country,position,club;
    private int jerseynum,age,salery;
    private double height;
    private final Button button;
    public player2(String name, String country, int age, double height, String club, String position, int jersey, int salery,String s, EventHandler<MouseEvent> buttonAction){
        this.name=name;
        this.country=country;
        this.age=age;
        this.height=height;
        this.club=club;
        this.position=position;
        this.jerseynum=jersey;
        this.salery=salery;
        this.button=new Button(s);
        button.setOnMouseClicked(buttonAction);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public int getJerseynum() {
        return jerseynum;
    }
    public void setJerseynum(int jerseynum) {
        this.jerseynum = jerseynum;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSalery() {
        return salery;
    }
    public void setSalery(int salery) {
        this.salery = salery;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public Button getButton(){
        return button;
    }
    public String toString(){
        if(this.jerseynum!=-1)  return "Player Name: "+name+", Country: "+country+", Age: "+age+", Height: "+height+", Club: "+club+", Position: "+position+", Jersey Number: "+jerseynum+", Salary: "+salery;
        else  return "Player Name: "+name+", Country: "+country+", Age: "+age+", Height: "+height+", Club: "+club+", Position: "+position+", Salary: "+salery;
    }
    public String tonewString(){
        if(this.jerseynum!=-1)  return name+","+country+","+age+","+height+","+club+","+position+","+jerseynum+","+salery;
        else  return name+","+country+","+age+","+height+","+club+","+position+",,"+salery;
    }

}
