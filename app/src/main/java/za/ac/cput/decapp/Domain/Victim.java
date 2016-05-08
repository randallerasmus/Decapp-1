package za.ac.cput.decapp.Domain;

//import javax.persistence.Embedded;
//import javax.persistence.Entity;
import java.io.Serializable;
import java.security.KeyStore;



public class Victim implements Serializable
{
    private Long id;
    private String name;
    private String surname;

    public Victim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private Victim(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setSurname(builder.surname);
    }

    public static class Builder {
        public Builder() {
        }

        public Builder(Victim copy) {
            this.id = copy.id;
            this.name = copy.name;
            this.surname = copy.surname;
        }

        public Builder Id(Long value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;

        }

        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        public Victim build() {
            return new Victim(this);
        }


        private Long id;
        private String name;
        private String surname;

        public Builder id(Long val) {
            id = val;
            return this;
        }
    }
        public Victim build()
        {
            return new Victim();
        }
}

























