package il.cshaifasweng.OCSFMediatorExample.client;

public class SendUsernameEvent {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SendUsernameEvent(String username) {
        this.username = username;
    }
}
