package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.AdminGroup;
import poc.springbootexample.models.Group.Group;
import poc.springbootexample.models.Group.GroupDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class GroupController {

    @Autowired
    private GroupDao groupDao;


    @RequestMapping("/createGroup")
    @ResponseBody
    public ModelAndView createGroup(String groupName) {
        if (groupName.isEmpty()) {
            return new ModelAndView("redirect:/addGroup", "msg", "Please enter a group name");
        }
        Group group = new AdminGroup(groupName);
        try {
            groupDao.save(group);
        } catch (Exception ex) {
            String msg = "Failed to add group";
            return new ModelAndView("/","msg",msg);
        }
        String msg = "Successfully added group: " + group.getGroupName();
        return new ModelAndView("redirect:/","msg",msg);
    }

    @RequestMapping("/deleteGroup")
    @ResponseBody
    public ModelAndView deleteGroup(Long groupId) {
        if (groupId == null) { return new ModelAndView("redirect:/","msg","Please select a group"); }

        try {
            groupDao.delete(groupId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/","msg","Failed to delete group");
        }

        return new ModelAndView("redirect:/","msg","Group successfully deleted");
    }


}
