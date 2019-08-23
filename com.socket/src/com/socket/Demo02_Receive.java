package com.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * 2.���ܶ�Receive
 *����DatagramSocket,ָ���˿ں�
 *����DatagramSocket��ָ�����飬����
 *ʹ��DatagramSocket����DatagramPacket
 *�ر�DatagramSocket
 *��DatagramPacket�л�ȡ����
 * */
public class Demo02_Receive {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		//����Socket�൱�ڴ�����ͷ
		DatagramSocket socket =new DatagramSocket(6666);
		//����Packet�൱�ڴ�����װ��
		DatagramPacket packet=new DatagramPacket(new byte[1024], 1024);
		while (true) {
		//�ӻ�����������
		socket.receive(packet);
		//��ȡ����
		byte[] arr=packet.getData();
		String ip=packet.getAddress().getHostAddress();//��ȡIP��ַ
		int port=packet.getPort();//��ȡ�˿ں�
		//��ȡ��Ч�ֽڸ���
		int len=packet.getLength();
		System.out.println(ip+":"+port+":"+new String(arr, 0, len));
		}
//		socket.close();
	}

}