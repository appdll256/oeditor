package oeditor;

import javax.swing.JEditorPane;

class ArrayHelper{
  
  static String[] stringArrayOutput(String input){
    return input.split(System.lineSeparator());
  }  

  static String[] addStringToStringArray(String[] old, String add){
    String[] buffer = new String[old.length];
    System.arraycopy(old, 0, buffer, 0, old.length);
    buffer[buffer.length - 1] = add;
    return buffer;
  }
  
  static JEditorPane[] addEditorPaneToOther(JEditorPane[] old, JEditorPane add){
    JEditorPane[] buffer = new JEditorPane[old.length];
    System.arraycopy(old, 0, buffer, 0, old.length);
    buffer[buffer.length - 1] = add;
    return buffer;
  }



}
