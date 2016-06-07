package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */
public class Demographics implements Serializable
{
    private String race;
    private String gender;
    private String age;

    private Demographics() {
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public Demographics(Builder builder) {
        race = builder.race;
        gender = builder.gender;
        age = builder.age;
    }

    public static final class Builder {
        private String race;
        private String gender;
        private String age;

        public Builder() {
        }

        public Builder race(String val) {
            race = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public Builder copy(Demographics copy) {
            Builder builder = new Builder();
            builder.race = copy.race;
            builder.gender = copy.gender;
            builder.age = copy.age;
            return this;
        }

        public Demographics build() {
            return new Demographics(this);
        }
    }
}
