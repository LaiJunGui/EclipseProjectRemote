package com.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 1,�ͻ���
 * ����Socket���ӷ����(ָ��ip��ַ���˿ں�)ͨ��ip��ַ�Ҷ�Ӧ�ķ�����
 * ����Socket��getInputStream()��getOutputStream()������ȡ�ͷ����������IO��
 * ���������Զ�ȡ����������д��������
 * ���������д�����ݵ�����˵�������
 * */
public class Demo01_Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket=new Socket("127.0.0.1",12345);
		InputStream is=socket.getInputStream();//��ȡ�ͻ���������
		OutputStream os=socket.getOutputStream();//��ȡ�ͻ��������
		byte[] arr=new byte[1024];
		int len=is.read(arr);//��д������������������
		System.out.println(new String(arr,0,len));//������ת�����ַ�������ӡ
		os.write("ѧϰ�ھ�������ļ�ǿ".getBytes());//�ͻ����������д����
		socket.close();
		
	}

}
