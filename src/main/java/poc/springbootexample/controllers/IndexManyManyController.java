package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.GroupManyMany;
import poc.springbootexample.models.Group.GroupManyManyDao;
import poc.springbootexample.models.User.UserManyMany;
import poc.springbootexample.models.User.UserManyManyDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class IndexManyManyController {

    @Autowired
    private UserManyManyDao userDao;

    @Autowired
    private GroupManyManyDao groupDao;

    @RequestMapping("/ManyToMany")
    public ModelAndView home(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, String> msgMap = new HashMap<>();
        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<UserManyMany> users = userDao.findAll();
        model.put("users", users);

        Iterable<GroupManyMany> groups = groupDao.findAll();
        model.put("groups", groups);

        return new ModelAndView("homeManyMany","model",model);
    }

    @RequestMapping("/addUserManyToMany")
    public ModelAndView addUser(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<GroupManyMany> groups = groupDao.findAll();
        model.put("groups", groups);

        return new ModelAndView("addUserManyToMany","model",model);
    }

    @RequestMapping("/addGroupManyToMany")
    public ModelAndView addGroup(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        return new ModelAndView("addGroupManyToMany", "model", model);
    }

}
