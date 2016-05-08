package za.ac.cput.decapp.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class Demographics implements Serializable
{
    private Long id;
    private String race;
    private String gender;
    private Date dob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Demographics() {

    }
    private Demographics(Builder builder) {
        setId(builder.id);
        setRace(builder.race);
        setGender(builder.gender);
        setDob(builder.dob);
    }

    public static final class Builder {
        private String race;
        private String gender;
        private Date dob;
        private Long id;

        public Builder(String race) {
            this.race = race;
        }

        public Builder() {
        }

        public Demographics build() {
            return new Demographics(this);
        }

        public Builder race(String race) {
            this.race = race;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder dob(Date dob) {
            this.dob = dob;
            return this;
        }

        public static Builder newBuilder(Demographics copy) {
            Builder builder = new Builder();
            builder.id = copy.id;
            builder.race = copy.race;
            builder.gender = copy.gender;
            builder.dob = copy.dob;
            return builder;
        }


        public Builder id(Long val) {
            id = val;
            return this;
        }
    }
}
