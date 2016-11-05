package oeditor;

import java.util.Scanner;

import java.io.File;

public class MainClass{
  
  private static SettingHandler settingHandler;

  private static FileHandler fileHandler;

  private static UI ui;
  
  public static void main(String[] args){
    try{
      assert init() == true;
      ui();
    }catch(Throwable throwable){
      fileHandler.writeFile(new File("log.txt"), StackTraceHelper.convertStackToStrings(throwable.getStackTrace()));
    }
  }
  
  
  private static boolean init(){
    settingHandler = new SettingHandler();
    return true;
  }
  
  //Move to UI
  private static void ui(){
    UI ui = new UI();

  }



}
