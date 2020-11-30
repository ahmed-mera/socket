package Server;

import Utils.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.lang3.StringUtils;


/**
 * questa class serve per la gestione del server
 * @author Ahmed Mera
 */
public class Server {

    private final int port = 2020;
    private static final String sum = "+";
    private static final String multiplication = "*";
    private static final String division = "/";
    private static final String subtraction = "-";

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
     * helper function to get operation
     * @param data type {@link String}
     * @return the result
     */
    private String getOperation(String data){
        data = Utils.parsing(data);

        if (data.contains(sum)){
            String[] numbers = data.split("\\" + sum);
            return String.valueOf(Utils.sum(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(subtraction)){
            String[] numbers = data.split(subtraction);
            return String.valueOf(Utils.subtraction(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(multiplication)){
            String[] numbers = data.split("\\" + multiplication);
            return String.valueOf(Utils.multiplication(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(division)){
            String[] numbers = data.split(division);
            return String.valueOf(Utils.division(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else {
            return "operatore non é riconociuto";
        }
    }


public String prova(String data){
        return this.getOperation(data);
}


    /**
     * il nostro metodo principale per la gestione del tutto <b>( mandare/ricevere dati al/dal client ) </b>
     * @param socket {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public  void startServer(Socket socket) throws IOException {
        System.out.println("new connection");

        while (true){
            String data = Utils.readData(socket);

            String operation = this.getOperation(data);

            if (data.equalsIgnoreCase("end") ){
                socket.close();
                System.out.println( "Connection closed" );
                return;
            }

            System.out.println("Client : " + data);

            if(StringUtils.isNumeric(operation))
                Utils.sendData(socket, "result = " + operation);
            else
                Utils.sendData(socket, data.toUpperCase().concat(" (from Server)"));
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
