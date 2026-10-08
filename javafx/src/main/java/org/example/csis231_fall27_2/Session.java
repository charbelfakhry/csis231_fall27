package org.example.csis231_fall27_2;

import org.example.csis231_fall27_2.api.LoginResponse;

public class Session {
    public static LoginResponse currentUser;

    public static void setCurrentUser(LoginResponse user) {
        currentUser = user;
    }

    public static LoginResponse getCurrentUser() {
        return currentUser;
    }

    public static void clear(){
        currentUser = null;
    }
}