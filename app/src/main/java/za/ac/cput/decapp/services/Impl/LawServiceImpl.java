package za.ac.cput.decapp.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import za.ac.cput.decapp.Domain.Comment;
import za.ac.cput.decapp.Factories.CommentFactory;
import za.ac.cput.decapp.Repositories.Impl.CommentRepositoryImpl;
import za.ac.cput.decapp.services.LawService;

/**
 * Created by User on 2016/05/04.
 */
//this service is used for the quick review of the Law for the office
    // this is bound services because it must wait for the return of the request
public class LawServiceImpl extends Service implements LawService{
    
    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private CommentRepository repo;

    public ActivateServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ActivateServiceImpl getService() {
            return ActivateServiceImpl.this;
        }
    }


    @Override
    public String activateAccount(String email, String code, String password) {
        if (true) {
            Comment Comment = CommentFactory.getComment(email, code, password);

            return DomainState.ACTIVATED.name();
        } else {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isAccountActivated() {
        return repo.findAll().size()>0;
    }

    @Override
    public boolean deactivateAccount() {
        int rows = repo.deleteAll();
        return rows > 0;

    }

    private Comment createComment(Comment Comment) {
        repo = new CommentRepositoryImpl(App.getAppContext());
        return repo.save(Comment);
    }
}

}
