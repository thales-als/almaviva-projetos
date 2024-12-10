package connection;

public class UserLogin {
    private final String username;
    private final String host;
    private final String password;
    private final int port;

    public UserLogin(String username, String host, String password, int port) {
        this.username = username;
        this.host = host;
        this.password = password;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
}
