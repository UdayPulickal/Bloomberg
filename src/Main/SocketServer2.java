package Main;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class SocketServer2 extends Thread {
    private ServerSocket serverSocket2;
    int num1,num2,result;
    public SocketServer2(int port) throws IOException {
        serverSocket2 = new ServerSocket(port);

    }

    public void run() {
        while(true) {
            try {

                Socket ss=serverSocket2.accept();
                Scanner sc=new Scanner(ss.getInputStream());
                num1=sc.nextInt();
                num2=sc.nextInt();
                System.out.println("Numbers received from server1 to server2 "+num1+" "+num2);
                result=num1*num2;
                System.out.println();
                PrintStream p=new PrintStream(ss.getOutputStream());
                p.println(result);
                System.out.println("Result sent to server1"+result);

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
        int port = 10000;
        try {
            Thread t = new SocketServer2(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}