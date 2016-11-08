package oeditor.io;

import java.io.File;

public class OpenFile{
  private File correspondingFile;
  
  private String[] lines;
  
  OpenFile(File file, String[] intrigents){
    correspondingFile = file;
    intrigents = lines;
  }


  public File getRepresentFile(){
    return correspondingFile;
  }

  public String[] getContentLines(){
    return lines;
  }
  
}
