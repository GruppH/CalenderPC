/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

/**
 *
 * @author Calle Skanshed
 */
public class Calender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calender calender = new Calender();
        calender.print("hej");
        System.out.println("asddasdsad");
        //765
        calender.count(10000000);
        //hhhhh
    }
    
    public void print(String toPrint) {
        System.out.println(toPrint);
    }
    
    public void count(int i) {
        for(int j=0; j<i; j++) {
            System.out.println(j);
        }
    }
    
}
