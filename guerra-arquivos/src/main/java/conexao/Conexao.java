package conexao;

import java.io.*;
import java.util.logging.Logger;

import com.jcraft.jsch.*;

public class Conexao {

    static JSch jsch = new JSch();
    static Session session;
    static Logger logger = Logger.getLogger(Conexao.class.getName());

    public static void main(String[] args) {
        connectToUser("thalesvm", "localhost", "2203", 2222);
        sendFileToUser("/opt/dev/projects/almaviva-projetos/guerra-arquivos/src/main/resources/scripts/attack.sh", "/home/thalesvm/");
        runCommandOnUser("chmod +x /home/thalesvm/attack.sh");
        runCommandOnUser("cd /home/thalesvm && ./attack.sh");

    }

    public static void connectToUser(String user, String host, String password, int port) {

        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            logger.info("Conexão estabelecida com o host.");

        } catch (JSchException e) {
            logger.warning("Não foi possível estabelecer uma conexão com o host: " + e.getMessage());

        }
    }

    public static void sendFileToUser(String localFilePath, String remoteDirectory) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            logger.info("SFTP channel opened.");

            channelSftp.put(localFilePath, remoteDirectory);

            logger.info("File uploaded successfully to " + remoteDirectory);
        } catch (JSchException | SftpException e) {
            logger.warning("Erro ao enviar o arquivo: " + e.getMessage());
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
            logger.info(captureCommandOutput(channel));
            channel.disconnect();

        } catch (JSchException e) {
            logger.warning("Não foi possível executar o comando: " + e.getMessage());

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
            logger.warning("Erro ao ler a saída do comando: " + e.getMessage());

        }

        return out.toString();
    }
}
