package za.ac.cput.decapp.services.Impl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Set;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;
import za.ac.cput.decapp.services.LoginService;

/**
 * Created by User on 2016/05/04.
 */// **********  WHY?*******************
// this service is for the login of only law officials with a authorization number
    // and can only used while on duty due to security constraints
    // this is a started service

public class LoginServiceImpl extends Service implements LoginService
{
    final private UserRepository userRepository;
    private static LoginServiceImpl service = null;
    private final IBinder localBinder = new LoginServiceBinder();//Binder bridge

    public static LoginServiceImpl getInstance()
    {
        if (service == null)
            service = new LoginServiceImpl();
        return service;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public LoginServiceImpl()
    {
        userRepository = new UserRepositoryImpl(App.getAppContext());

    }
    public class LoginServiceBinder extends Binder
    {
        public LoginServiceImpl getService()
        {
            return LoginServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }

   public boolean checkLogin(String password) {
        boolean loggedIn = false;
        Set<User> users = userRepository.findAll();
        for (User user : users) {
            loggedIn = BCrypt.checkpw(password, user.getPassword());
        }
        return loggedIn;
    }

}

