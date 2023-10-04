

/**
 *
 * @author Desktop
 */
public class WorkerLogin {

    private String username;
    private String password;

    public WorkerLogin() {
    }

    public WorkerLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WorkerLogin(String username) {
        this.username = username;
    }
    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WorkerLogin{" + "username=" + username + ", password=" + password + '}';
    }

}
