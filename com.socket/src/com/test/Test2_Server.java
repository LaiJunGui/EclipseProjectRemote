package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test2_Server {
public static void main(String[] args) throws IOException {
	ServerSocket server=new ServerSocket(54321);
	System.out.println("��������������54321�˿�");
	while(true) {
		 final Socket socket=server.accept();
		new Thread() {
			public void run(){//����һ���߳�
				try {
					BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintStream ps=new PrintStream(socket.getOutputStream());//��ȡ�����
					
					String line=br.readLine();//���ͻ���д���������ݶ�ȡ����
					line =new StringBuilder(line).reverse().toString();//��ʽ���
					ps.println(line);//��ת��д�ط�����
					
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
