package Main;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class SocketServer1 extends Thread {
    private ServerSocket serverSocket1;
    int num1,num2,result;
    public SocketServer1(int port) throws IOException {
        serverSocket1 = new ServerSocket(port);

    }

    public void run() {
        while(true) {
            try {

                Socket ss=serverSocket1.accept();
                Scanner sc=new Scanner(ss.getInputStream());
                num1=sc.nextInt();
                num2=sc.nextInt();

                System.out.println("Numbers received from client to server-one "+num1+" "+num2);

                Socket ss2=new Socket("localhost",10000);
                Scanner sc1=new Scanner(ss2.getInputStream());

                PrintStream pserver=new PrintStream(ss2.getOutputStream());
                pserver.println(num1);
                pserver.println(num2);
                result=sc1.nextInt();

                PrintStream pclient=new PrintStream(ss.getOutputStream());
                pclient.println(result);

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }


        }
    }

    public static void main(String [] args) {
        int port = 9999;
        try {
            Thread t = new SocketServer1(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
