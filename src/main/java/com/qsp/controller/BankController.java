package com.qsp.controller;

import com.qsp.model.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankController {
    static Connection con;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qsp", "postgres", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveBank(Bank b) throws SQLException{
        PreparedStatement ps = con.prepareStatement("insert into bank values(?, ? ,?)");
                ps.setInt(1,b.getBid());
                ps.setString(2,b.getName());
                ps.setString(3,b.getLoc());
                ps.execute();
        System.out.println("Bank is added.");
    }

    public Bank fetchBank(int id) throws SQLException{
        PreparedStatement ps = con.prepareStatement("select * from bank where bid = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Bank b = new Bank();
        while (rs.next()){
            b.setBid(rs.getInt("bid"));
            b.setName(rs.getString("name"));
            b.setLoc(rs.getString("loc"));
        }
        return b;
    }

    public void deleteBank(int id) throws SQLException{
        if(fetchBank(id).getBid() == id){
            PreparedStatement ps = con.prepareStatement("delete from bank where bid = ?");
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Bank is deleted");
        }
    }

    public void updateBank(int id, String loc) throws SQLException{
        PreparedStatement ps = con.prepareStatement("update bank set loc  = ? where bid = ?");
        ps.setString(1,loc);
        ps.setInt(2,id);
        ps.execute();
        System.out.println("Location is updated.");
    }

    public List<Bank> fetchAll() throws SQLException{
        List<Bank> l = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("select * from bank");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Bank b = new Bank();
            b.setBid(rs.getInt("bid"));
            b.setName(rs.getString("name"));
            b.setLoc(rs.getString("loc"));
            l.add(b);
        }
        return l;
    }
}
