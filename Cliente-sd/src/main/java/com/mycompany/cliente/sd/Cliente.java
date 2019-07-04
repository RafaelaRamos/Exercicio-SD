/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente.sd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Cliente
 */
public class Cliente {

    private static Random rd = new Random();
    private static final ArrayList op = new ArrayList(1);
    private static final ArrayList mensagem = new ArrayList(2);

    public Cliente() {

    }

    public static void main(String[] args) throws IOException {
        int resposta = 0;
        op.add("soma");
        op.add("sub");
        mensagem.add(rd.nextInt(10));
        mensagem.add(rd.nextInt(10));
        mensagem.add(op.get(0));

        Socket socket = null;

        socket = new Socket("localhost", 10999);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        output.writeObject(mensagem);

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        resposta = input.readInt();

        requestReply(resposta);

    }

    public static void requestReply(int resposta) throws IOException {
        while (true) {
            Socket socket = new Socket("localhost", 10999);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Collections.shuffle(op);
            mensagem.set(0, rd.nextInt(10));
            mensagem.set(1, resposta);
            mensagem.set(2, op.get(0));
            output.writeObject(mensagem);
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            resposta = input.readInt();
            System.out.print(resposta);

        }
    }
}
