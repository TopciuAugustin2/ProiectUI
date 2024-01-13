package Bricks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomMatrixFileWriter {

     public static void GenerateMatrix(){
        int rows = 12;
        int cols = 13;
        int minValue = 0;
        int maxValue = 3;

        int[][] matrix = generateRandomMatrix(rows, cols, minValue, maxValue);

        // Scrie matricea in fisier
        writeMatrixToFile(matrix, "\\src\\Bricks\\map4.txt");
    }

    // Functie pentru generarea matricei
    private static int[][] generateRandomMatrix(int rows, int cols, int minValue, int maxValue) {
        int[][] matrix = new int[rows][cols];

        Random random = new Random();

        // Completeaza prima si ultima coloana cu 0
        for (int i = 0; i < rows; i++) {
            matrix[i][0] = 0; // Prima coloana
            matrix[i][cols - 1] = 0; // Ultima coloana
        }

        // Completeaza primele doua randuri cu 0
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }

        // Completeaza ultimele 5 randuri cu 0
        for (int i = rows - 5; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }

        // Completeaza restul matricei cu valori random intre 1 si 3
        for (int i = 2; i < rows - 5; i++) {
            for (int j = 1; j < cols - 1; j++) {
                matrix[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }

        return matrix;
    }

    // Functie pentru scrierea matricei intr-un fisier
    private static void writeMatrixToFile(int[][] matrix, String filename) {
        String filePath = new File(filename).getAbsolutePath();
        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}