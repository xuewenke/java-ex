

package com.x.atrs.netty.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuewenke
 * @since 2020/10/13 5:34 下午
 */
public class SocketHandler extends Thread {
    /**
     * 当前的连接
     */
    private Socket socket;

    /**
     * 当前连接处理的编码
     */
    private Charset charset;


    public SocketHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        clientConnectAdvice(socket, charset);
    }


    /**
     * 当有新的连接进来的时候通知
     *
     * @param socket
     * @param charset
     */
    public void clientConnectAdvice(Socket socket, Charset charset) {
        System.out.println("监听到新的连接LocalAddress:" + socket.getLocalAddress());
    }


    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String messageLine = input.readLine();
            while (messageLine != null) {
                printMessageFromClient(messageLine);
                messageLine = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void printMessageFromClient(String message) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + message);
        replyMessage2Client();
    }

    private void replyMessage2Client() {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(("消息已收到\r\n").getBytes(charset));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}