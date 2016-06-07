package za.ac.cput.decapp.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class Transfer implements Serializable
{
    private Long id;
    private byte[] suspectImage;
    private Date date;
    private String TransferId;


    public static Builder newBuilder(Transfer copy) {
        Builder builder = new Builder();
        builder.TransferId = copy.TransferId;
        return builder;
    }

    public String getTransferId() {
        return TransferId;
    }

    private Transfer(Builder builder) {
        TransferId = builder.TransferId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getsuspectImage() {
        return suspectImage;
    }

    public void setsuspectImage(byte[] suspectImage) {
        this.suspectImage = suspectImage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transfer() {
    }

    public static final class Builder {
        private byte[] suspectImage;
        private Date date;
        private Long id;
        private String TransferId;

        public Builder(byte[] suspectImage) {
            this.suspectImage = suspectImage;
        }

        public Builder() {
        }

        public Builder copy(Transfer copy) {
            this.id = copy.id;
            this.suspectImage = copy.suspectImage;
            this.date = copy.date;
            return this;
        }

        public Transfer build() {
            return new Transfer(this);
        }

        public Builder suspectImage(byte[] suspectImage) {
            this.suspectImage = suspectImage;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder TransferId(String val) {
            TransferId = val;
            return this;
        }
    }
}
