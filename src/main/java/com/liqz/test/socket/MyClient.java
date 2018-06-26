package com.liqz.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyClient {
	public static void main(String[] args) {
		try {
			String content = null;
			Socket socket = null;
			BufferedWriter bufferedWriter = null;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			while(!"exit".equals(content = bufferedReader.readLine())) {
				socket = new Socket("127.0.0.1", 9999);
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bufferedWriter.write(content);
				bufferedWriter.flush();
				//接收服务端消息
				socket.shutdownOutput();//通过shutdownOutput通知服务器已经发送完数据，后续只能接受数据
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println(br.readLine());
				br.close();
				//
				bufferedWriter.close();
				socket.close();
			}
			System.out.println("退出Socket交互");
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
