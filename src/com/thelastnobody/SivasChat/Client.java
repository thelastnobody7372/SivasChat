package com.thelastnobody.SivasChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader input;
    private DataOutputStream out;
    private Boolean İsCreated = false;
    String Data;
    int QueryNum = 2;
    ProcessCodes Status = ProcessCodes.RL;
    Scanner ConsoleReader = new Scanner(System.in);
    String LUserName;
    String LPassWord;
    Boolean RegisterSucess;
    public User LocalUser;

    public Client(String address,int Port) {
        System.out.print("Login Or Register:");
        LocalUser = GetData();


            String line = "";
            try {
                socket = new Socket(address,Port);
                input = new BufferedReader(new InputStreamReader(System.in));
                out = new DataOutputStream(socket.getOutputStream());

            }catch (UnknownHostException e){
                System.out.println(e);
            }catch (IOException e ){
                System.out.println(e);
            }

            while (!line.equals("Stop"))
            {
                try
                {
                    line = ParsePack(new Pack(LocalUser,input.readLine()));// reads the line from the keyboard

                    out.writeUTF(line);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }




    }
    public User GetData(){
        //User LocalUser = new User();

        while (!İsCreated && !(QueryNum == 0)) {
            Data = ConsoleReader.next();
            if (Data.isEmpty()) {


                Data = ConsoleReader.next();


                İsCreated = true;

            }
        }

        if (İsCreated) {

            if (Data == "Login") {
                İsCreated = false;
                QueryNum = 2;
                Status = ProcessCodes.log;
            } else if (Data == "Register") {
                İsCreated = false;
                QueryNum = 3;
                Status = ProcessCodes.Reg;
            }

            //Login
            if (Status == ProcessCodes.log) {
                if (Data.toLowerCase().startsWith("name:")) {
                    LUserName = Data.split(":")[1];
                    QueryNum = 1;
                }

                if(Data.toLowerCase().startsWith("password:")){
                    LPassWord = Data.split(":")[1];
                    QueryNum = 0;

                }

                if(QueryNum == 0){
                    return new User(LUserName,LPassWord,Adminstration.Member);
                }



            }

            //Register
            if (Status == ProcessCodes.Reg) {

                if (Data.toLowerCase().startsWith("name:")) {
                    LUserName = Data.split(":")[1];
                    QueryNum = 2;
                }

                if (Data.toLowerCase().startsWith("Password:")) {
                    LPassWord = Data.split(":")[1];
                    QueryNum = 1;
                }

                if (Data.toLowerCase().startsWith("Password Again:")) {
                    if(Data.split(":")[1] == LPassWord){
                        QueryNum = 0;
                    }else{
                        System.out.println("Passwords Are not same");
                    }

                }
            }


        }

        return new User("mr.Default","1234",Adminstration.Member);
    }

    public String ParsePack(Pack pack){
        return pack.Message + "#" + pack.Sender.UserName + "#" + pack.Sender.adminstration + "#" + pack.Sender.PIN;
    }
}

