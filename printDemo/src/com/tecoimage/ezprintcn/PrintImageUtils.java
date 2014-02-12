package com.tecoimage.ezprintcn;

public class PrintImageUtils {
  static {
    System.loadLibrary("jni_convert");
  }

  public native void imagetoprnapi(String paramString1, String paramString2, String paramString3,
      String paramString4, String paramString5, String paramString6);

  public void printImage(String str1, String str2, String str3, String str4, String str5,
      String str6) {
    imagetoprnapi(str1, str2, str3, str4, str5, str6);
  }

}
