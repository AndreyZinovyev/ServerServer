import java.net.*;
import java.io.*;



public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msgNum = in.readLine();
            boolean fin = false;
            try {
                double ret = Double.parseDouble(msgNum);
                System.out.println("Server send message: " + msgNum);
                out.println(ret*ret);
                fin = true;

            }
            catch (NumberFormatException e){
                e.printStackTrace();
            }
            finally {
                if(fin)
                {
                    System.out.println("Сообщение отправлено");
                }
                else {
                    out.println("Вы ввели не число,. повторите опытку позже");
                    System.out.println("Клиент отправил не число");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server stop");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println("Server start");
        GreetServer server=new GreetServer();
        server.start(6666);
    }
}