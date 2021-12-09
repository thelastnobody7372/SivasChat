package com.thelastnobody.SivasChat;

import java.util.Scanner;

public class Main  {

    public static void main(String[] args){
        boolean Started = false;
        Scanner ConsoleReader = new Scanner(System.in);
        System.out.print("Do you want to create a server or connect:");
        String CrOrCn = (String) ConsoleReader.next();
        while(CrOrCn != null && !Started){
            if(CrOrCn.equalsIgnoreCase("create")){
                try{
                    System.out.print("İP:");
                    String IP = ConsoleReader.next();
                    System.out.print("Port:");
                    int PORT = ConsoleReader.nextInt();
                    Server server = new Server(IP,PORT);
                    Started = true;
                }catch (InvalidPortException e){
                    System.out.println("InvalidPortException" + e);
                }

            }else if(CrOrCn.equalsIgnoreCase("connect")) {

                    System.out.print("İP:");
                    String IP = ConsoleReader.next();
                    System.out.print("Port:");
                    int PORT = ConsoleReader.nextInt();
                    Client client = new Client(IP,PORT);
                    Started = true;

            }else{
                CrOrCn = null;

            }
        }

    }
}
