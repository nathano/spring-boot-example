package poc.springbootexample.config;

import java.util.Set;

/**
 * Created by norner on 14/04/2017.
 */
public class GroupManyManyForm {
    private String groupName;
    private Set<Long> userVal;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Long> getUserVal() {
        return userVal;
    }

    public void setUserVal(Set<Long> userVal) {
        this.userVal = userVal;
    }
}
