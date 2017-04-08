package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.GroupOneOne;
import poc.springbootexample.models.Group.GroupOneOneDao;
import poc.springbootexample.models.User.UserOneOne;
import poc.springbootexample.models.User.UserOneOneDao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class IndexOneOneController {

    @Autowired
    private UserOneOneDao userDao;

    @Autowired
    private GroupOneOneDao groupDao;

    @RequestMapping("/OneToOne")
    public ModelAndView home(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, String> msgMap = new HashMap<>();
        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<UserOneOne> users = null;
        try {
            users = userDao.findAll();
        } catch (JpaSystemException e) {
            System.out.println("exception: " + e);
            model.replace("msg","Error: More than one user has been added to the same group");
        }
        model.put("users", users);

        Iterable<GroupOneOne> groups = null;
        try {
            groups = groupDao.findAll();
        } catch (JpaSystemException e) {
            System.out.println("exception: " + e);
            model.replace("msg","Error: More than one user has been added to the same group");
        }
        model.put("groups", groups);

        return new ModelAndView("homeOneOne","model",model);
    }

    @RequestMapping("/addUserOneToOne")
    public ModelAndView addUser(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<GroupOneOne> groups = groupDao.findAll();
        model.put("groups", groups);

        return new ModelAndView("addUserOneToOne","model",model);
    }

    @RequestMapping("/addGroupOneToOne")
    public ModelAndView addGroup(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        return new ModelAndView("addGroupOneToOne", "model", model);
    }

}
