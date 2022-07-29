package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Message implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 24613557937684578L;
    private String message;
    private Object object;
    private Object object2;
    private Object object3;
    private Object object4;
    private Object object5;
    private Object object6;
    private Object object7;
    private Object object8;



    public Message(String msg, Object obj) {
        this.message = msg;
        this.object = obj;
    }

    public Message(String msg, Object obj,Object obj2) {
        this.message = msg;
        this.object = obj;
        this.object2 = obj2;
    }

    public Message(String msg, Object obj,Object obj2, Object obj3) {
        this.message = msg;
        this.object = obj;
        this.object2 = obj2;
        this.object3 = obj3;
    }

    public Message(String msg, Object obj,Object obj2, Object obj3, Object obj4) {
        this.message = msg;
        this.object = obj;
        this.object2 = obj2;
        this.object3 = obj3;
        this.object4 = obj4;
    }
    public Message(String msg, Object obj,Object obj2, Object obj3, Object obj4,Object obj5) {
        this.message = msg;
        this.object = obj;
        this.object2 = obj2;
        this.object3 = obj3;
        this.object4 = obj4;
        this.object5=obj5;
    }



    public Message(String msg, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        this.message = msg;
        this.object = obj;
        this.object2 = obj2;
        this.object3 = obj3;
        this.object4 = obj4;
        this.object5=obj5;
        this.object6=obj6;
    }

    public Message(String msg ,Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        this.message = msg;
        this.object = object;
        this.object2 = object2;
        this.object3 = object3;
        this.object4 = object4;
        this.object5 = object5;
        this.object6 = object6;
        this.object7 = object7;
        this.object8 = object8;
    }

    public Message(String msg,Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7) {
        this.message = msg;
        this.object = object;
        this.object2 = object2;
        this.object3 = object3;
        this.object4 = object4;
        this.object5 = object5;
        this.object6 = object6;
        this.object7 = object7;
    }

    public Object getObject7() {
        return object7;
    }

    public void setObject7(Object object7) {
        this.object7 = object7;
    }

    public Object getObject8() {
        return object8;
    }

    public void setObject8(Object object8) {
        this.object8 = object8;
    }

    public Message(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject () { return this.object; }

    public Object getObject2 () { return this.object2;}

    public Object getObject3 () { return this.object3;}

    public void setMessage(String msg) {
        this.message=msg;
    }

    public void setObject(Object obj) {
        this.object=obj;
    }

    public void setObject2(Object obj) {
        this.object2=obj;
    }

    public void setObject3(Object obj) {
        this.object3=obj;
    }

    public String toString() {
        return this.message;
    }

    public Object getObject4() {
        return object4;
    }

    public void setObject4(Object object4) {
        this.object4 = object4;
    }
    public Object getObject5() { return object5; }
    public Object getObject6() { return object6; }

    public void setObject5(Object object5) {
        this.object5 = object5;
    }

    public void setObject6(Object object6) { this.object6 = object6; }


}