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

    public Contact() {
    }

    private Contact(Builder builder) {
        cellphone = builder.cellphone;
        phoneNumber = builder.phoneNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static final class Builder {
        private String cellphone;
        private String phoneNumber;

        public Builder() {
        }

        public Builder cellphone(String val) {
            cellphone = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }
        public Builder copy(Contact copy) {
            Builder builder = new Builder();
            builder.cellphone = copy.cellphone;
            builder.phoneNumber = copy.phoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
