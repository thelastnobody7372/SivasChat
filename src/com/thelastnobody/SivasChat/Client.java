package com.thelastnobody.SivasChat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream out;
    String Data;
    ProcessCodes Status = ProcessCodes.RL;
    Scanner ConsoleReader = new Scanner(System.in);
    String LUserName;
    String LPassWord;
    public User LocalUser;

    public Client(String address,int Port) {

            LocalUser = GetData();
            String line = "";
            try {
                socket = new Socket(address,Port);
                System.out.println("works");
                input = new BufferedReader(new InputStreamReader(System.in));
                out = new DataOutputStream(socket.getOutputStream());
            }catch (UnknownHostException e){
                System.out.println(e);
            }catch (IOException e ){
                System.out.println(e);
            }
            try {
                line = input.readLine();
            }catch(IOException i)
            {
                System.out.println(i);
            }
            while (!line.equals("Stop"))
                try
                {
                    line = input.readLine();
                    if(line.equals("Stop")) return;
                    line = ParsePack(new Pack(LocalUser,line));// reads the line from the keyboard
                    System.out.println(LocalUser.UserName);

                    out.writeUTF(LocalUser.adminstration == Adminstration.Member || LocalUser.adminstration == Adminstration.Admin ? line : "");
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }

    public User GetData(){
        System.out.println("Login or Register:");
            switch (ConsoleReader.next().toLowerCase()){
                case "login":
                    Status = ProcessCodes.log;
                    return register();
                case "register":
                    Status = ProcessCodes.Reg;
                    return register();
                default:
                    break;


            }
            return new User("mr.Default","root",Adminstration.Visitor);
    }

    public String ParsePack(Pack pack){
        return pack.Message + "#" + pack.Sender.UserName + "#" + pack.Sender.adminstration + "#" + pack.Sender.PIN;
    }


    private User register(){
        System.out.println("works()");

        System.out.print(" Enter userName => ");
        LUserName = ConsoleReader.next();

        System.out.print(" Enter password => ");
        LPassWord = ConsoleReader.next();

        return new User(LUserName,LPassWord,Adminstration.Member);

    }
}

