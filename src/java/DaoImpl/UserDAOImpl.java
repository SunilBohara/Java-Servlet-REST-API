/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import DAO.UsersDAO;
import DatabaseConnection.*;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sunilbohara
 */
public class UserDAOImpl implements UsersDAO {

    @Override
    public List<Users> getAllUsers() {
        List<Users> listOfUsers = new ArrayList<>();
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement("select * from users;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setStatus(rs.getString("status"));
                listOfUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfUsers;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement("delete from users where id = '" + id + "';");
            flag = pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public boolean createUser(Users user) {
        boolean flag = false;
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement("insert into users (id,name,email,gender,status)"
                    + " values('" + user.getId() + "','" + user.getName() + "','" + user.getEmail() + "','" + user.getGender() + "','" + user.getStatus() + "');");
            flag = pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public Users getUserById(int id) {
        Users user = null;
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement("select * from users where id = '" + id + "';");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("gender"), rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean updateUser(int id, Users user) {
        boolean flag = false;
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement("update users set id = '" + user.getId() + "', name = '" + user.getName() + "', email = '" + user.getEmail() + "', gender = '" + user.getGender() + "', status = '" + user.getStatus() + "' where id = '" + id + "';");
            flag = pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

}
