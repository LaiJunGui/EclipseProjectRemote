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
 * ����DatagramSocket������˿ں�
 * ����DatagramPacket,ָ�����ݣ����ȣ���ַ���˿�
 * ʹ��DatagramSocket����DatagramPacket
 * �ر�DatagramSocket
 * @throws SocketException
 * */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// String str="what are you Ūɶ��";
		Scanner sc = new Scanner(System.in);
		DatagramSocket socket = new DatagramSocket();// ����Socket�൱�ڴ�����ͷ
		while (true) {
			String line = sc.nextLine();
			if ("quit".equals(line)) {
				break;
			}
			// ����Packet�൱�ڴ�����װ��
			DatagramPacket packet = new DatagramPacket(line.getBytes(), line.getBytes().length,
					InetAddress.getByName("127.0.0.1"), 6666);
			socket.send(packet);// �����������ݷ���ȥ
			socket.close();// �ر���ͷ
		}
	}
}
