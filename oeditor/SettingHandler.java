package oeditor;


import java.io.*;


class SettingHandler{
  private LangStrings langStrings;
  
  SettingHandler(){
    langStrings = new LangStrings(getPreferLanguage());
  }

  
  LangStrings getLangStrings(){
    return langStrings;
  }

  
  private String getPreferLanguage(){
    return "";
  }


  private class LangStrings{
    LangStrings(String language){
      
    }

    
    String getLangStringAtPosition(int position){
      
      return "";
    }

  }

  private class Settings{
    private String language;
  }

  
}
