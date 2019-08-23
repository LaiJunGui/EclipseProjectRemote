package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/* 
 * 1,��ʾ����Ҫ�ϴ����ļ�·������֤·���Ƿ�����Լ��Ƿ����ļ���
 * 2�������ļ����������
 * 6�����ս����������ڸ�����ʾ������ֱ���˳�
 * 7����������ڣ�����FileInuputStream��ȡ�ļ���д��������
 * */
public class Test2_UpdateClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
	//1,��ʾ����Ҫ�ϴ����ļ�·������֤·���Ƿ�����Լ��Ƿ����ļ���
		File file =getFile();
	//2�������ļ����������
		Socket socket=new Socket("127.0.0.1", 12345);
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream());
		ps.println(file.getName());
	//6�����ս����������ڸ�����ʾ������ֱ���˳�
		String result =br.readLine();
		if("����".equals(result)) {
			System.out.println("���ϴ����ļ��Ѿ����ڣ��벻Ҫ�ظ��ϴ�");
			socket.close();
			return;
		}
		//7����������ڣ�����FileInuputStream��ȡ�ļ���д��������,ps�ȿ���д�ַ�����Ҳ����д�ֽڴ�
		FileInputStream fis=new FileInputStream(file);
		byte[] arr=new byte[8192];
		int len;
		while ((len=fis.read(arr))!=-1) {
			ps.write(arr,0,len);
		}
		fis.close();
		socket.close();
	}
	private static File getFile() {
		Scanner sc=new Scanner(System.in);//��������¼�����
		System.out.println("������һ���ļ�·����");
		while(true) {
			String line=sc.nextLine();
			File file=new File(line);
			if(!file.exists()) {
				System.out.println("��¼����ļ�·�������ڣ����������룺");
			}else if(file.isDirectory()) {
				System.out.println("��¼������ļ���·����������һ���ļ�·��");
			}else {
				return file;
			}
		}
		}
}
