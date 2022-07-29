package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.SystemManagers_Messages;

import java.util.List;

public class MessagesEvent {
    private List<SystemManagers_Messages>list;
    private String type;

    public MessagesEvent(List<SystemManagers_Messages> list, String type) {
        this.list = list;
        this.type = type;
    }

    public List<SystemManagers_Messages> getList() {
        return list;
    }

    public void setList(List<SystemManagers_Messages> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
