package connection;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Connection {

    private static final JSch jsch = new JSch();

    public static Session connectToUser(UserLogin user) {
        try {
            Session session = jsch.getSession(user.getUsername(), user.getHost(), user.getPort());
            session.setPassword(user.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            return session;
        } catch (JSchException e) {
            System.out.println("Falha na conexão com " + user.getHost() + ": " + e.getMessage());
            return null;
        }
    }

    public static void sendFileToUser(Session session, String localFilePath, String remoteDirectory) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(localFilePath, remoteDirectory);
        } catch (JSchException | SftpException e) {
            System.out.println("Erro ao enviar o arquivo: " + e.getMessage());
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }
    }

    public static void runCommandOnUser(Session session, String command) {
        Channel channel = null;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.connect();
            captureCommandOutput(channel);
        } catch (JSchException e) {
            System.out.println("Erro ao executar o comando: " + e.getMessage());
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private static void captureCommandOutput(Channel channel) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler a saída do comando: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o BufferedReader: " + e.getMessage());
                }
            }
        }
    }

    public static void disconnectFromUser(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
