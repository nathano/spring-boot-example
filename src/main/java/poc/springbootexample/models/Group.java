package poc.springbootexample.models;

import poc.springbootexample.config.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public Group() {}

    public Group(long id) {
        this.id = id;
    }

    public Group(String groupName) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
