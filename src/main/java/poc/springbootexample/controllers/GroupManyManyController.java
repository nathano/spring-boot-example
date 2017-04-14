package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.config.GroupManyManyForm;
import poc.springbootexample.models.Group.GroupManyMany;
import poc.springbootexample.models.Group.GroupManyManyDao;
import poc.springbootexample.models.User.UserManyManyDao;

import java.util.stream.Collectors;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class GroupManyManyController {

    @Autowired
    private GroupManyManyDao groupDao;

    @Autowired
    private UserManyManyDao userDao;


    @RequestMapping("/createGroupManyToMany")
    @ResponseBody
    public ModelAndView createGroup(@ModelAttribute("form") GroupManyManyForm form) {
        if (form.getGroupName().isEmpty()) {
            return new ModelAndView("redirect:/addGroupManyToMany", "msg", "Please enter a group name");
        }
        GroupManyMany group = new GroupManyMany(form.getGroupName());
        try {
            groupDao.save(group);
        } catch (Exception ex) {
            String msg = "Failed to add group";
            return new ModelAndView("/ManyToMany","msg",msg);
        }

        if (form.getUserVal() != null) {
            form.getUserVal().stream().map(userId -> userDao.findOne(userId)).collect(Collectors.toList()).stream().forEach(userManyMany -> {
                userManyMany.getGroups().add(group);
                userDao.save(userManyMany);
            });

            group.getUsers().addAll(form.getUserVal().stream().map(userId -> userDao.findOne(userId)).collect(Collectors.toList()));
            groupDao.save(group);
        }

        String msg = "Successfully added group: " + group.getGroupName();
        return new ModelAndView("redirect:/ManyToMany","msg",msg);
    }

    @RequestMapping("/deleteGroupManyToMany")
    @ResponseBody
    public ModelAndView deleteGroup(Long groupId) {
        if (groupId == null) { return new ModelAndView("redirect:/ManyToMany","msg","Please select a group"); }

        try {
            groupDao.delete(groupId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/ManyToMany","msg","Failed to delete group");
        }

        return new ModelAndView("redirect:/ManyToMany","msg","Group successfully deleted");
    }


}
