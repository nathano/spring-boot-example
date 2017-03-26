package poc.springbootexample.models;

import poc.springbootexample.config.Role;

import javax.persistence.*;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID", nullable = false)
    private long id;

    @Column(name = "GROUP_NAME", nullable = false)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
