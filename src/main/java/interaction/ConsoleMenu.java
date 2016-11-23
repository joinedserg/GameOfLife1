package interaction;

import java.io.File;

/**
 * Created by Никита on 22.11.2016.
 */
public class ConsoleMenu {


    private final String confPath =".\\configuration";
    private final String savesPath =".\\saves";

    int choosenPath = -1;
    public int getCurrent(){
        return currentScreen;
    }

    private String[] messages;
    private char[] keys;
    int currentScreen;

    private String[] confPaths;
    private String[] savesPaths;

    public ConsoleMenu(){
        this.messages = new String[]{
                "To Start with default press r\n"+
                "Go to Load from saved files press s\n"+
                "Go to Load from config file press c\n" +
                "To Exit press e\n"
                ,
                "Choose save: \n Press 1 to run\n",
                "Choose config: \n",

        };
        keys = new char[]{'1','2','3','0'};
        currentScreen = 0;
        confPaths = getFiles(confPath);
        savesPaths = getFiles(savesPath);
        messages[1] = joinWithMass(messages[1], savesPaths);
        messages[2] = joinWithMass(messages[2], confPaths);
    }

    public String getDescription(){
        String s = messages[currentScreen];
        return s;
    }

    public int getChoosenNumber(){
        return choosenPath;
    }

    public String getPath(){
        String s = null;
        if(currentScreen == 1){
            s = savesPaths[choosenPath - 1];
        }
        if(currentScreen == 2){
            s = confPaths[choosenPath - 1];
        }
        return s;
    }

    private String[] getFiles(String str){
        File f = new File(str);
        return f.list();
    }

    private String joinWithMass(String str, String[] sm){
        int i = 1;
        for(String s: sm){

            str += i+++": "+ s + '\n';
        }
        str += "Press b to return\n";
        return str;
    }

    public int menu(char c){

        switch (c){
            case 'r':
                return 1;
            case 's':
                currentScreen = 1;
                return 2;
            case 'c':
                currentScreen = 2;
                return 3;
            case 'e':
                return 0;
            case 'b':
                currentScreen = 0;
                return 4;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                String s =String.valueOf(c);
                choosenPath = Integer.valueOf(s);
                return -3;

            default:
                return -2;
        }
    }

}
