package oeditor;



import java.io.File;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.FileDialog;
import java.awt.ScrollPane;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.IOException;


class UI{
  private static IOHandler ioHandler;
  
  private File[] openedFiles = new File[1];
  private JEditorPane[] editorPanes = new JEditorPane[1];
  
  private Frame rootFrame;
  
  private MenuBar rootMenuBar;
  
  private Menu fileMenu;
  
  private Menu editMenu;

  private JTabbedPane tabbedPane;
  
  UI(){
    ioHandler = new IOHandler();
    initUI();   
  }

  
  
  private void initUI(){
    //RootWindow
    rootFrame = new Frame("O-Editor");
    //Set size
    rootFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
    //Set close action
    rootFrame.addWindowListener(new WindowListener(){
      @Override
      public void windowDeactivated(WindowEvent e){}
      
      @Override
      public void windowActivated(WindowEvent e){}
      
      @Override
      public void windowDeiconified(WindowEvent e){}

      @Override
      public void windowIconified(WindowEvent e){}
      
      @Override
      public void windowClosed(WindowEvent e){}

      @Override
      public void windowClosing(WindowEvent e){
        rootFrame.dispose();
        System.exit(0);
      }
      
      @Override
      public void windowOpened(WindowEvent e){}

    });

    //Menu bar
    rootMenuBar = new MenuBar();
    //"File"
    fileMenu = new Menu("File");
    
    //"Open"
    MenuItem openFile = new MenuItem("Open");
    openFile.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent ae){
        openFile();
      }
    });
    
    //"Save"
    MenuItem saveFile = new MenuItem("Save");
    saveFile.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        saveFile(false);
      }
    });
    
    //"Save under..."
    MenuItem saveFileUnder = new MenuItem("Save file under...");
    saveFileUnder.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //Show FileDialog
        FileDialog chooser = new FileDialog(new Frame(), "Save file under...", FileDialog.SAVE);
        chooser.setVisible(true);
        String selected = chooser.getFile();
        if (selected != null) {
          File selectedFile = new File(selected);
          int selectedIndex = tabbedPane.getSelectedIndex();
          ioHandler.writeFile(selectedFile, editorPanes[selectedIndex].getText());
          tabbedPane.setTitleAt(selectedIndex, selectedFile.getPath());
        }
      }
    });
    
    //"Print"
    MenuItem printFile = new MenuItem("Print");

    //"Exit"
    MenuItem exit = new MenuItem("Exit");
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
    editMenu = new Menu("Edit");

    //"Undo"
    MenuItem undo = new MenuItem("Undo");

    //"Redo"
    MenuItem redo = new MenuItem("Redo");





    //Add menu items
    editMenu.add(undo);
    editMenu.add(redo);


    //"Help"
    Menu helpMenu = new Menu("Help");

    //"Manual"
    MenuItem manual = new MenuItem("Manual");

    //"Updates"
    MenuItem checkForUpdate = new MenuItem("Check for update");
    
    //"About"
    MenuItem about = new MenuItem("About");





    //Add menu items
    helpMenu.add(manual);
    helpMenu.add(checkForUpdate);
    helpMenu.add(about);










    
    //Addings to root
    rootMenuBar.add(fileMenu);
    rootMenuBar.add(editMenu);
    rootMenuBar.add(helpMenu);
    
    
    //Adding to frame
    rootFrame.setMenuBar(rootMenuBar);
    
    
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
    FileDialog chooser = new FileDialog(new Frame(), "Open file...", FileDialog.LOAD);
    chooser.setVisible(true);
    String selected = chooser.getFile();
    if (selected != null) {
      File selectedFile = new File(selected);
      
      tabbedPane.addTab(selectedFile.getPath(), editPaneWithScrollBars(IOHandler.readFileLinesWithSeperators(selectedFile)));
      
    }
  }
  
  
  private void saveFile(boolean fileChooser){
    if(fileChooser){
      //Show FileDialog
      FileDialog chooser = new FileDialog(new Frame(), "Save file under...", FileDialog.SAVE);
      chooser.setVisible(true);
      String selected = chooser.getFile();
      if (selected != null) {
        File selectedFile = new File(selected);
        int selectedIndex = tabbedPane.getSelectedIndex();
        ioHandler.writeFile(selectedFile, editorPanes[selectedIndex].getText());
        tabbedPane.setTitleAt(selectedIndex, selectedFile.getPath());
      }
    }else{
      int selected = tabbedPane.getSelectedIndex();
      ioHandler.writeFile(new File(tabbedPane.getTitleAt(selected)), editorPanes[selected].getText());
    }
  }



  private ScrollPane editPaneWithScrollBars(String data){
    ScrollPane scrollPane = new ScrollPane();
    ArrayHelper.addEditorPaneToOther(editorPanes, new JEditorPane());
    editorPanes[editorPanes.length - 1].setText(data);
    //If no error occurred
    scrollPane.add(editorPanes[editorPanes.length - 1]);
    return scrollPane;
  }


}
