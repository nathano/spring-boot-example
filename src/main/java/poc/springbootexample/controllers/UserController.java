package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.Group;
import poc.springbootexample.models.Group.GroupDao;
import poc.springbootexample.models.User.User;
import poc.springbootexample.models.User.UserDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;


    @RequestMapping("/create")
    @ResponseBody
    public ModelAndView create(String email, String name, Role roleVal, Long groupVal) {
        if (groupVal == null) {
            return new ModelAndView("redirect:/addUser", "msg", "Please select a group");
        }
        if (name.isEmpty()) {
            return new ModelAndView("redirect:/addUser", "msg", "Please enter a name");
        }
        Group foundGroup = groupDao.findOne(groupVal);
        User user = new User(email, name, roleVal, foundGroup);
        try {
            userDao.save(user);
        } catch (Exception e) {
            String msg = "Failed to add user";
            return new ModelAndView("/","msg",msg);
        }
        String msg = "Successfully added user: " + user.getName();
        return new ModelAndView("redirect:/","msg",msg);
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ModelAndView delete(Long userId) {
        if (userId == null) { return new ModelAndView("redirect:/","msg","Please select a user"); }

        try {
            userDao.delete(userId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/","msg","Failed to delete user");
        }

        return new ModelAndView("redirect:/","msg","User successfully deleted");
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User successfully updated!";
    }


}
