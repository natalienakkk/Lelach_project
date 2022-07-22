package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import javax.persistence.*;
import javax.xml.namespace.QName;
import java.io.Serializable;

@Entity
@Table(name = "messages")
public class SystemManagers_Messages implements Serializable {
    private static final long serialVersionUID= -1384117544136995313L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Message;
    private String client_username;

    //   @ManyToOne
//    @JoinColumn(name="message_id")
//   private User messages;
//
//    public User getMessages() {
//        return messages;
//    }
//
//    public void setMessages(User messages) {
//        this.messages = messages;
//    }

    public SystemManagers_Messages(String message) {
        Message = message;
    }

    public SystemManagers_Messages() {
    }

    public SystemManagers_Messages(String message,String username ) {
        Message = message;
        client_username=username;
    }

    public String getClient_username() {
        return client_username;
    }

    public void setClient_username(String client_username) {
        this.client_username = client_username;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }
}
