import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //w => (int) Ancho en caracteres
        //h => (int) Altura en caracteres
        //g => (int) El número de generaciones que se van a ejecutar
        //s => (int) La velocidad en milisegundos de las generaciones

        int w = 5;
        int h = 5;

        int[][] ambiente = new int[w][h];

        Random random = new Random();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                ambiente[i][j] = random.nextInt(2);
            }
        }


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                System.out.print("[" +ambiente[i][j]+ "]");
            }
            System.out.println();
        }
    }
}