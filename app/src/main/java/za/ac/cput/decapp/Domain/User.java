package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */
public class User implements Serializable
{
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String screenName;
    private String email;
    private String password;
    private String obNumber;

    public static Builder newUser() {
        return new Builder();
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getobNumber() {
        return obNumber;
    }

    public void setobNumber(String obNumber) {
        obNumber = obNumber;
    }

    public User() {

    }

    private User(Builder builder) {
        setId(builder.id);
        setScreenName(builder.screenName);
        setEmail(builder.email);
        setPassword(builder.password);
        setobNumber(builder.obNumber);
    }

    public static class Builder {
        private Long id;
        private String screenName;
        private String email;
        private String password;
        private String obNumber;


        public Builder(String screenName) {
            this.screenName = screenName;
        }

        public Builder() {
        }

        public Builder(User copy) {
            this.id = copy.id;
            this.screenName = copy.screenName;
            this.email = copy.email;
            this.password = copy.password;
            this.obNumber = copy.obNumber;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder obNumber(String obNumber) {
            this.obNumber = obNumber;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder screenName(String val) {
            screenName = val;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
