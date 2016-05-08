package za.ac.cput.decapp.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */

public class Case implements Serializable
{
    private Long id;
    private String offense;
    private Date date;


    public Long getId() {
        return id;
    }

    public String getOffense() {
        return offense;
    }

    public Date getDate() {
        return date;
    }

    public Case() {

    }

    private Case(Builder builder) {
       this.id=builder.id;
        this.offense=builder.offense;
        this.date=builder.date;
    }

    public static class Builder {
        private String offense;
        private Date date;
        private Long id;

        public Builder() {

        }


        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder offense(String val) {
            offense = val;
            return this;
        }
        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder(Case copy) {
            this.id = copy.id;
            this.offense = copy.offense;
            this.date = copy.date;
        }

        public Case build() {
            return new Case(this);
        }
    }
}
