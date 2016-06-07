package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */

    public class Suspect implements Serializable
    {
        private Long id;
        private String name;
        private String surname;

       public Suspect() {
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

        private Suspect(Builder builder) {
            setId(builder.id);
            setName(builder.name);
            setSurname(builder.surname);
        }

        public static class Builder {
            private Long id;
            private String name;
            private String surname;

            public Builder() {
            }

            public Builder copy(Suspect copy) {
                this.id = copy.id;
                this.name = copy.name;
                this.surname = copy.surname;
                return this;
            }


            public Builder Id(Long id) {
                this.id = id;
                return this;
            }

            public Builder name(String name) {
                this.name = name;
                return this;

            }

            public Builder surname(String surname) {
                this.surname = surname;
                return this;
            }

            public Suspect build() {
                return new Suspect(this);
            }

            public Builder id(Long val) {
                id = val;
                return this;
            }
        }

        public Suspect build()
        {
            return new Suspect();
        }
    }


