package za.ac.cput.decapp.Domain;

//import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */
public class Contact implements Serializable
{
    private String cellphone;
    private String phoneNumber;
    private Long id;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Contact copy) {
        Builder builder = new Builder();
        builder.cellphone = copy.cellphone;
        builder.phoneNumber = copy.phoneNumber;
        builder.id = copy.id;
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Contact(Builder builder) {
        setCellphone(builder.cellphone);
        setPhoneNumber(builder.phoneNumber);
        setId(builder.id);
    }

//    public static Builder newContact() {
//        return new Builder();
//    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact() {

    }

    public static class Builder {
        private String cellphone;
        private String phoneNumber;
        private Long id;

        public Builder(String cellphone) {
            this.cellphone = cellphone;
        }

        public Builder() {
        }

        public Builder(Contact copy) {
            this.cellphone = copy.cellphone;
            this.phoneNumber = copy.phoneNumber;
        }

        public Contact build() {
            return new Contact(this);
        }

        public Builder cellphone(String cellphone) {
            this.cellphone = cellphone;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }
    }
}
