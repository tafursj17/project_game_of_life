import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //w => (int) Ancho en caracteres
        //h => (int) Altura en caracteres
        //g => (int) El número de generaciones que se van a ejecutar
        //s => (int) La velocidad en milisegundos de las generaciones

        int filas = 5;
        int columnas = 5;

        int[][] ambiente = new int[filas][columnas];

        Random random = new Random();

        //llenar matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                ambiente[i][j] = random.nextInt(2);
            }
        }

        //Imprimir matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("[" +ambiente[i][j]+ "]");
            }
            System.out.println();
        }

        //Generaciones
        //int gen = 10;
        generaciones(ambiente, filas, columnas);

    }

    public static void generaciones(int ambiente[][], int filas, int columnas){
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
        System.out.println("Nueva generacion");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("[" +ambienteCopia[i][j]+ "]");
            } System.out.println();
        }




    }




}