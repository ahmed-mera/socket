package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * questa class serve per la gestione del server
 * @author Ahmed Mera
 */
public class Server {

    private final int port = 2020;

    /**
     * un metodo per creare la socket che a noi serve per la connessione
     * @return {@link Socket}
     * see {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo <b>( nel caso se ci sono problemi su il livello di rete)</b>
     */
    public Socket getServerSocket() throws IOException {
        return (new ServerSocket(port)).accept();
    }

    /**
     * un metodo per leggere la data che il server che ci ha inviato
     * @param socket {@link Socket}
     * @return String {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    private String readData(Socket socket) throws IOException {
        return (new BufferedReader( new InputStreamReader( socket.getInputStream() ))).readLine();
    }

    /**
     * un metodo per mandare la data al server
     * @param socket {@link Socket}
     * @param data {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    private void sendData(Socket socket, String data) throws IOException {
        (new PrintWriter( socket.getOutputStream(), true)).println(data);
    }


    /**
     * il nostro metodo principale per la gestione del tutto <b>( mandare/ricevere dati al/dal client ) </b>
     * @param socket {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public  void startServer(Socket socket) throws IOException {
        System.out.println("new connection");

        while (true){
            String data = this.readData(socket);

            if (data.equalsIgnoreCase("end") ){
                socket.close();
                System.out.println( "Connection closed" );
                return;
            }

            System.out.println("Client : " + data);

            this.sendData(socket, data.toUpperCase().concat(" (from Server)"));
        }
    }

    /**
     * metodo che restuisce la port del server sul quale rimane in ascolto
     * @return int
     */
    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws IOException, NullPointerException {
        Server server = new Server();
        System.out.println("Server Listen on Port : " + server.getPort());
        server.startServer(server.getServerSocket());
    }


}
