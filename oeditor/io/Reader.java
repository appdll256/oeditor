package oeditor.io;


import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;




import java.io.IOException;


public class Reader extends Thread{
  private File readTarget;
  
  private String[] intrigents;
  
  
  public Reader(File file){
    readTarget = file;
  }

  
  public String[] readFileLines(){
    start();
    return intrigents;
  }

  
  public OpenFile readFile(){
    return new OpenFile(readTarget, readFileLines());
  }


  
  @Override
  public void run(){
    try{
      BufferedReader reader = new BufferedReader(new FileReader(readTarget));
      intrigents = (String[]) reader.lines().toArray();
      reader.close();
    }catch(IOException ioex){
      StackTraceHelper.writeCatchedExceptionStackTrace(ioex.getStackTrace());
    }
  }

  
  //Note: This do nothing yet but should do in some time
  interface ProgressListener{
    public void onProgress(int progress);
  }

}
