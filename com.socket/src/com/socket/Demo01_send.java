package com.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Demo01_send {
/*
 * ����DatagramSocket������˿ں�
 * ����DatagramPacket,ָ�����ݣ����ȣ���ַ���˿�
 * ʹ��DatagramSocket����DatagramPacket
 * �ر�DatagramSocket
 * @throws SocketException
 * */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		String str="what are you Ūɶ��";
			DatagramSocket socket=new DatagramSocket();//����Socket�൱�ڴ�����ͷ
			//����Packet�൱�ڴ�����װ��
			DatagramPacket packet=new DatagramPacket(str.getBytes(),str.getBytes().length,InetAddress.getByName("127.0.0.1"),6666);
		socket.send(packet);//�����������ݷ���ȥ
		socket.close();//�ر���ͷ
	}

}
