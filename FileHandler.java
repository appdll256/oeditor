package oeditor;

import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.io.FileNotFoundException;

class FileHandler extends Thread{
  
  static final short read = 0;
  
  static final short write = 1;


  private short taskforce;

  private File subject;
  
  private String[] buffer;
  
  private String buffer2;
  
  String[] readFile(File file){
    taskforce = read;
    subject = file;
    run();
    return buffer;
  }
  
  void writeFile(File file, String data){
    taskforce = write;
    if(!file.exists()){
      try{
        file.createNewFile();
      }catch(IOException ioex){
        System.err.println("Warning: File not created, lets run anyway...");
      }
    }
    subject = file;
    buffer2 = data;
    run();
  }

  @Override
  public void run(){
    switch (taskforce) {
      case read: 
        try{
          BufferedReader reader = new BufferedReader(new FileReader(subject));
          //Obtain line numbers
          int lines = 0;
          while (reader.readLine() != null) { 
            lines++;
          }
          buffer = new String[lines];
          if(buffer.length == 0){
            //For preventing null pointer
            buffer[0] = "";
          }else{
            //Fill in buffer
            String current = reader.readLine();
            for(int i = 0; buffer.length > i && current != null; i++){
              buffer[i] = current;
              current = reader.readLine();
            }
          reader.close();
          }
        }catch(FileNotFoundException notFound){
          buffer = new String[1];
          buffer[0] = "File not found!";
        }catch(IOException ioex){
          buffer = new String[1];
          buffer[0] = "I/O error!";
        }
        break;
      case write: 
        try{
          BufferedWriter writer = new BufferedWriter(new FileWriter(subject));
          writer.write(buffer2);
          writer.newLine();
        }catch(IOException ioex){
          System.out.println("I/O exception while writing...");
        }
        writer.close();
        break;
    }
  }

  
}
