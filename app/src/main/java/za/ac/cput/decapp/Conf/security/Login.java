package za.ac.cput.decapp.Conf.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by User on 2016/06/13.
 */

    public class Login {

        public static boolean checkLogin(String username, String password,String authorization) {
            return BCrypt.checkpw(password, "hashed");
        }
    }

