package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * questa class serve per la gestione del client
 * @author Ahmed Mera
 */
public class Client {

    /**
     * un metodo per creare la socket che a noi serve per la connessione
     * @return {@link Socket}
     * see {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo <b>( nel caso se ci sono problemi su il livello di rete)</b>
     */
    public Socket getSocket() throws IOException {
        return new Socket("localhost", 2020);
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
     * il nostro metodo principale per la gestione del tutto <b>( mandare/ricevere dati al/dal server ) </b>
     * @param socket {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public void startClient(Socket socket) throws IOException {

        while (true){

            System.out.println("Write Your message:_ ");
            this.sendData(socket, (new BufferedReader( new InputStreamReader( System.in ))).readLine() );

            String data = this.readData(socket);

            if (data.equalsIgnoreCase("end") ){
                socket.close();
                System.out.println( "Connection closed" );
                return;
            }

            System.out.println("Server : " + data);
        }
    }


    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startClient(client.getSocket());
    }

}
