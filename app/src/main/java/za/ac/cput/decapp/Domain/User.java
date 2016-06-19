package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */
public class User implements Serializable
{
    private Long id;
    private String username;
    private String password;
    private String passwordConfirmation;
    private String authorizationNumber;



    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {return passwordConfirmation;}

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public User() {
    }
    public User(Builder builder) {
        id = builder.id;
        username = builder.username;
        password = builder.password;
        passwordConfirmation = builder.passwordConfirmation;
        authorizationNumber = builder.authorizationNumber;
    }
    public static final class Builder {
        private Long id;
        private String username;
        private String password;
        private String passwordConfirmation;
       private String authorizationNumber;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }
        public Builder passwordConfirmation(String val) {
            passwordConfirmation = val;
            return this;
        }

        public Builder authorizationNumber(String val) {
            authorizationNumber = val;
            return this;
        }
        public Builder copy(User copy) {
            Builder builder = new Builder();
            builder.id = copy.id;
            builder.username = copy.username;
            builder.password = copy.password;
            builder.passwordConfirmation = copy.passwordConfirmation;
            builder.authorizationNumber = copy.authorizationNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
