package com.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo2_client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket=new Socket("127.0.0.1",12345);
		//���ֽ�����װ���ַ���
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream());//PrintStream����д�����еķ���
		System.out.println(br.readLine());
		ps.println("���뱨���������Ա");
		System.out.println(br.readLine());
		ps.println("��ޣ��ܲ��ܸ�һ�λ���");
		socket.close();
		
	}
}
