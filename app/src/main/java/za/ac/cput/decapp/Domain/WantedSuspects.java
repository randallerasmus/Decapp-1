package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/06/06.
 */
public class WantedSuspects implements Serializable{

    private Long id;
    private String name;
    private String surname;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public WantedSuspects() {
    }

    private WantedSuspects(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String surname;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }
        public Builder copy(WantedSuspects copy) {
            Builder builder = new Builder();
            builder.id = copy.id;
            builder.name = copy.name;
            builder.surname = copy.surname;
            return this;
        }

        public WantedSuspects build() {
            return new WantedSuspects(this);
        }
    }
}
