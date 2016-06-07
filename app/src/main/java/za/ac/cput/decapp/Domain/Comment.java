package za.ac.cput.decapp.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/04/24.
 */
public class Comment implements Serializable
{
    private Long id;
    private String info;
    private String commentOfficial;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getCommentOfficial() {
        return commentOfficial;
    }

    private Comment(Builder builder) {
        id = builder.id;
        info = builder.info;
        commentOfficial = builder.commentOfficial;
    }
    public static final class Builder {
        public Long id;
        private String info;
        private String commentOfficial;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder info(String val) {
            info = val;
            return this;
        }

        public Builder commentOfficial(String val) {
            commentOfficial = val;
            return this;
        }

        public Builder copy(Comment copy) {
            Builder builder = new Builder();
            builder.id = copy.id;
            builder.info = copy.info;
            builder.commentOfficial = copy.commentOfficial;
            return this;
        }


        public Comment build() {
            return new Comment(this);
        }
    }
}
