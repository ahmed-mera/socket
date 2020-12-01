package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * class utils to help us for doing some operation without declare the new object.
 * @author Ahmed Mera
 */
public class Utils {
    /**
     * un metodo per leggere la data che il server che ci ha inviato
     * @param socket {@link Socket}
     * @return String {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public static String readData(Socket socket) throws IOException {
        return (new BufferedReader( new InputStreamReader( socket.getInputStream() ))).readLine();
    }


    /**
     * un metodo per mandare la data al server
     * @param socket {@link Socket}
     * @param data {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public static void sendData(Socket socket, String data) throws IOException {
        (new PrintWriter( socket.getOutputStream(), true)).println(data);
    }


    /**
     * funzione per sommare due elementi
     * @param num1 deve essere un numero
     * @param num2 deve essere un numero
     * @param <T> un tipo generico della nostra funzione
     * @return il resulto della somma
     */
    public static  <T extends Number> double sum(T num1, T num2){
        return num1.doubleValue() + num2.doubleValue();
    }


    /**
     * funzione per la fare la divisione di  due elementi
     * @param num1 deve essere un numero
     * @param num2 deve essere un numero
     * @param <T> un tipo generico della nostra funzione
     * @return il resulto della divisione
     * @throws ArithmeticException must be catched
     */
    public static <T extends Number> double division(T num1, T num2) throws ArithmeticException{
        if(num2.doubleValue() == 0)
            throw new ArithmeticException("non puoi dividere per zero");

        return  num1.doubleValue() / num2.doubleValue();
    }


    /**
     * funzione per la fare la multipicazione di  due elementi
     * @param num1 deve essere un numero
     * @param num2 deve essere un numero
     * @param <T> un tipo generico della nostra funzione
     * @return il resulto della multiplicazione
     */
    public static <T extends Number> double multiplication(T num1, T num2){
        return num1.doubleValue() * num2.doubleValue();
    }

    /**
     * funzione per la fare la sotrazione di  due elementi
     * @param num1 deve essere un numero
     * @param num2 deve essere un numero
     * @param <T> un tipo generico della nostra funzione
     * @return il resulto della sotrazione
     */
    public static <T extends Number> double subtraction(T num1, T num2){
        return num1.doubleValue() - num2.doubleValue();
    }


    /**
     * funzione per togliere gli spazi,
     * @param value type {@link String}
     * @return il resultato senza spazi
     */
    public static String parsing(String value){
        return value.replaceAll("\\s+", "");
    }

    /**
     * helper function to show the menu
     */
    public static void menu(){
        System.out.println("1- Convertire una stringa");
        System.out.println("2- Calcolatrice");
        System.out.println("0- Exit");
    }



    /**
     * helper function to show the sub menu
     */
    public static void subMenuCalc() {
        System.out.println("1- Sum");
        System.out.println("2- Subtraction");
        System.out.println("3- Multiplication");
        System.out.println("4- Division");
        System.out.println("5- Menu principale");
    }


    /**
     * helper function to clear console
     * @throws IOException must be catch
     */
    public static void clear() throws IOException {
        if ((System.getProperty("os.name").contains("Windows"))) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }



}
