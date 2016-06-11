//package za.ac.cput.decapp.services.Impl;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.IBinder;
//
//import java.util.Set;
//
//import za.ac.cput.decapp.Domain.User;
//import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;
//
///**
// * Created by User on 2016/05/04.
// */// **********  WHY?*******************
//// this service is for the login of only law officials with a authorization number
//    // and can only used while on duty due to security constraints
//    // this is a started service
//
//public class LoginServiceImpl extends Service  {
//    final private UserRepository userRepository;
//    private static LoginServiceImpl service = null;
//
//    public static LoginServiceImpl getInstance() {
//        if (service == null)
//            service = new LoginServiceImpl();
//        return service;
//    }
//
//    public LoginServiceImpl() {
//        userRepository = new UserRepositoryImpl(userRepository.save()) {
//        };
//    }
//
//    private final IBinder localBinder = new LoginServiceBinder();
//
//    public class LoginServiceBinder extends Binder {
//        public LoginServiceImpl getService() {
//            return LoginServiceImpl.this;
//        }
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        return localBinder;
//    }
//
//    @Override
//    public boolean checkActivition() {
//        Set<User> persons = userRepository.findAll();
//        return persons.size() > 0;
//    }
//
//    @Override
//    public boolean checkLogin(String emailAddress, String password) {
//        boolean loggedIn = false;
//        Set<User> persons = userRepository.findAll();
//        for (User person : persons) {
//            loggedIn = BCrypt.checkpw(password, person.getAuthvalue());
//        }
//        return loggedIn;
//    }
//}
//
