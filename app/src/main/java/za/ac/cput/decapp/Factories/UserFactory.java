package za.ac.cput.decapp.Factories;


import za.ac.cput.decapp.Domain.User;

/**
 * Created by User on 2016/04/24.
 */
public class UserFactory {
    public static User getUser (String screenName, String email,String password, String obNumber)
    {
        User usr = new User
                .Builder(screenName)
                .email(email)
                .password(password)
                .obNumber(obNumber)
                .build();

        return usr;
}}
