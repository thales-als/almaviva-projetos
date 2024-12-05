import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class Jogador {
    private static final Logger logger = Logger.getLogger(Jogador.class.getName());
    private static final int PORTA = 3304;

    public static void main(String[] args) {

        String enderecoServidor;

        if (args.length == 0) {
            logger.warning("Execute o comando no seguinte formato: java Jogador <ip-host>");
            return;
        }

        enderecoServidor = args[0];

        try (
                Socket socket = new Socket(enderecoServidor, PORTA);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ) {

            String mensagem;

            while ((mensagem = in.readLine()) != null) {
                logger.info(mensagem);
                if (mensagem.equals("Faça sua jogada: (1 - Papel, 2 - Pedra, 3 - Tesoura)")) {
                    int jogada = Integer.parseInt(console.readLine());
                    out.println(jogada);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}