package za.ac.cput.decapp.RepositoryTests;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.Comment;
import za.ac.cput.decapp.Repositories.Impl.CommentRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.CommentRepository;

public class CommentsRepositoryTest extends TestCaseSuite{
    private static final String TAG="COMMENTS TEST";
    private Long id;
    CommentRepository repo = new CommentRepositoryImpl(App.getAppContext());
    @Test
    public void testCommentCRUD() throws Exception{

        Comment coments = new Comment.Builder()
                .id(id)
                .info("left rear scar")
                .commentOfficial("Mr Jones")
                .build();
        Comment insertedEntity = repo.save(coments);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);
        Set<Comment> Comments = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",Comments.size()>0);
        Comment entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);
        Comment updateEntity = new Comment.Builder()
                .copy(entity)
                .info("broken arm")
                .commentOfficial("Mr Jones")
                .build();
        repo.update(updateEntity);
        Comment newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","broken arm",newEntity.getInfo());
        repo.delete(insertedEntity);
        Comment deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
