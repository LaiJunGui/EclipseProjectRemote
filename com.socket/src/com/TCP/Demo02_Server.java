package com.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 2�������
 * ����ServerSorcket(��Ҫָ���˿ں�)
 * ����ServerSocket��accept()��������һ���ͻ������󣬵õ�һ��Socket
 * ����Socket��getInputStream()��getOutputStream()������ȡ�Ϳͻ���������IO��
 * ���������Զ�ȡ�ͻ��������д��������
 * ���������д�����ݵ��ͻ��˵�������
 * */
public class Demo02_Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server =new ServerSocket(12345);
		Socket socket=server.accept();//���տͻ��˵�����
		InputStream is=socket.getInputStream();//���տͻ���������
		OutputStream os=socket.getOutputStream();//��ȡ�ͻ��˵������
		os.write("�ٶ�һ�����֪��".getBytes());//��������ͻ���д������
		byte[] arr=new byte[1024];
		int len=is.read(arr);//��д�ͻ��˷�����������
		System.out.println(new String(arr,0,len));//������ת�����ַ�������ӡ
	}

}
