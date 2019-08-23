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
		//将字节流包装成字符流
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream());//PrintStream中有写出换行的方法
		System.out.println(br.readLine());
		ps.println("我想报名黑马程序员");
		System.out.println(br.readLine());
		ps.println("大哭，能不能给一次机会");
		socket.close();
		
	}
}
