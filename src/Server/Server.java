package Server;

import Utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


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
    private Double getOperation(String data) throws ArithmeticException{
        data = Utils.parsing(data);

        if (data.contains(sum)){
            String[] numbers = data.split("\\" + sum);
            return (Utils.sum(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(subtraction)){
            String[] numbers = data.split(subtraction);
            return (Utils.subtraction(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(multiplication)){
            String[] numbers = data.split("\\" + multiplication);
            return (Utils.multiplication(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));

        }else if(data.contains(division)){
            String[] numbers = data.split(division);
            return (Utils.division(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1])));
        }
        return null;
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
            Double operation = null;

            if (data.equals("0")){
                socket.close();
                System.out.println( "Connection closed" );
                return;
            }

            System.out.println("Client : " + data);

            try {
                operation = this.getOperation(data);
            }catch (ArithmeticException e){
                data = e.getMessage();
            }

            if(operation != null)
                Utils.sendData(socket, "result = " + operation);
            else
                Utils.sendData(socket, data.toUpperCase().concat(" (from Server)"));
        }
    }

    /**
     * metodo che restuisce la port del server sul quale rimane in ascolto
     * @return int
     */
    public int getPort() { return port; }

    public static void main(String... args) throws IOException, NullPointerException {
        Server server = new Server();
        System.out.println("Server Listen on Port : " + server.getPort());
        server.startServer(server.getServerSocket());
    }


}
