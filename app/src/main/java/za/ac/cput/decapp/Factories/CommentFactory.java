package za.ac.cput.decapp.Factories;


import za.ac.cput.decapp.Domain.Comment;

/**
 * Created by User on 2016/04/24.
 */
public class CommentFactory {
    public static Comment getComment(String info, String commentOfficial ) {
        Comment comment = new Comment
                .Builder()
                .info(info)
                .commentOfficial(commentOfficial)
                .build();

        return comment;
    }
}