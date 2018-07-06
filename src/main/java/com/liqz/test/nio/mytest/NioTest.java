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
		//buffer数据传输测试
		testTransferFromBuffer();
		System.out.println("---------buffer数据传输测试完成-----------");
		
		//对象流测试
		NioTest nioTest = new NioTest();
		nioTest.testObjectInputStream();
		System.out.println("---------对象流测试完成-----------");
		
		//通道数据传输测试
		testTransferFromChannel();
		System.out.println("---------通道数据传输测试完成-----------");
		
		System.out.println(3 | 2);
	}
	
	/**
	 * buffer数据传输测试
	 */
	public static void testTransferFromBuffer() {
		File file = new File("D:/a/timg.jpg");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通道数据传输测试
	 */
	public static void testTransferFromChannel() {
		try {
			File file = new File("D:/a/nio.txt");
			FileInputStream fis = new FileInputStream(file);
			File file2 = new File("D:/a/nio2.txt");
			FileOutputStream fos = new FileOutputStream(file2);
			//获取通道
			FileChannel ci = fis.getChannel();
			FileChannel co = fos.getChannel();
			//nio通道传输
			co.transferFrom(ci, 0, ci.size());
			ci.close();
			co.close();
		} catch (Exception e) {
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
		} catch (Exception e) {
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
