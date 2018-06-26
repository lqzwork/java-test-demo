package com.liqz.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class TestClient {
   public static void main(String[] args) {
      try {
         Socket socket = new Socket("127.0.0.1",8888);
         
         //构建IO
         InputStream is = socket.getInputStream();
         OutputStream os = socket.getOutputStream();
         
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
         //向服务器端发送一条消息
         bw.write("客户端向服务器端发送的消息\n");
         bw.flush();
         
         //读取服务器返回的消息
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String mess = br.readLine();
         System.out.println("服务器返回消息：" + mess);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
