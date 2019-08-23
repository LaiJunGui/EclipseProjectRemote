package com.socket;
/*存在的问题：Socket会关闭，不知道为什么，只能开启一次
 * nihao
  127.0.0.1:54212:nihao
    我
  java.net.SocketException: Socket is closed
	at java.net.DatagramSocket.send(DatagramSocket.java:658)
	at com.socket.send.run(Demo03_MoreThread.java:58)
 * */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Demo03_MoreThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Receive().start();
		new send().start();
	}
}
	class Receive extends Thread{
		public void run() {
			try {
				//创建Socket相当于创建码头
				DatagramSocket socket =new DatagramSocket(6666);
				//创建Packet相当于创建集装箱
				DatagramPacket packet=new DatagramPacket(new byte[1024], 1024);
				while (true) {
				//接货，接受数据
				socket.receive(packet);
				//获取数据
				byte[] arr=packet.getData();
				String ip=packet.getAddress().getHostAddress();//获取IP地址
				int port=packet.getPort();//获取端口号
				//获取有效字节个数
				int len=packet.getLength();
				System.out.println(ip+":"+port+":"+new String(arr, 0, len));
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	class send extends Thread{
		public void run() {
			try {
				Scanner sc = new Scanner(System.in);
				DatagramSocket socket = new DatagramSocket();// 创建Socket相当于创建码头
				while (true) {
					String line = sc.nextLine();
					if ("quit".equals(line)) {
						break;
					}
					// 创建Packet相当于创建集装箱
					DatagramPacket packet = new DatagramPacket(line.getBytes(), line.getBytes().length,
							InetAddress.getByName("127.0.0.1"), 6666);
					socket.send(packet);// 发货，将数据发出去
					socket.close();// 关闭码头
				}
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}