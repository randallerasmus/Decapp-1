package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.Comment;

/**
 * Created by User on 2016/05/05.
 */
public class UpdateActivityFactoryTest {

    @Test
    public void testCreateComment() throws Exception {

        Comment Comment = CommentFactory.getComment("Huge nose","SGt ERasmus");
        Assert.assertEquals("Huge nose","Sgt. Roberts",Comment);
        }
    @Test
    public void testUpdate () throws Exception
    {
        Comment comment = CommentFactory.getComment("Huge nose","Sgt Roberts");
        Comment newComment = new Comment
                .Builder()
                .copy(comment)
                .info("Huge Nose")
                .commentOfficial("Sgt Erasmus")
                .build();

        Assert.assertEquals("Huge Nose",newComment.getInfo());
        Assert.assertEquals("Sgt.Erasmus",newComment.getCommentOfficial());
    }
}