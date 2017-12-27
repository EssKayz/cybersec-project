/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sec.project.Address;

/**
 *
 * @author ColdFish
 */
public class InterfDao implements Dao<Address, Integer> {

    private Database database;

    public InterfDao(Database database) {
        this.database = database;
    }

    @Override
    public Address findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Address WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");

        Address o = new Address(name, address, id);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Address> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Address");

        ResultSet rs = stmt.executeQuery();
        List<Address> addressess = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");

            addressess.add(new Address(name, address, id));
        }

        rs.close();
        stmt.close();
        connection.close();

        return addressess;
    }

    public void addAddress(String name, String address) throws SQLException {
        Connection con = database.getConnection();
        con.setAutoCommit(false);
        Statement stmt = null;
        String query = "INSERT INTO Address(name, address) VALUES ('" + name + "', '" + address + "');";
        stmt = con.createStatement();
        int rows = stmt.executeUpdate(query);
        System.out.println(rows + " rows updated");
        stmt.executeBatch();
        con.commit();
        stmt.close();
        con.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Address WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
    
}
