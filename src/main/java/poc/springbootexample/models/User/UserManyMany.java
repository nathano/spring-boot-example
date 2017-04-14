package poc.springbootexample.models.User;

import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupManyMany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "usersManyMany",uniqueConstraints = {
        @UniqueConstraint(columnNames = "NAME")})
public class UserManyMany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "ROLE")
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_GROUP", joinColumns = {
            @JoinColumn(name = "USER_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "GROUP_ID")
    })
    private Set<GroupManyMany> groups = new HashSet<>();

    @Version
    @Column(name = "version")
    private long version;

    public UserManyMany() {}

    public UserManyMany(long id) {
        this.id = id;
    }

    public UserManyMany(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Set<GroupManyMany> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupManyMany> groups) {
        this.groups = groups;
    }
}
