package com.socket;
/*���ڵ����⣺Socket��رգ���֪��Ϊʲô��ֻ�ܿ���һ��
 * nihao
  127.0.0.1:54212:nihao
    ��
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
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}