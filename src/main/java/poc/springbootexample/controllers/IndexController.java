package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.User;
import poc.springbootexample.models.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public ModelAndView home(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, String> msgMap = new HashMap<>();
        if (msg != null) {
            if (msg.equals("0")) {
                msg = "Successfully added user";
                msgMap.put("val", msg);
                msgMap.put("type", "true");
            } else {
                msg = "Failed to add user";
                msgMap.put("val", msg);
                msgMap.put("type", "false");
            }

            model.put("msg", msgMap);
        } else {
            model.put("msg", "");
        }

        Iterable<User> users = userDao.findAll();
        model.put("users", users);

        return new ModelAndView("home","model",model);
    }

    @RequestMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath()
                + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }



}