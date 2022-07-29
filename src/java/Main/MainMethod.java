/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DAO.UsersDAO;
import DaoImpl.UserDAOImpl;
import Model.Users;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sunilbohara
 */
public class MainMethod {
    public static void main(String[] args) {
        UsersDAO usersDAO = new UserDAOImpl();
        List<Users> users = usersDAO.getAllUsers();
        for(Users u : users) {
            System.out.println(u.getName());
        }

//        String pathInfo = "/2434/34535";
//        String[] splits = pathInfo.split("/");
//        
//        System.out.println(Arrays.toString(splits));
//
//        if (splits.length != 2) {
//            //System.out.println("0");
//            return;
//        }

        //String userId = splits[2];
        //System.out.println(userId);

    }
}
