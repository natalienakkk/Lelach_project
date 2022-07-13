package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;


public class Confirmation implements Serializable{
    private static final long serialVersionUID = -4964818825819886127L;

    private String message;
    private LocalTime time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Confirmation(String message) {
        this.message = message;
        this.time = LocalTime.now();
    }

    public LocalTime getTime() {
        return time;
    }
}
