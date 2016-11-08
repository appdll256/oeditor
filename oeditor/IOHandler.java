package oeditor;

import oeditor.io.*;

import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.io.FileNotFoundException;

class IOHandler{
  
  static OpenFile readFile(File file){
    Reader reader = new Reader(file);
    return reader.readFile();
  }
  
  static String[] readFileLines(File file){
    Reader reader = new Reader(file);
    return reader.readFileLines();
  }

  static void writeFile(OpenFile file){
    Writer writer = new Writer();
    writer.writeToFile(file);
  }
  
  static void writeFile(File file, String[] data){
    Writer writer = new Writer();
    writer.writeToFile(file, data);
  }
  
  static void writeFile(File file, String data){
    writeFile(file, new String[]{data});
  }

}
