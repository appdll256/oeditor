package oeditor.io;


import java.io.File;

import java.io.BufferedWriter;
import java.io.FileWriter;


import java.io.IOException;


public class Writer extends Thread{
  private Object writeTarget;

  private String[] buffer;



  public void writeToFile(OpenFile file){
    writeTarget = file;
    start();
  }


  public void writeToFile(File file, String[] content){
    writeTarget = file;
    //Preventing null-pointer
    if (buffer == null || buffer.length == 0) {
      buffer = new String[]{""};
    }else{
      buffer = content;
    }
  }

  
  
  
  
  
  @Override
  public void run(){
    try{
      if (writeTarget instanceof File) {
        BufferedWriter writer = new BufferedWriter(new FileWriter((File) writeTarget));
        for (String bufferUnit : buffer) {
          writer.write(bufferUnit);
          writer.newLine();
        }
        writer.close();
      }
      
      if (writeTarget instanceof OpenFile) {
        OpenFile openFile = (OpenFile) writeTarget;
        BufferedWriter writer = new BufferedWriter(new FileWriter(openFile.getRepresentFile()));
        String[] content = openFile.getContentLines();
        for (String line : content) {
          writer.write(line);
          writer.newLine();
        }
        writer.close();
      }

    }catch(IOException ioex){
      StackTraceHelper.writeCatchedExceptionStackTrace(ioex.getStackTrace());
    }


  }

  

}
