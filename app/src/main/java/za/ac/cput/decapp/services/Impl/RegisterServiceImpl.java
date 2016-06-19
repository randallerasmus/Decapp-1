package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;
import za.ac.cput.decapp.services.LoginService;
import za.ac.cput.decapp.services.RegisterService;

/**
 * Created by User on 2016/05/04.
 */
// this service is used for registration purposes
    // this is a started service cause it returns nothing and are stopped when finish
public class RegisterServiceImpl extends IntentService implements RegisterService {

    private static RegisterServiceImpl service = null;

    public static RegisterServiceImpl getInstance() {
        if (service == null)
            service = new RegisterServiceImpl();
        return service;
    }

    public RegisterServiceImpl()
    {

    }

    private RegisterServiceImpl() {
        super("RegisterServiceImpl");
        UserRepositoryImpl userRepository = new UserRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addUser(Context context, UserResourse UserResourse) {
        Intent intent = new Intent(context, RegisterServiceImpl.class);
        intent.setAction(ACTION);
        intent.putExtra(EXTRA_ADD, UserResourse);
        context.startService(intent);
    }

    @Override
    public void resetUsers(Context context) {
        Intent intent = new Intent(context, RegisterServiceImpl.class);
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
                .firstname(UserResourse.getFirstname())
                .lastName(UserResourse.getLastName())
                .build();
        User savedUser = UserRepository.save(User);

    }
}


