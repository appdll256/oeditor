package oeditor;



class StackTraceHelper{
  static String[] convertStackToStrings(StackTraceElement[] stack){
    String[] stacky = new String[stack.length];
    for (int i = 0; i < stack.length; i++) {
      stacky[i] = stack[i].toString();
    }
    return stacky;
  }
}
