package com.thelastnobody.SivasChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.thelastnobody.SivasChat.Color;


public class Server {

    DataInputStream in;
    DataOutputStream out;
    Scanner ConsoleReader = new Scanner(System.in);
    Boolean Running = true;

    public Server(String IP ,int Port) throws InvalidPortException {

        if(Port > 65535 || Port < 1){
            throw new InvalidPortException(Integer.toString(Port) + "was invalid");
        }else{
            try{


                ServerSocket server = new ServerSocket(Port);
                System.out.println(Color.GREEN_BOLD + "[server] " + Color.RESET + "Server Started");

                System.out.println(Color.GREEN_BOLD + "[server] " + Color.RESET + "Waiting For Clients");
                Socket socket = server.accept();

                System.out.println(Color.GREEN_BOLD + "[server] " + Color.RESET + "Client Connected");

                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                String line = "";

                while (Running)
                {
                    try
                    {

                        String Command = ConsoleReader.next();
                        if (Command == "stop"){
                            Running = false;
                        }
                        line = in.readUTF();
                        System.out.println(UnPack(line));


                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                }



            }catch (IOException e){
                System.out.println(e);
            }


        }
    }

    public Pack UnPack(String Parsed){
        String[] args = Parsed.split("#");
        String Message = args[0];
        String Username = args[1];
        String PIN = args[3];
        Adminstration adminstration = (new StringBuffer(args[2]) == new StringBuffer("Admin")) ? Adminstration.Admin : ((new StringBuffer(args[2]) == new StringBuffer("Member")) ? Adminstration.Member : Adminstration.Visitor);
        return new Pack(new User(Username,PIN,adminstration),Message);
    }
}
