package poc.springbootexample.models.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import poc.springbootexample.models.User.UserManyMany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "groupsManyMany")
public class GroupManyMany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserManyMany> users;

    @Column(name = "GROUP_TYPE", insertable = false, updatable = false)
    @JsonIgnore
    private String groupType;

    public GroupManyMany() {}

    public GroupManyMany(long id) {
        this.id = id;
    }

    public GroupManyMany(String groupName) {
       this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Set<UserManyMany> getUsers() {
        return users;
    }

    public void setUsers(Set<UserManyMany> users) {
        this.users = users;
    }
}
