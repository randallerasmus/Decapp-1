package za.ac.cput.decapp.Domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class Transfer implements Serializable
{
    private Long id;
    private Blob pic;
    private Date date;

    private Transfer(Builder builder) {
        setId(builder.id);
        setPic(builder.pic);
        setDate(builder.date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public static Builder newTransfer() {
//        return new Builder();
//    }

    public Blob getPic() {
        return pic;
    }

    public void setPic(Blob pic) {
        this.pic = pic;
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
        private Blob pic;
        private Date date;
        private Long id;

        public Builder(Blob pic) {
            this.pic = pic;
        }

        public Builder() {
        }

        public Builder(Transfer copy) {
            this.id = copy.id;
            this.pic = copy.pic;
            this.date = copy.date;
        }

        public Transfer build() {
            return new Transfer(this);
        }

        public Builder pic(Blob pic) {
            this.pic = pic;
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
    }
}
