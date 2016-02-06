import java.util.Scanner;

import com.robobum.negocio.Robobum;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor ingrese el tamaño del campo en sus ejes X y Y respectivamente:");
        
        int tamañoX = sc.nextInt();
        int tamañoY = sc.nextInt();
        
        sc.close();
        
        Robobum robobum = new Robobum(tamañoX, tamañoY);
       

	}

}
