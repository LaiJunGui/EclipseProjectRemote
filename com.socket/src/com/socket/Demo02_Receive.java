package com.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * 2.接受端Receive
 *创建DatagramSocket,指定端口号
 *创建DatagramSocket，指定数组，长度
 *使用DatagramSocket接受DatagramPacket
 *关闭DatagramSocket
 *从DatagramPacket中获取数据
 * */
public class Demo02_Receive {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
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
//		socket.close();
	}

}
