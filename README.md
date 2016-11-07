# oeditor
## Description
This is the source for O-Editor, an open source editor, for some file edits, based on java.

## Download this repo
Download the zip and then unzip or use <code>git clone https://github.com/appdll256/oeditor</code>

## Compilation
To compilate this, make these steps :

1. Switch to the repository root dir, it __must__ be <b>oeditor</b> for a successful compilation! 

2. Now execute the *java compiler* __javac__ in your terminal or command line panel using this:
  * <code>javac oeditor/*.class</code>
  * __Note__: You can also put more __javac__ arguments in, e. g. <code>-g</code> for debugging, if you want to
  * __Note__: For the __javac__ command, you need the *JDK (Java Development Kit)* installed

3. You *can* execute the program on two ways:
  - Use the __java__ command directly: <code>java oeditor/MainClass</code>
    * __Note__: You can also put more __jvm__ arguments in, as you like it

  - Pack first to a jar-archive and then execute with __java__:
    * Pack with the __jar__ tool: <code>jar cfm [name].jar Manifest.txt oeditor/*.class</code>
      * __Note__: You can also put more __jar__ arguments in, as you want
      * __Note__: For the __jar__ command, you need the *JDK (Java Development Kit)* installed
    * Then execute with the __java__ command: <code>java -jar [name].jar</code>
      * __Note__: You can also put more __jvm__ arguments in, as you would like to

And now we are happy :smiley:

## Other notes
_Production State = INDEV_

_License = MIT_
