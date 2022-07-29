/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Users;
import java.util.List;

/**
 *
 * @author sunilbohara
 */
public interface UsersDAO {
    public List<Users> getAllUsers();
    public boolean deleteUser(int id);
    public boolean createUser(Users user);
    public Users getUserById(int id); 
    public boolean updateUser(int id,Users user);
}
