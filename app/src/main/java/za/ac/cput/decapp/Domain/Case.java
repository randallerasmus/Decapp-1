package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */

public class Case implements Serializable
{
    private Long id;
    private String offense;
    private String offenseLocation;

    public Long getId() {
        return id;
    }

    public String getOffense() {
        return offense;
    }

    public String getOffenseLocation() {
        return offenseLocation;
    }

    public Case() {
    }

    private Case(Builder builder) {
        id = builder.id;
        offense = builder.offense;
        offenseLocation = builder.offenseLocation;
    }

    public static final class Builder {
        private Long id;
        private String offense;
        private String offenseLocation;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder offense(String val) {
            offense = val;
            return this;
        }

        public Builder offenseLocation(String val) {
            offenseLocation = val;
            return this;
        }
        public Builder copy(Case copy) {
            Builder builder = new Builder();
            builder.id=copy.id;
            builder.offense = copy.offense;
            builder.offenseLocation = copy.offenseLocation;
            return this;
        }

        public Case build() {
            return new Case(this);
        }
    }
}
