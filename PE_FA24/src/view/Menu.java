
package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public abstract class Menu {
     protected String title;
    protected ArrayList<String> mChon;
    
    public Menu(){}
    public Menu(String td, String[] mc){
        title=td;
        mChon= new ArrayList<>();
        mChon.addAll(Arrays.asList(mc));
    }

    public void display(){
        System.out.println(title);
        System.out.println("--------------------------------");
        for(int i=0; i<mChon.size();i++)
            System.out.println((i+1)+"."+mChon.get(i));
        System.out.println("--------------------------------");
    }

    public int getSelected(){
        display();
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter selection..");
        return sc.nextInt();
    }

    public abstract void execute(int n);

    public void run(){
        while(true){
            int n=getSelected();
            execute(n);
            if(n>mChon.size()) break;
        }
    }
}
