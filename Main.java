/* 
 * @Autor Najera Noyola Karla Andrea
 * @Fecha 25 de septiembre de 2022
 * @Descripción Clase principal que solicita datos al usuario para aplicar RC4.
*/

import java.util.Scanner;

/*
 * Clase principal del programa a partir de la cual funciona todo. 
 */
public class Main {
    /*
     * Metodo principal que realiza toda la interacción con el usuario. 
     */
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String key="";
        String texto="";
        String mensaje="";

        // Datos de bienvenida
        System.out.println(" **********     RCA Algorithm     ********** ");
        System.out.println(" *****    Created by: Karla Najera     ***** ");

        int op=0;
        // Ciclo que permite la repetición del menú de opciones
        while(op!=3){
            System.out.println("\n Select an option: ");
            System.out.println("  1.-Encrypt ");
            System.out.println("  2.-Decrypt ");
            System.out.println("  3.-Exit \n ");
            
            // Selección del usuario
            op=sc.nextInt();
            sc.nextLine();

            System.out.println("");
            // Menú del programa
            switch(op){ 
                case 1: // Encriptado del mensaje
                    System.out.print("Key: ");
                    key=sc.nextLine();
                    System.out.print("Plaintext: ");
                    texto=sc.nextLine();
                    Rc4 encriptado=new Rc4(key, texto);
                    mensaje = encriptado.encriptar();
                    System.out.println("Encrypted Message: "+mensaje);
                    break;
                case 2: // Desencriptado del mensaje
                    System.out.print("Key: ");
                    key=sc.nextLine();
                    System.out.print("Encrypted Text: ");
                    texto=sc.nextLine();
                    Rc4 desencriptado=new Rc4(key, texto);
                    mensaje = desencriptado.desencriptar();
                    System.out.println("Decrypted Message: "+mensaje);
                    break;
                case 3: // Salida del programa
                    System.out.println("Goodbye.");
                    break;
                default: // Mensaje en caso de opción incorrecta
                    System.out.println("Wrong option. Try again");
                    break;
            }
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
        sc.close();
    }
}
