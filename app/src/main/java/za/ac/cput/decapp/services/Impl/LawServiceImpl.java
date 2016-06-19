package za.ac.cput.decapp.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Conf.util.DomainState;
import za.ac.cput.decapp.Domain.UpdateActivity;
import za.ac.cput.decapp.Factories.CommentFactory;
import za.ac.cput.decapp.Repositories.Impl.CommentRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.CommentRepository;
import za.ac.cput.decapp.services.LawService;

/**
 * Created by User on 2016/05/04.
 */
//this service is used for the quick review of the Law for the office
    // this is bound services because it must wait for the return of the request

public abstract class LawServiceImpl extends Service implements LawService{

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private CommentRepository repo;

    public LawServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder
    {
        public LawServiceImpl getService() {
            return LawServiceImpl.this;
        }
    }


    @Override
    public String activateAccount(String email, String code, String password) {
        if (true) {
            UpdateActivity UpdateActivity = CommentFactory.getComment(email, code, password);

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

    private UpdateActivity createComment(UpdateActivity UpdateActivity) {
        repo = new CommentRepositoryImpl(App.getAppContext());
        return repo.save(UpdateActivity);
    }
}


