package Client;

import Utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * il nostro metodo principale per la gestione del tutto <b>( mandare/ricevere dati al/dal server ) </b>
     * @param socket {@link Socket}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public void startClient(Socket socket) throws IOException {


        while (true){
            Utils.clear();
            Utils.menu(); // to show the menu
            System.out.print("Choose a number:_ ");
            String input = (new BufferedReader( new InputStreamReader( System.in ))).readLine();

            switch (input){
                case "1":
                    System.out.print("Enter your String:_ ");
                    Utils.sendData(socket, (new BufferedReader( new InputStreamReader( System.in ))).readLine());
                    break;
                case "2":
                    Utils.clear();
                    Utils.subMenuCalc();

                    System.out.print("Choose a number:_ ");
                    switch ((new BufferedReader( new InputStreamReader( System.in ))).readLine()){
                        case "5":
                            continue;

                        case "1":
                        case "2":
                        case "3":
                        case "4":
                            System.out.print("Enter your Operation:_ ");
                            Utils.sendData(socket, (new BufferedReader( new InputStreamReader( System.in ))).readLine());
                            break;

                        default:
                            System.out.println("Wrong Choose");
                            startClient(socket);
                            break;
                    }
                    break;
                case "0":
                    Utils.sendData(socket, input);
                    socket.close();
                    System.out.println( "Connection closed");
                    return;
                default:
                    System.out.println("Wrong Choose");
                    startClient(socket);
                    break;
            }

            String data = Utils.readData(socket);

            System.out.println("Server : " + data);
        }
    }


    public static void main(String... args) throws IOException {
        Client client = new Client();
        client.startClient(client.getSocket());
    }

}
