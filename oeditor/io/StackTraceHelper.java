package oeditor.io;



public class StackTraceHelper{
  static void writeCatchedExceptionStackTrace(StackTraceElement[] elements){
    //Enforce data is copied to error stream
    for (StackTraceElement element : elements) {
      System.err.println(element);
    }
  }
}
