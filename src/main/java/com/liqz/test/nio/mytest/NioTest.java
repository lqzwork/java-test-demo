package com.liqz.test.nio.mytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NioTest implements Serializable{
	public static void main(String[] args) {
		try {
			File file = new File("D:/a/timg.jpg");
			FileInputStream fis = new FileInputStream(file);
			File file2 = new File("D:/a/timg2.jpg");
			FileOutputStream fos = new FileOutputStream(file2);
			//获取通道
			FileChannel ci = fis.getChannel();
			FileChannel co = fos.getChannel();
			//创建缓冲区
			ByteBuffer bb = ByteBuffer.allocate(1024);
			while ((ci.read(bb)) != -1) {
				bb.flip();
				co.write(bb);
				bb.clear();
			}
			ci.close();
			co.close();
			System.out.println("----------------完成---------------");
			NioTest nioTest = new NioTest();
			nioTest.testObjectInputStream();
			System.out.println("---------666-----------");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testObjectInputStream() {
		File file = new File("D:/a/user.txt");
		User1 user1 = new User1();
		user1.setAge(10);
		user1.setName("user1");
		user1.setSer("男");
		try {
			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
			objOutput.writeObject(user1);
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
			User1 user = (User1) objInput.readObject();
//			User2 user = (User2) objInput.readObject();
			System.out.println(user.getName());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	public void testMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			//
		}
	}
	
	class User1 implements Serializable{
		private static final long serialVersionUID = 1L;
		private String name;
		private String ser;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSer() {
			return ser;
		}
		public void setSer(String ser) {
			this.ser = ser;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
	
	class User2 {
		private static final long serialVersionUID = 1L;
		private String name;
		private String ser;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSer() {
			return ser;
		}
		public void setSer(String ser) {
			this.ser = ser;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
	
}
