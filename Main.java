import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Entrada de datos del usuario
        Scanner sc= new Scanner(System.in);
        String key="";
        String textoPlano="";
        String mensaje="";

        System.out.println(" **********     RCA Algorithm     ********** ");
        System.out.println(" *****    Created by: Karla Najera     ***** ");

        int op=0;
        while(op!=3){
            System.out.println("\n Select an option: ");
            System.out.println("  1.-Encrypt ");
            System.out.println("  2.-Decrypt ");
            System.out.println("  3.-Exit \n ");
            
            op=sc.nextInt();
            sc.nextLine();

            System.out.println("");

            switch(op){ // Menú del programa
                case 1: // Encriptado del mensaje
                    System.out.print("Key: ");
                    key=sc.nextLine();
                    System.out.print("Plaintext: ");
                    textoPlano=sc.nextLine();
                    Rc4 encriptado=new Rc4(key, textoPlano);
                    mensaje = encriptado.encriptar();
                    System.out.println("Encrypted Message: "+mensaje);
                    break;
                case 2: // Desencriptado del mensaje
                    System.out.print("Key: ");
                    key=sc.nextLine();
                    System.out.print("Plaintext: ");
                    textoPlano=sc.nextLine();
                    Rc4 desencriptado=new Rc4(key, textoPlano);
                    mensaje = desencriptado.desencriptar();
                    System.out.println("Decrypted Message: "+mensaje);
                    break;
                case 3: // Salida del programa
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Wrong option. Try again");
                    break;
            }
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
        sc.close();
    }
}
