package com.liqz.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket socket = null;
			BufferedReader bufferedReader = null;
			while (null != (socket = serverSocket.accept())) {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String content = null;
				while(null != (content = bufferedReader.readLine())) {
					System.out.println("接收到客户端信息：[" + content + "]");
					//往客户端发消息
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					bw.write("服务端已接收消息：[" + content + "]");
					bw.flush();
					bw.close();
					//
				}
				bufferedReader.close();
				socket.close();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
