package oeditor;

import java.util.Scanner;

import java.io.File;

public class MainClass{
  
  private static SettingHandler settingHandler;

  private static FileHandler fileHandler;

  private static UI ui;
  
  public static void main(String[] args){
    boolean init_successful = init();
    assert init_successful == true;
    boolean ui_build_successful = ui();
    assert ui_build_successful = true;
  }
  
  
  private static boolean init(){
    settingHandler = new SettingHandler();
    return true;
  }
  
  //Move to UI
  private static boolean ui(){
    UI ui = new UI();
    return true;
  }



}
