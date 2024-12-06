package conexao;

import java.io.*;
import java.util.*;
import com.jcraft.jsch.*;

public class Conexao {

    static JSch jsch = new JSch();
    static Session session;

    public static void main(String[] args) {
        List<UserConnection> userConnections = Arrays.asList(
                new UserConnection("thalesvm", "localhost", "2203", 2222)
        );

        for (UserConnection user : userConnections) {
            connectToUser(user);
            sendFileToUser("/opt/dev/projects/almaviva-projetos/guerra-arquivos/src/main/resources/scripts/attack_*.sh", "/home/" + user.getUsername() + "/");
            runCommandOnUser("chmod +x /home/" + user.getUsername() + "/attack.sh");
            runCommandOnUser("cd /home/" + user.getUsername() + " && ./attack.sh");
            disconnectFromUser();
        }
    }

    public static void connectToUser(UserConnection user) {
        try {
            session = jsch.getSession(user.getUsername(), user.getHost(), user.getPort());
            session.setPassword(user.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            System.out.println("Conexão estabelecida com o host: " + user.getHost());
        } catch (JSchException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o host " + user.getHost() + ": " + e.getMessage());
        }
    }

    public static void sendFileToUser(String localFilePath, String remoteDirectory) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            System.out.println("Canal SFTP aberto.");

            channelSftp.put(localFilePath, remoteDirectory);

            System.out.println("Arquivo enviado para " + remoteDirectory);
        } catch (JSchException | SftpException e) {
            System.out.println("Erro ao enviar o arquivo: " + e.getMessage());
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }
    }

    public static void runCommandOnUser(String command) {
        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.connect();
            System.out.println(captureCommandOutput(channel));
            channel.disconnect();
        } catch (JSchException e) {
            System.out.println("Não foi possível executar o comando: " + e.getMessage());
        }
    }

    public static String captureCommandOutput(Channel channel) {
        StringBuilder out = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler a saída do comando: " + e.getMessage());
        }
        return out.toString();
    }

    public static void disconnectFromUser() {
        if (session != null && session.isConnected()) {
            session.disconnect();
            System.out.println("Sessão desconectada.");
        }
    }

    static class UserConnection {
        private final String username;
        private final String host;
        private final String password;
        private final int port;

        public UserConnection(String username, String host, String password, int port) {
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
}
