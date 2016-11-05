package oeditor;


import java.net.URL;

import java.io.File;


import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.IOException;

import java.net.MalformedURLException;

class UI{
  private static FileHandler fileHandler;
  
  private File[] openedFiles = new File[1];
  private JEditorPane[] editorPanes = new JEditorPane[1];
  
  private JFrame rootFrame;
  
  private JMenuBar rootMenuBar;
  
  private JMenu fileMenu;
  
  private JMenu editMenu;

  private JTabbedPane tabbedPane;
  
  UI(){
    fileHandler = new FileHandler();
    initUI();   
  }

  
  
  private void initUI(){
    //RootWindow
    rootFrame = new JFrame("O-Editor");
    //Set size
    rootFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    //Menu bar
    rootMenuBar = new JMenuBar();
    //"File"
    fileMenu = new JMenu("File");
    
    //"Open"
    JMenuItem openFile = new JMenuItem("Open");
    openFile.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent ae){
        openFile();
      }
    });
    
    //"Save"
    JMenuItem saveFile = new JMenuItem("Save");
    saveFile.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        int selected = tabbedPane.getSelectedIndex();
        fileHandler.writeFile(new File(tabbedPane.getTitleAt(selected)), editorPanes[selected].getText());
      }
    });
    
    //"Save under..."
    JMenuItem saveFileUnder = new JMenuItem("Save file under...");
    saveFileUnder.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //Show FileChooser
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(new JDialog()) == JFileChooser.APPROVE_OPTION) {
          int selected = tabbedPane.getSelectedIndex();
          File selectedFile = chooser.getSelectedFile();
          fileHandler.writeFile(selectedFile, editorPanes[selected].getText());
          tabbedPane.setTitleAt(selected, selectedFile.getPath());
        }
      }
    });
    
    //"Print"
    JMenuItem printFile = new JMenuItem("Print");

    //"Exit"
    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });





    //Add menu items
    fileMenu.add(openFile);
    fileMenu.add(saveFile);
    fileMenu.add(saveFileUnder);
    fileMenu.addSeparator();
    fileMenu.add(printFile);
    fileMenu.addSeparator();
    fileMenu.add(exit);


    //"Edit"
    editMenu = new JMenu("Edit");

    //"Undo"
    JMenuItem undo = new JMenuItem("Undo");

    //"Redo"
    JMenuItem redo = new JMenuItem("Redo");








    //Add menu items
    editMenu.add(undo);
    editMenu.add(redo);


    //"Help"
    JMenu helpMenu = new JMenu("Help");

    //"Manual"
    JMenuItem manual = new JMenuItem("Manual");

    //"Updates"
    JMenuItem checkForUpdate = new JMenu("Check for update");






    //Add menu items
    helpMenu.add(manual);
    helpMenu.add(checkForUpdate);











    
    //Addings to root
    rootMenuBar.add(fileMenu);
    rootMenuBar.add(editMenu);
    rootMenuBar.add(helpMenu);
    
    
    //Adding to frame
    rootFrame.setJMenuBar(rootMenuBar);
    
    
    //Set Layout
    rootFrame.setLayout(new BorderLayout());
    
    //Tool panel
    //JPanel tools = new JPanel();
    
    //TabbedPane for multiple documents
    tabbedPane = new JTabbedPane();
    //Add empty tab
    editorPanes[0] = new JEditorPane();
    tabbedPane.addTab("New", editorPanes[0]);
    
    
    //Add pane to frame
    rootFrame.add(tabbedPane, BorderLayout.CENTER);
    
    
    
    

    
    //Finally
    rootFrame.setVisible(true);
  }
  
  
  private void openFile(){
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text-Files", ".txt", ".TXT"));
    if (fileChooser.showOpenDialog(new JDialog()) == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      try{
        tabbedPane.addTab(selectedFile.getPath(), editPane(selectedFile.toURI().toURL()));
      }catch(MalformedURLException exeption){
        JOptionPane.showMessageDialog(null, "Error!", "File could not be opened cause of a malformed URL. Please try again later.", JOptionPane.ERROR_MESSAGE);
      }


      
    }
  }


  private JEditorPane editPane(URL fileUrl){
    try{
      ArrayHelper.addEditorPaneToOther(editorPanes, new JEditorPane(fileUrl));
      return editorPanes[editorPanes.length - 1];
    }catch(IOException ioex){
      return new JEditorPane();
    }
  }


}
