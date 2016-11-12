package oeditor.io;


import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;


import java.io.IOException;


public class Reader extends Thread{
  private File readTarget;

  private String[] lines;
  
  
  public Reader(File file){
    readTarget = file;
  }

  
  public String[] readFileLines(){
    start();
    return lines;
  }

  
  public OpenFile readFile(){
    return new OpenFile(readTarget, readFileLines());
  }


  
  @Override
  public void run(){
    try{
      BufferedReader reader = new BufferedReader(new FileReader(readTarget));
      StringBuilder builder = new StringBuilder();
      String buffer = "";
      while ((buffer = reader.readLine()) != null) { 
        builder.append(buffer + ".line.Seperator");
      }
      //Invalidate reader & buffer
      reader.close();
      buffer = null;
      lines = builder.toString().split(".line.Seperator");
    }catch(IOException ioex){
      StackTraceHelper.writeCatchedExceptionStackTrace(ioex.getStackTrace());
    }
  }

  
  //Note: This do nothing yet but should do in some time
  interface ProgressListener{
    public void onProgress(int progress);
  }

}
