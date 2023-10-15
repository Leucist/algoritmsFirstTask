import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("in_E_1_Nowik.txt");          /* Initialising file with input intervals */
        Scanner scanner = new Scanner(inputFile);

        int intervalsAmount = scanner.nextInt();
        int[][] inputIntervals = new int[intervalsAmount][2];        /* Initialises an array for storing given intervals */

        for (int i = 0; i < intervalsAmount; i++) {                 /* Fills input intervals into the array */
            inputIntervals[i][0] = scanner.nextInt();
            inputIntervals[i][1] = scanner.nextInt();
        }

        int[][] outputIntervals1 = solution1(inputIntervals);

        // Writes output data into the file
        try (FileWriter outputFile = new FileWriter("output.txt")) {
            outputFile.write(Integer.toString(outputIntervals1.length+1) + '\n');
            for (int i = 0; i <= outputIntervals1.length; i++) {
                String output = Integer.toString(outputIntervals1[i][0]) + ' ' + Integer.toString(outputIntervals1[i][1]) + '\n';
                outputFile.write(output);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] solution1 (int[][] inputIntervals) {
        if (inputIntervals == null || inputIntervals.length <= 1) return inputIntervals;
        int intervalsAmount = inputIntervals.length;
        // sorting
        inputIntervals = sortArray(inputIntervals);

        // adding intervals
        int[][] mergedIntervals = new int[intervalsAmount][2];
        int[] currentInterval = inputIntervals[0];
        int counter = 0;

        for (int i = 1; i < intervalsAmount; i++) {
            if (currentInterval[1] >= inputIntervals[i][0]) {                /* if two intervals collide */
                // when arrays are sorted by the beginning of the interval in ascending order =>
                // ..'intervals[i][0] <= intervals[i+1][0]' is always true.

                // takes the larger end among the two intervals
                // currentInterval[1] = currentInterval[1] > inputIntervals.get(i)[1] ? currentInterval[1] : inputIntervals.get(i)[1];
                currentInterval[1] = Math.max(currentInterval[1], inputIntervals[i][1]);
            }
            else {
                mergedIntervals[counter++] = currentInterval;
                currentInterval = inputIntervals[i];
            }
        }
        mergedIntervals[counter] = currentInterval;                                 /* adds the last interval */
        // Creates a new array outputIntervals, containing only non-empty elements
        int[][] outputIntervals = new int[counter][2];
        System.arraycopy(mergedIntervals, 0, outputIntervals, 0, counter);

        return outputIntervals;
    }

    public static int[][] sortArray(int[][] inputArray) {
        if (inputArray == null || inputArray.length < 2) return inputArray;
        // Initialises arrays for dividing the inputArray into two parts
        int[][] childArray1 = new int[inputArray.length / 2][2];
        int[][] childArray2 = new int[inputArray.length - inputArray.length / 2][2];
        // Copies values in halves from the inputArray into the child arrays
        System.arraycopy(inputArray, 0, childArray1, 0, childArray1.length);
        System.arraycopy(inputArray, childArray1.length, childArray2, 0, childArray2.length);
        // Recursively call the sortArray method, dividing arrays further and receiving them back sorted through mergeArrays function
        childArray1 = sortArray(childArray1);
        childArray2 = sortArray(childArray2);

        return mergeArrays(childArray1, childArray2);
    }

    public static int[][] mergeArrays(int[][] array1, int[][] array2) {
        int[][] outputArray = new int[array1.length + array2.length][2];
        int pointer1 = 0, pointer2 = 0;
        for (int i = 0; i < outputArray.length; i++) {
            // if one array ends, output is filled with the rest of another
            if (pointer1 == array1.length) {
                System.arraycopy(array2, pointer2, outputArray, i, outputArray.length - i);
                break;
            }
            else if (pointer2 == array2.length) {
                System.arraycopy(array1, pointer1, outputArray, i, outputArray.length - i);
                break;
            }
            outputArray[i] = array1[pointer1][0] < array2[pointer2][0] ? array1[pointer1++] : array2[pointer2++];
        }
        return outputArray;
    }
}