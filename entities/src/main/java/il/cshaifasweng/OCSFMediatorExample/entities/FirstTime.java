package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "firsttime") 
public class FirstTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int firsttime;
    private int firsttime1;
    private String username;

    public FirstTime() {
    }

    public FirstTime(int firsttime, int firsttime1, String username) {
        this.firsttime = firsttime;
        this.firsttime1 = firsttime1;
        this.username = username;
    }

    public int getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(int firsttime) {
        this.firsttime = firsttime;
    }

    public int getFirsttime1() {
        return firsttime1;
    }

    public void setFirsttime1(int firsttime1) {
        this.firsttime1 = firsttime1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}