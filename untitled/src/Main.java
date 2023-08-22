import java.util.Random;
import java.util.Scanner;
//Sebastian Tafur
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int columnas;  //w => (int) Ancho en caracteres
        do {
            System.out.println("Ingresa el ancho del ambiente [10,20,40,80]: ");
            columnas = scanner.nextInt();

            if (columnas != 10 && columnas != 20 && columnas != 30 && columnas != 40 && columnas != 80) {
                System.out.println("El valor ingresado no es valido");
            }
        } while (columnas != 10 && columnas != 20 && columnas != 30 && columnas != 40 && columnas != 80);


        int filas; //h => (int) Altura en caracteres
        do {
            System.out.println("Ingresa la altura del ambiente [10,20,40]: ");
            filas = scanner.nextInt();

            if (filas != 10 && filas != 20 && filas != 30 && filas != 40) {
                System.out.println("El valor ingresado no es valido");
            }
        } while (filas != 10 && filas != 20 && filas != 30 && filas != 40);

        int velocidad;     //s => (int) La velocidad en milisegundos de las generaciones
        do {
            System.out.print("Introduce la velocidad en milisegundos [250-1000]: ");
            velocidad = scanner.nextInt();

            if (velocidad <250 || velocidad > 1000){
                System.out.println("Valor no valido");
            }
        }while (velocidad <250 || velocidad > 1000);

        int numGeneraciones;    //g => (int) El número de generaciones que se van a ejecutar
        do {
            System.out.print("Ingresa el numero de generaciones: ");
            numGeneraciones = scanner.nextInt();
            if (numGeneraciones<0){
                System.out.println("El numero debe ser 0 o mayor");
            }
        }while (numGeneraciones<0);

        //Escoger semilla o aleatorio
        int option;
        do {
            System.out.print("1. Rnd -> aleatoria \n" + "2. Semilla \n");
            System.out.print("Debes escoger si quieres una matriz aleatoria o ingresar una semilla: ");
            option = scanner.nextInt();

            if (option != 1 && option !=2){
                System.out.println("Valor no valido");
            }
        }while (option != 1 && option !=2);


        int[][] ambiente = new int[filas][columnas];

        Random random = new Random();


        //llenar matriz
        if (option == 1) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    ambiente[i][j] = random.nextInt(2);
                }
            }
        } else {
            scanner.nextLine();
            System.out.println("Ingresa la semilla de poblacion: ");
            String semilla = scanner.nextLine();

            String[] filasSemilla = semilla.split("#");

            // matriz por poblacion
            for (int i = 0; i < filas && i < filasSemilla.length; i++) {
                String filaSemilla = filasSemilla[i];
                for (int j = 0; j < columnas && j < filaSemilla.length(); j++) {
                    char c = filaSemilla.charAt(j);
                    if (c == '1') {
                        ambiente[i][j] = 1;
                    } else {
                        ambiente[i][j] = 0;
                    }
                }
            }
        }


        //Imprimir matriz
        System.out.println("Generacion Original");
        imprimirMatriz(ambiente);

        //Generaciones
        if (numGeneraciones ==0){
            while (numGeneraciones==0){
                for (int gen = 1; gen > numGeneraciones; gen++) {
                    clearConsole();
                    System.out.println("Generación " + gen + ":");
                    ambiente = generaciones(ambiente, filas, columnas);
                    imprimirMatriz(ambiente);
                    Thread.sleep(velocidad);
                }
            }
        } else {
            for (int gen = 1; gen <= numGeneraciones; gen++) {
                clearConsole();
                System.out.println("Generación " + gen + ":");
                ambiente = generaciones(ambiente, filas, columnas);
                imprimirMatriz(ambiente);
                Thread.sleep(velocidad);
            }
        }


        scanner.close();

    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int[][] generaciones(int ambiente[][], int filas, int columnas){

        int[][] ambienteCopia = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                int celulasVivas = 0;
                for (int k = -1; k <=1 ; k++) { //Fila arriba y abajo
                    for (int l = -1; l <=1 ; l++) { //Columna derecha e izquierda
                        if ((i+k>=0 && i+k<filas) && (j+l>=0 && j+l<columnas)){ //evitar desbordamiento
                            celulasVivas += ambiente[i+k][j+l];
                        }
                    }
                }
                    celulasVivas -= ambiente[i][j];
                    //Regla #1
                    if ((ambiente[i][j] == 1) && (celulasVivas < 2)){
                        ambienteCopia[i][j] = 0;
                    }
                    //Regla #2
                    else if ((ambiente[i][j] == 1) && (celulasVivas > 3)) {
                        ambienteCopia[i][j] = 0;
                    }
                    //Regla #3
                    else if ((ambiente[i][j] == 0) && (celulasVivas == 3)) {
                        ambienteCopia[i][j] = 1;
                    }else {
                        ambienteCopia[i][j] = ambiente[i][j];
                    }
            }
        }
        return ambienteCopia;
    }

    public static void imprimirMatriz(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (j == 0){
                    System.out.print("| ");
                }

                String cell;
                if (matriz[i][j] == 1) {
                    cell = "\u25A0"; // celula viva (cuadrado negro)
                } else {
                    cell = "\u25A1"; // celula muerta (cuadrado blanco)
                }
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("+---------------------------------------+");
    }

}



