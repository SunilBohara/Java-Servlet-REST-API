package UserApi;

import DAO.UsersDAO;
import DaoImpl.UserDAOImpl;
import Model.Users;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/users/*")
public class UserServlet extends HttpServlet {

    private static Gson GSON = null;
    private static UsersDAO usersDAO = null;

    static {
        GSON = new Gson();
        usersDAO = new UserDAOImpl();
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String userJson = GSON.toJson(obj);
        PrintWriter out = response.getWriter();
        out.println(userJson);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.setContentType("application/json");
            String userJson = GSON.toJson(usersList);
            PrintWriter out = response.getWriter();
            out.println(userJson);
            out.flush();
            return;
        }

        String[] splits = pathInfo.split("/");

        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String userId = splits[1];
        boolean found = false;

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == Integer.parseInt(userId)) {
                found = true;
                break;
            }
        }

        if (found) {
            Users singleUser = usersDAO.getUserById(Integer.parseInt(userId));
            sendAsJson(response, singleUser);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String payload = buffer.toString();
            System.out.println(payload);
            Users createUser = GSON.fromJson(payload, Users.class);
            usersDAO.createUser(createUser);
            response.setStatus(200);
            sendAsJson(response, createUser);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public String getServletInfo() {
        return "Rest Api Demo...";
    }// </editor-fold>

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");

        if (splits.length != 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String userId = splits[1];
        boolean found = false;

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == Integer.parseInt(userId)) {
                found = true;
                break;
            }
        }

        if (found) {
            usersDAO.deleteUser(Integer.parseInt(userId));
            resp.setStatus(200);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");

        if (splits.length != 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String userId = splits[1];
        boolean found = false;

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == Integer.parseInt(userId)) {
                found = true;
                break;
            }
        }

        if (found) {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String payload = buffer.toString();

            Users user = GSON.fromJson(payload, Users.class);

            usersDAO.updateUser(Integer.parseInt(userId), user);
            resp.setStatus(200);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
