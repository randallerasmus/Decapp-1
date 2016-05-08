package za.ac.cput.decapp.Factories;


import za.ac.cput.decapp.Domain.Comment;

import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class CommentFactory {
    public static Comment getComment(String info, Date date
                                ) {
        Comment comment = new Comment.Builder()
                .info(info)
                .date(new Date())
                .build();

        return comment;
    }
}