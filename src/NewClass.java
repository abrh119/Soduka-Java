/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class NewClass {
    


    public static void main(String[] args) {
        String data[] = {"Bob", "Ali", "Amy", "Tom", "Dal", "Eva", "Gen", "Ian",
            "Jak", "Jim", "Joe", "Kay", "Ron", "Kim", "Roy", "Tim"};
        System.out.println("Unsorted Array is: " + Arrays.toString(data));
        
        boolean swapMade = true;
        
        while(swapMade){
            swapMade = false;
            for(int i = 0; i < data.length - 1; i++){
                if(data[i].compareTo(data[i + 1]) > 0){
                    System.out.print("First: " + data[i]);
                    System.out.println("\t\tSecond: " + data[i+1]);
                    String temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    swapMade = true;
                }
    }
        }
                System.out.println("Sorted name list: ");
                for(String data_t:data){
                    System.out.printf("%s\t", data_t);
        }
    }
}
