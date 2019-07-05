/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidor.sd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Servidor {

    private static ArrayList mensagem = new ArrayList(2);
    private static int resposta = 0;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket;
        ServerSocket server = new ServerSocket(10999);

        while (true) {
            socket = server.accept();
            ObjectInputStream obj = new ObjectInputStream(socket.getInputStream());
            mensagem = (ArrayList) obj.readObject();
            int num = (int) mensagem.get(0);
            int num2 = (int) mensagem.get(1);
           
            //Tipo de operacao
            if (mensagem.get(2).equals("soma")) {

                resposta = num + num2;
               

            } else {
                resposta = num - num2;
                
            }

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(resposta);
            out.writeInt(resposta);
            out.close();

        }
    }

}
