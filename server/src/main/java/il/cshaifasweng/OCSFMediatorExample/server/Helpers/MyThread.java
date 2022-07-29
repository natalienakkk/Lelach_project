package il.cshaifasweng.OCSFMediatorExample.server.Helpers;

public class MyThread implements Runnable {
    String a;
    String b;
    String c;

    public MyThread(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void run() {
        SendEmail.SendEmail(a,b,c);

    }
}
