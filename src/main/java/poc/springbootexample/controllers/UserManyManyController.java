package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupManyMany;
import poc.springbootexample.models.Group.GroupManyManyDao;
import poc.springbootexample.models.User.UserManyMany;
import poc.springbootexample.models.User.UserManyManyDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class UserManyManyController {

    @Autowired
    private UserManyManyDao userDao;

    @Autowired
    private GroupManyManyDao groupDao;


    @RequestMapping("/createManyToMany")
    @ResponseBody
    public ModelAndView create(String email, String name, Role roleVal, Long groupVal) {
        if (groupVal == null) {
            return new ModelAndView("redirect:/addUserManyToMany", "msg", "Please select a group");
        }
        if (name.isEmpty()) {
            return new ModelAndView("redirect:/addUserManyToMany", "msg", "Please enter a name");
        }
        GroupManyMany foundGroup = groupDao.findOne(groupVal);
        UserManyMany user = new UserManyMany(email, name, roleVal, foundGroup);
        try {
            userDao.save(user);
        } catch (Exception e) {
            String msg = "Failed to add user";
            return new ModelAndView("/ManyToMany","msg",msg);
        }
        String msg = "Successfully added user: " + user.getName();
        return new ModelAndView("redirect:/ManyToMany","msg",msg);
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/deleteUserManyToMany")
    @ResponseBody
    public ModelAndView delete(Long userId) {
        if (userId == null) { return new ModelAndView("redirect:/ManyToMany","msg","Please select a user"); }

        try {
            userDao.delete(userId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/ManyToMany","msg","Failed to delete user");
        }

        return new ModelAndView("redirect:/ManyToMany","msg","User successfully deleted");
    }

}
