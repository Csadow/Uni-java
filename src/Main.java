import StuffnStaff.Clothing;
import database.DatabaseConnection;
import database.ClothingDAO;
import Menu.Menu;
import Menu.ClothingMenu;
import StuffnStaff.Shirt;
import StuffnStaff.Shorts;

import javax.xml.crypto.Data;
import  java.rmi.ConnectIOException;
import java.sql.Connection;
import  java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){
        ClothingDAO clothingDAO = new ClothingDAO();
        List<Clothing> clothingList = clothingDAO.getAllClothing();
        for(Clothing clothing: clothingList){
            System.out.println(clothing);
        }
    }
}