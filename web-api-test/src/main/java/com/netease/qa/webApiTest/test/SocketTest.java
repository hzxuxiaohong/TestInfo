package com.netease.qa.webApiTest.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		Socket socket    = new Socket("10.165.124.145",8181);
		OutputStream os   = socket.getOutputStream();
		boolean autoflush = true;
		PrintWriter out   = new PrintWriter( socket.getOutputStream(), autoflush );
		BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream() ));

		// 向Web服务器发送一个HTTP请求
		out.println("GET /consumer/mock?token=test HTTP/1.1");
		out.println("Host: 10.165.124.145:8181");
		out.println("Connection: close");
		out.println();

		// 读取服务器的应答
		boolean loop    = true;
		StringBuffer sb = new StringBuffer(8096);

		while (loop) {
		    if ( in.ready() ) {
		        int i=0;
		        while (i!=-1) {
		            i = in.read();
		            sb.append((char) i);
		        }
		        loop = false;
		    }
		    Thread.currentThread().sleep(50);
		}

		// 把应答显示到控制台
		System.out.println(sb.toString());
		socket.close();
	}
}
