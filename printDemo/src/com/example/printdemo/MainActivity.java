package com.example.printdemo;

import com.tecoimage.ezprintcn.PrintImageUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }

  public void test(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        // try {
        // Socket socket = new Socket();
        // socket.connect(new InetSocketAddress("192.168.9.14", 9100), 3000);
        // // PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        // // printWriter.write("测试");
        // // printWriter.write("abc");
        // // printWriter.write("!@#");
        // // printWriter.write("123");
        // // printWriter.close();
        // FileInputStream fis = new FileInputStream("sdcard/1.jpg");
        // PrintStream printStream = new PrintStream(socket.getOutputStream());
        // byte[] buf = new byte[1024];
        // int len = 0;
        // while ((len = fis.read()) != -1) {
        // printStream.write(buf, 0, len);
        // }
        // fis.close();
        // printStream.close();
        // socket.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        PrintImageUtils printImageUtils = new PrintImageUtils();
        printImageUtils.printImage("sdcard/1.jpg", "PCL3GUI", "HP LaserJet", "hpLaserJet.ppd",
            "paper_list.csv", "192.168.9.14");
      }
    }).start();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
