package za.ac.cput.decapp.Domain;

/**
 * Created by User on 2016/04/24.
 */

import java.io.Serializable;


public class Address implements Serializable{

    private Long id;
    private String address;
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Address() {
    }
    private Address(Builder builder) {
       this.id=builder.id;
        this.address=builder.address;
       this.postalCode=builder.postalCode;
    }

    public static class Builder {
        private Long id;
        private String address;
        private String postalCode;

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;

        }

        public Builder copy(Address address) {
           this.id=address.id;
            this.address=address.address;
            this.postalCode=address.postalCode;

            return this;
        }
        public Address build()
        {
            return new Address(this);
        }

    }
}
