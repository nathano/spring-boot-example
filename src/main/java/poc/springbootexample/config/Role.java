package poc.springbootexample.config;

/**
 * Created by norner on 26/03/2017.
 */
public enum Role {
    ADMIN("admin"),
    ANONYMOUS("anonymous"),
    MANAGEMENT("management"),
    CLIENT("client");


    private final String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role fromName(String string) {

        for (Role role : values()) {
            if (role.getName().equalsIgnoreCase(string)) {
                return role;
            }
        }
        return null;
    }
    }
