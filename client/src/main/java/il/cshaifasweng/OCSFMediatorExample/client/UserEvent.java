package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Registration;

public class UserEvent {
    Registration User;

    public UserEvent(Registration user) {
        User = user;
    }

    public Registration getUser() {
        return User;
    }

    public void setUser(Registration user) {
        User = user;
    }
}
