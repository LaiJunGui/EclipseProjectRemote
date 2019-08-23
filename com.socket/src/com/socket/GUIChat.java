package com.socket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.annotation.processing.Messager;
import javax.swing.text.View;

public class GUIChat extends Frame{
	private Button clear;
	/*
	 * GUI����
	*/
	private TextField tf;
	private Button send;
	private Button log;
	private Button shake;
	private TextArea viewText;
	private TextArea sendText;
	private DatagramSocket socket;
	private BufferedWriter bw;
public GUIChat() {
	init();
	southPanel();
	centerPanel();
	event();
}
public void event() {
		// TODO Auto-generated method stub
		this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			try {
				socket.close();
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
		});
		
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					send();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					logFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewText.setText("");
			}
		});
		shake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//shake();
				try {
					send(new byte[] {-1},tf.getText());//
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_ENTER&&e.isControlDown()) {
				//isControlDown() ctrl�Ƿ񱻰���
				try {
					send();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
	}
	private void shake() {
	// TODO Auto-generated method stub
		int x=this.getLocation().x;//��ȡ������λ��
		int y=this.getLocation().y;//��ȡ������λ��
		for(int i=0;i<20;i++) {
				try {
					this.setLocation(x+20,y+20);
					Thread.sleep(20);
					this.setLocation(x+20,y-20);
					Thread.sleep(20);
					this.setLocation(x-20,y-20);
					Thread.sleep(20);
					this.setLocation(x,y);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	private void logFile() throws IOException {
		// TODO Auto-generated method stub
		bw.flush();//ˢ�»�����
		FileInputStream fis=new FileInputStream("config.txt");
		ByteArrayOutputStream baos=new ByteArrayOutputStream();//���ڴ��д���������
		int len;
		byte[] arr=new byte[8192];
		while((len=fis.read(arr))!=-1) {
			baos.write(arr,0,len);
		}
		String str=baos.toString();//���ڴ��е�����ת�������ַ���
		viewText.setText(str);//������append��
		fis.close();
	}
private void send(byte[]arr,String ip) throws IOException {
	DatagramPacket packet=
			   new DatagramPacket(arr,arr.length, InetAddress.getByName(ip), 9999);
			socket.send(packet);//��������
}
private void send() throws IOException{
	// TODO Auto-generated method stub
	String message =sendText.getText();//��ȡ������������� 
	String ip=tf.getText();//��ȡip��ַ
	ip=ip.trim().length()==0?"255.255.255.255":ip;
	send(message.getBytes(),ip);
  //	viewText.setText(message);
	//����Ϣ��ӵ���ʾ������
	String time=getCurrentTime();//��ȡ��ǰʱ��
	String str = time+"�Ҷ�"+(ip.equals("255.255.255.255")?"������":ip)+"˵\r\n"+message+"\r\n\r\n";//alt+shift+l��ȡ�ֲ�����
	viewText.append(str);//����Ϣ��ӵ���ʾ������
	bw.write(str);//������д�����ݿ���
	sendText.setText("");
}
private String getCurrentTime() {
	// TODO Auto-generated method stub
	//Date d=new Date();//������ǰ���ڶ���
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��HH��mm:ss");
	return sdf.format(d);//��ʱ���ʽ��
}
public void centerPanel() {
		// TODO Auto-generated method stub
		Panel center= new Panel();//�����м��Panel
		viewText = new TextArea(5,1);
		sendText = new TextArea();
		center.setLayout(new BorderLayout());  //����Ϊ�߽粼�ֹ�����
		center.add(sendText, BorderLayout.SOUTH);//���͵��ı���������ϱ�
		center.add(viewText, BorderLayout.CENTER);//��ʾ����������м�
		viewText.setEditable(false);//���ò����Ա༭
		viewText.setBackground(Color.white);//���ñ�����ɫ
		sendText.setFont(new Font("xxx",Font.PLAIN,18));
		viewText.setFont(new Font("xxx",Font.PLAIN,18));
		this.add(center, BorderLayout.CENTER);//��Panel����Frame���ϱ�
		
	}
public void southPanel() {
		// TODO Auto-generated method stub
		Panel south =new Panel();			//�����ϱߵ�Pannel
		tf = new TextField(15);
		tf.setText("127.0.0.1");
		send = new Button("����");
		log = new Button("��¼");
		clear = new Button("����");
		shake = new Button("��");
		south.add(tf);
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
	   this.add(south,BorderLayout.SOUTH);	//��Panel����Frame���Ա�
	}
private void init() {
	this.setLocation(500, 50);
	this.setSize(400, 600);
	new Receive().start();
	try {
		socket = new DatagramSocket();
		bw=new BufferedWriter(new FileWriter("config.txt",true));//��Ҫ��β��׷��
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//����˿ں�
	this.setVisible(true);
}
private class Receive extends Thread{
	//���պͷ�����Ҫͬʱִ�У����Զ���ɶ��̵߳�
	public void run() {
		try {
			DatagramSocket socket=new DatagramSocket(9999);
			DatagramPacket packet=new DatagramPacket(new byte[8192], 8192);
			while(true) {
						socket.receive(packet);//������Ϣ
						byte[] arr =packet.getData();//��ȡ�ֽ�����
						int len =packet.getLength();//��ȡ��Ч���ֽ�����
						if(arr[0]==-1&&len==1) {//������͹����������һ���洢����-1����������ĳ�����1
							shake();//�����𶯷���
							continue;//��ֹ����ѭ���������´�ѭ������Ϊ�𶯺���Ҫִ������Ĵ���
						}
						String message=new String(arr,0,len);//ת�����ַ���
						String time=getCurrentTime();//��ȡ��ǰʱ��
						String ip=packet.getAddress().getHostAddress();//��ȡip��ַ
						String str = time+" "+ip+" ����˵��\r\n"+message+"\r\n\r\n";
						viewText.append(str);
						bw.write(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public static void main(String[] args) {
	new GUIChat();
}
}
