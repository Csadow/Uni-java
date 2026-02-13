package database;

import StuffnStaff.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClothingDAO {
    public boolean insertShirt(Shirt shirt){
        String sql = "INSERT INTO clothing (clothing_id, name, price, \"size\", clothing_type, sleeve_length, waist_length)" +
                "VALUES (?, ?, ?, ?, 'SHIRT', ?, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return false;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shirt.getId());
            statement.setString(2, shirt.getName());
            statement.setDouble(3, shirt.getPrice());
            statement.setInt(4, shirt.getSize());
            statement.setInt(5, shirt.getSleeveLength());

            int rowInserted = statement.executeUpdate();
            statement.close();

            if(rowInserted>0){
                System.out.println("Shirt inserted:"+ shirt.getName());
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Insert Shirt failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean insertShorts(Shorts shorts){
        String sql = "INSERT INTO clothing (clothing_id, name, price, \"size\", clothing_type, sleeve_length, waist_length)" +
                "VALUES (?, ?, ?, ?, 'SHORTS', NULL, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return false;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shorts.getId());
            statement.setString(2, shorts.getName());
            statement.setDouble(3, shorts.getPrice());
            statement.setInt(4, shorts.getSize());
            statement.setInt(5, shorts.getWaist());

            int rowInserted = statement.executeUpdate();
            statement.close();

            if(rowInserted>0){
                System.out.println("Shorts inserted:"+ shorts.getName());
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Insert Shorts failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Clothing> getAllClothing(){
        List<Clothing> clothingList = new ArrayList<>();
        String sql = "SELECT * FROM clothing ORDER BY clothing_id";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return clothingList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing != null){
                    clothingList.add(clothing);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved " + clothingList.size() + "clothing from database");
        }

        catch (SQLException e){
            System.out.println("Select all clothing failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }

        return clothingList;
    }

    public Clothing getClothingById(int clothingId){
        String sql = "SELECT * FROM clothing WHERE clothing_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clothingId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                resultSet.close();
                statement.close();

                if(clothing !=null){
                    System.out.println("Found clothing with id: " + clothingId);
                }
                return  clothing;
            }
            System.out.println("No clothing found with id: " + clothingId);

            resultSet.close();
            statement.close();
        }
        catch (SQLException e){
            System.out.println("Select ID failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        
        return null;
    }

    public  List<Shirt> getAllShirts(){
        List<Shirt> shirts = new ArrayList<>();
        String sql = "SELECT * FROM clothing WHERE clothing_type = 'SHIRT' ORDER BY clothing_id";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return shirts;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing instanceof  Shirt){
                    shirts.add((Shirt) clothing);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved "+ shirts.size() + "shirts");
        }
        catch (SQLException e){
            System.out.println("Select shirts failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return shirts;
    }

    public  List<Shorts> getAllShorts(){
        List<Shorts> shorts = new ArrayList<>();
        String sql = "SELECT * FROM clothing WHERE clothing_type = 'SHORTS' ORDER BY clothing_id";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return shorts;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing instanceof  Shorts){
                    shorts.add((Shorts) clothing);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved "+ shorts.size() + "shorts");
        }
        catch (SQLException e){
            System.out.println("Select shorts failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return shorts;
    }

    public boolean updateShirts(Shirt shirt){
        String sql = "UPDATE clothing SET name = ?, price = ?, size = ?, sleeve_length = ?" +
                "WHERE clothing_id = ? AND clothing_type = 'SHIRT'";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shirt.getId());
            statement.setString(2, shirt.getName());
            statement.setDouble(3, shirt.getPrice());
            statement.setInt(4, shirt.getSize());
            statement.setInt(5, shirt.getSleeveLength());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if(rowsUpdated>0){
                System.out.println("Shirt updated" + shirt.getName());
                return true;
            } else {
                System.out.println("No shirt found with id:" + shirt.getId());
            }
        }
        catch (SQLException e){
            System.out.println("Update shirt failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean updateShorts(Shorts shorts){
        String sql = "UPDATE clothing SET name = ?, price = ?, size = ?, waist_length = ?" +
                "WHERE clothing_id = ? AND clothing_type = 'SHORTS'";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shorts.getId());
            statement.setString(2, shorts.getName());
            statement.setDouble(3, shorts.getPrice());
            statement.setInt(4, shorts.getSize());
            statement.setInt(5, shorts.getWaist());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if(rowsUpdated>0){
                System.out.println("Shorts updated" + shorts.getName());
                return true;
            } else {
                System.out.println("No shorts found with id:" + shorts.getId());
            }
        }
        catch (SQLException e){
            System.out.println("Update shorts failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteClothing(int clothing_id){
        String sql = "DELETE FROM clothing WHERE clothing_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clothing_id);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if(rowsDeleted>0){
                System.out.println("Clothing deleted (ID: " + clothing_id + ")");
                return true;
            } else {
                System.out.println("No clothing found with ID: " + clothing_id);
            }
        }
        catch (SQLException e){
            System.out.println("Delete failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public List<Clothing> searchByName(String name){
        List<Clothing> clothingList = new ArrayList<>();

        String sql = "SELECT * FROM clothing WHERE name LIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return clothingList;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing != null){
                    clothingList.add(clothing);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found" + clothingList.size() + "clothing matching '" + name + "'");
        }
        catch (SQLException e) {
            System.out.println("Failed search by name");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return clothingList;
    }

    public List<Clothing> searchByPriceRange(double minPrice, double maxPrice){
        List<Clothing> clothingList = new ArrayList<>();

        String sql = "SELECT * FROM clothing WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if(connection==null) return clothingList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing!=null){
                    clothingList.add(clothing);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + clothingList.size() + " clothing in price range" +
                    minPrice + " - " + maxPrice);
        }
        catch (SQLException e){
            System.out.println("Search with range price failed");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return clothingList;
    }

    public List<Clothing> searchByMinPrice(double minPrice){
        List<Clothing> clothingList = new ArrayList<>();

        String sql = "SELECT * FROM clothing WHERE price >= ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection==null) return clothingList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Clothing clothing = extractClothingFromResultSet(resultSet);
                if(clothing!=null){
                    clothingList.add(clothing);
                }
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e){
            System.out.println("Faield to search with minimal price");
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return clothingList;
    }

    private Clothing extractClothingFromResultSet(ResultSet resultSet) throws SQLException{
        int clothingId = resultSet.getInt("clothing_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int size = resultSet.getInt("size");
        String clothingType = resultSet.getString("clothing_type");

        Clothing clothing = null;

        if("SHIRT".equals(clothingType)){
            int sleeveLength = resultSet.getInt("sleevelength");
            clothing = new Shirt(clothingId, name, price, size, sleeveLength);

        }
        else if("SHORTS".equals(clothingType)){
            int waistLength = resultSet.getInt("waist_length");
            clothing = new Shorts(clothingId, size, price, name, waistLength);
        }
        return clothing;
    }

    public void displayAllClothing(){
        List<Clothing> clothingList = getAllClothing();

        System.out.println("---All clothing from database---");

        if(clothingList.isEmpty()){
            System.out.println("No clothing in database");
        } else {
            for(int i = 0; i < clothingList.size(); i++){
                Clothing s = clothingList.get(i);
                System.out.println((i + 1) + ". ");
                System.out.println("[" + s.getType() + "]");
                System.out.println(s.toString());
            }
        }
        System.out.println("-------------------------------");
    }


    public void demonstratePolymorphism(){
        List<Clothing> clothingList = getAllClothing();

        System.out.println("---Polymorphism---");

        if(clothingList.isEmpty()){
            System.out.println("No clothing to demonstrate");
        } else {
            for(Clothing s : clothingList){
                s.cloth();
            }
        }
        System.out.println("-----------------------------------");
    }

}
