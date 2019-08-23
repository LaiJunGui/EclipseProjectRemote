package com.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Demo02_send {
/*
 * 创建DatagramSocket，随机端口号
 * 创建DatagramPacket,指定数据，长度，地址，端口
 * 使用DatagramSocket发送DatagramPacket
 * 关闭DatagramSocket
 * @throws SocketException
 * */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// String str="what are you 弄啥呢";
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
	}
}
