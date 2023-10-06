
/**
 *
 * @author Desktop
 */
public class WorkerLogin {

    private String username;
    private String password;
    private String userAccessType;

    public WorkerLogin() {
    }

    public WorkerLogin(String username, String password, String userAccessType) {
        this.username = username;
        this.password = password;
        this.userAccessType = userAccessType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserAccessType(String userAccessType) {
        this.userAccessType = userAccessType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserAccessType() {
        return userAccessType;
    }

    @Override
    public String toString() {
        return "WorkerStudLogin{" + "username=" + username + ", password=" + password + ", studentID=" + userAccessType + '}';
    }
    
    
    
}
