
package com.x.atrs.netty.bio.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author xuewenke
 * @since 2020/10/13 7:18 下午
 */
public class BioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 7397);
            Scanner sc = new Scanner(System.in);
            while (true) {
                String ms = sc.nextLine();
                if (ms != null) {
                    sendMessage(socket, ms);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(Socket socket, String message) {
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
            message = message + "\r\n";
            out.write(message.getBytes(Charset.forName("GBK")));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}