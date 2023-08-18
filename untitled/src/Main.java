import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //w => (int) Ancho en caracteres
        //h => (int) Altura en caracteres
        //g => (int) El número de generaciones que se van a ejecutar
        //s => (int) La velocidad en milisegundos de las generaciones

        int filas = 20;
        int columnas = 30;

        System.out.print("Introduce la velocidad en milisegundos [250-1000]: ");
        int velocidad = scanner.nextInt();
        scanner.close();

        int[][] ambiente = new int[filas][columnas];

        Random random = new Random();

        //llenar matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                ambiente[i][j] = random.nextInt(2);
            }
        }

        //Imprimir matriz
        System.out.println("Generacion Original");
        imprimirMatriz(ambiente);

        //Generaciones

        int numGeneraciones = 3; // Cambia este valor al número de generaciones que desees
        for (int gen = 1; gen <= numGeneraciones; gen++) {
            System.out.println("Generación " + gen + ":");
            ambiente = generaciones(ambiente, filas, columnas);
            imprimirMatriz(ambiente);
            Thread.sleep(velocidad);
        }

        scanner.close();

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
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    
}