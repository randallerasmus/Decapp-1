package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

import za.ac.cput.decapp.Domain.Comment;

/**
 * Created by User on 2016/05/05.
 */
public class CommentFactoryTest {

    @Test
    public void testCreateComment() throws Exception {

        Comment Comment = CommentFactory.getComment("Huge nose",new Date());
        Assert.assertEquals("Huge nose",new Date());
        }
    @Test
    public void testUpdate () throws Exception
    {
        Comment comment = CommentFactory.getComment("Huge nose",new Date());
        Comment newComment = new Comment
                .Builder()
//                .copy(Comment)
                .info("Huge Nose")
                .date(new Date())
                .build();

        Assert.assertEquals("Huge Nose",newComment.getInfo());
        Assert.assertEquals("Huge nose",newComment.getInfo());
    }
}