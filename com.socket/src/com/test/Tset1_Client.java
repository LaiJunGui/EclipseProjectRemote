package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 *�ͻ����������д�ַ�(����¼��)��������(���߳�)���ַ�����ת��д�أ�
 *�ͻ����ٴζ�ȡ���Ƿ�ת����ַ���
 * */
public class Tset1_Client {
public static void main(String[] args) throws UnknownHostException, IOException {
	Scanner sc=new Scanner(System.in);//������¼�����
	Socket socket=new Socket("127.0.0.1", 54321);//�����ͻ��ˣ�ָ��IP��ַ�Ͷ˿ں�
	//��ȡ������
	BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintStream ps=new PrintStream(socket.getOutputStream());//��ȡ�����
	ps.println(sc.nextLine());//���ַ���д��������ȥ
	System.out.println(br.readLine());//����ת��Ľ��������
	socket.close();
}
}
