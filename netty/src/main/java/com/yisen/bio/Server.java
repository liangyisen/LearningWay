package com.yisen.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket localhost = new ServerSocket(8080);
        Socket accept = localhost.accept();
        System.out.println("客户端:" + "已连接到服务器");

        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        //读取客户端发送来的消息
        String mess = br.readLine();
        System.out.println("客户端：" + mess);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bw.write(mess + "\n");
        bw.flush();
    }
}
