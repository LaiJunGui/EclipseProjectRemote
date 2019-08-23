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
					final Socket socket=server.accept();//接收客户端的请求
					new Thread() {
						public void run() {
					try {
						//将字节流包装成字符流
						BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintStream ps=new PrintStream(socket.getOutputStream());//PrintStream中有写出换行的方法
						ps.println("欢迎咨询黑马程序员");
						System.out.println(br.readLine());
						ps.println("不好意思，爆满了");
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
