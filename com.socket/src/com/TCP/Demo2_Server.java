package com.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Demo2_Server {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//demo1();
	}

	public static void demo1() throws IOException {
		ServerSocket server =new ServerSocket(12345);
		while(true) {
					final Socket socket=server.accept();//���տͻ��˵�����
					new Thread() {
						public void run() {
					try {
						//���ֽ�����װ���ַ���
						BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintStream ps=new PrintStream(socket.getOutputStream());//PrintStream����д�����еķ���
						ps.println("��ӭ��ѯ�������Ա");
						System.out.println(br.readLine());
						ps.println("������˼��������");
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										}
					}.start();
			}
	}
}
