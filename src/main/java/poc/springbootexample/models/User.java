package poc.springbootexample.models;

import poc.springbootexample.config.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ROLE", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Group> groups;


    //TODO - play with jpa/hibernate annotations incl. cascading deletes
    //eg. add group (note that group is a reserved keyword )
    //if you delete a group, you delete all users from that group
    //one to many - groups to users
    //users can have many groups - bonus task

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name, Role role) {
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
