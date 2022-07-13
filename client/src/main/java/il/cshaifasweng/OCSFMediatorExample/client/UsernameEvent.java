package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Registration;

import java.util.List;


public class UsernameEvent {
    private String username;
    private String type;
    private List<Registration>list;

    public UsernameEvent(String type, List<Registration> list) {
        this.type = type;
        this.list = list;
    }
    public UsernameEvent(String userName) {
        username=userName;
    }

    public UsernameEvent(List<Registration> list) {
        this.list = list;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public List<Registration> getList() {
        return list;
    }

    public void setList(List<Registration> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
