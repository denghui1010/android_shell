package com.ldh.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.os.Handler;
import android.os.Message;

public class ShellUtil {
  private OutputListener mOutPutListener;
  private Process proc;
  private Handler handler = new Handler() {
    public void handleMessage(android.os.Message msg) {
      String line = (String) msg.obj;
      mOutPutListener.onOneLineOutput(line);
    };
  };
  private ProcessBuilder pb;
  private BufferedReader in;
  private PrintWriter out;
  private ResultThread resultThread;

  public void execute(String commond) {
    if (mOutPutListener == null) {
      return;
    }
    out.println(commond);
  }

  public void setOutputListener(OutputListener outputListener) {
    mOutPutListener = outputListener;
  }

  public void newShell() {
    pb = new ProcessBuilder("/system/bin/sh");
    pb.directory(new File(Constant.DIRECTORY));// 设置shell的当前目录
    pb.redirectErrorStream(true);
    try {
      proc = pb.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    new ResultThread().start();
  }

  public void closeShell() {
    if (out != null) {
      out.close();
    }
    if (in != null) {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    proc.destroy();
  }

  class ResultThread extends Thread {
    public void run() {
      in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      out =
          new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
      String line;
      try {
        while ((line = in.readLine()) != null) {
          Message msg = Message.obtain();
          msg.obj = line + "\n";
          sleep(10);
          handler.sendMessage(msg);
        }
      } catch (Exception e) {
        Message msg = Message.obtain();
        msg.obj = "exception:" + e + "\n";
        handler.sendMessage(msg);
        e.printStackTrace();
      }
    }
  }

}