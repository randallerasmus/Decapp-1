package za.ac.cput.decapp.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class Comment implements Serializable
{
    private Long id;
    private String info;
    private Date date;


    public Long getId() {
        return id;
    }

   public String getInfo() {
        return info;
    }

    public Date getDate() {
        return date;
    }

    public Comment() {
    }

    private Comment(Builder builder) {
       this.id=builder.id;
        this.info=builder.info;
        this.date=builder.date;
    }

    public static class Builder {
        private String info;
        private Date date;
        private Long id;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }
        public Builder(Comment copy) {
            this.id = copy.id;
            this.info = copy.info;
            this.date = copy.date;
        }
        public Comment build() {
            return new Comment(this);
        }

    }
}
