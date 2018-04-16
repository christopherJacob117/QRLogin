package vision.qr.com.qrlogin;

/**
 * Created by jeena on 3/3/18.
 */

public class User {
    String user,pass,web;

    public User(String user, String pass, String web) {
        this.user = user;
        this.pass = pass;
        this.web = web;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
