package poc.springbootexample.models.Group;

import javax.persistence.*;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@DiscriminatorValue(value = "Admin")
public class AdminGroup extends  Group {

    private static final long serialVersionUID = -4791261083735661295L;

    public AdminGroup() {}

    public AdminGroup(String groupName) {
        setGroupName(groupName);
    }
}
