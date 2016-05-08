package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
import za.ac.cput.decapp.Repositories.UserRepository;
import za.ac.cput.decapp.services.LoginService;

/**
 * Created by User on 2016/05/04.
 */// **********  WHY?*******************
// this service is for the login of only law officials with a authorization number
    // and can only used while on duty due to security constraints
    // this is a started service

public class LoginServiceImpl extends IntentService implements LoginService
{

    private final UserRepository repo;

    private static UserServiceImpl service = null;

    public static UserServiceImpl getInstance() {
        if (service == null)
            service = new UserServiceImpl();
        return service;
    }

    private UserServiceImpl() {
        super("UserServiceImpl");
        repository = new UserRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addUser(Context context, UserResourse UserResourse) {
        Intent intent = new Intent(context, UserServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, UserResourse);
        context.startService(intent);
    }

    @Override
    public void resetUsers(Context context) {
        Intent intent = new Intent(context, UserServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final UserResource UserResource = (UserResource) intent.getSerializableExtra(EXTRA_ADD);
                saveUser(UserResourse);
            } else if (ACTION_RESET.equals(action)) {
                resetUsersRecords();
            }
        }
    }

    private void resetUsersRecords() {
        repository.deleteAll();
    }

    private void saveUser(UserResourse UserResourse) {
        User User = new User.Builder()
                .UserId(UserResourse.getUserId())
                .UserImage(AppUtil.getImage(UserResourse.getUserImageUrl()))
                .electionTypeId(UserResourse.getElectionTypeId())
                .firstname(UserResourse.getFirstname())
                .lastName(UserResourse.getLastName())
                .symbolImage(AppUtil.getImage(UserResourse.getSymbolImageUrl()))
                .build();

        User savedUser = repository.save(User);

    }
}

}
