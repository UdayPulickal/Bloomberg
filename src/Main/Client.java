package Main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String [] args) throws IOException {

        int num1,num2,result;
        Scanner sc=new Scanner(System.in);
        Socket s=new Socket("localhost",9999);

        Scanner sc1=new Scanner(s.getInputStream());
        System.out.println("Enter First Number");
        num1=sc.nextInt();
        System.out.println("Enter Second Number");
        num2=sc.nextInt();

        PrintStream p=new PrintStream(s.getOutputStream());
        p.println(num1);
        p.println(num2);
        result=sc1.nextInt();
        System.out.println("Result for the given two Numbers "+ result);

   }
}
