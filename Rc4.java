public class Rc4 {
    int[] S;            // Arreglo de 256 posiciones
    int [] keystream;   // Keystream generado en el algoritmo
    String key;         // Clave para encriptar
    String texto;       // Texto a cifrar/descifrar

    /* 
     * Constructor de la clase
     * Recibe la clave y el texto que se cifrará/descifrará
     */
    public Rc4(String key, String texto){
        this.key=key;
        this.texto=texto;
    }

    /*
     * Key-Scheduling Algorithm
     * Se usa para inicializar permutación en la matriz
     * Realiza los cambios directamente en las variables de instancia
     */
    public void ksa(){
        // Se inicializa la matriz S[]
        this.S=new int[256];
        // Se obtiene la longitud del key
        int longKey=key.length();
        // Llenado del vector S con valores consecutivos
        for(int i=0; i<256; i++){
            S[i]=i;
        }
        // Mezcla de bytes en la matriz S[]
        int j=0, aux=0;
        for(int i=0; i<256; i++){
            j=(j+S[i]+key.charAt(i%longKey))%256;
            aux=S[i];
            S[i]=S[j];
            S[j]=aux;
        }
    }

    /*
     * Pseudo-random generating algorithm (PRGA)
     * Genera el keystream (flujo de claves)
     * No toma directamente la longitud del texto debido a que
     *  el proceso en el descifrado es un poco diferente (toma la mitad)
     */
    public void prga(int longTexto){
        this.keystream=new int [longTexto];
        int i=0, j=0, k=0, aux=0, t=0;
        // Generación de la salida
        while(k<longTexto){
            i=((i+1)%256);
            j=((j+S[i])%256);
            aux=S[i];
            S[i]=S[j];
            S[j]=aux;
            t=S[(S[i]+S[j])%256];
            keystream[k]=t;
            k++;
        }      
    }

    public String encriptar(){
        int longTexto=texto.length();
        int[] ascii = new int[longTexto];
        for(int i=0; i<longTexto; i++){
            ascii[i]=texto.charAt(i);
        }
        ksa();
        prga(longTexto);
        //XOR entre el keystream y el texto plano
        int[] operXor= new int[longTexto];
        for(int i=0; i<longTexto; i++){
            operXor[i]=(ascii[i]^keystream[i]);
        }
        String mensaje="";
        String hex="";
        for(int i=0; i<longTexto; i++){
            hex=Integer.toHexString(operXor[i]);
            if(hex.length()==1){
                hex="0"+hex;
            }
            hex=hex.toUpperCase();
            mensaje+= hex;
        }
        return mensaje;
    }

    public String desencriptar(){
        String mensaje="";
        int longTexto=texto.length();
        int[] ascii = new int[longTexto];
        int aux=0;
        for(int i=0; i<longTexto; i+=2){
            ascii[aux]=(Integer.parseInt(texto.substring(i, i+2),16));
            aux++;
        }
        ksa();
        prga(longTexto/2);

        int[] operXor= new int[keystream.length];
        for(int i=0; i<keystream.length; i++){
            operXor[i]=(ascii[i]^keystream[i]);
        }
        
        for(int i=0; i<operXor.length; i++){
            mensaje+=(char)operXor[i];
        }
        return mensaje;
    }
}
