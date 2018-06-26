package com.liqz.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class TestServer {
   public static void main(String[] args) {
      try {
         ServerSocket serverSocket = new ServerSocket(8888);
         System.out.println("启动服务器....");
         Socket socket = serverSocket.accept();
//         System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
         
         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         
         //
         InputStream inputStream = socket.getInputStream();
         int first = inputStream.read();
         int second = inputStream.read();
         int length = (first << 8) + second;
         // 然后构造一个指定长的byte数组
         byte[] bytes = new byte[length];
         // 然后读取指定长度的消息即可
         inputStream.read(bytes);
         System.out.println("get message from client: " + new String(bytes, "UTF-8"));
         //
         
         //读取客户端发送来的消息
         String mess = br.readLine();
         System.out.println("客户端消息：" + mess);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         bw.write("成功" + "\n");
         bw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
