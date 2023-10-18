import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");          /* Initialising file with input intervals */
        Scanner scanner = new Scanner(inputFile);

        int intervalsAmount = scanner.nextInt();
        int[][] inputIntervals1 = new int[intervalsAmount][2];   /* Initialises two equal arrays for storing given intervals */
        int[][] inputIntervals2 = new int[intervalsAmount][2];

        for (int i = 0; i < intervalsAmount; i++) {                 /* Fills input intervals into the array */
            inputIntervals1[i][0] = scanner.nextInt();
            inputIntervals1[i][1] = scanner.nextInt();
            inputIntervals2[i][0] = inputIntervals1[i][0];
            inputIntervals2[i][1] = inputIntervals1[i][1];
        }

        int[][] outputIntervals1 = solution1(inputIntervals1);
        int[][] outputIntervals2 = solution2(inputIntervals2);

        // Writes output data into the file
        try (FileWriter outputFile = new FileWriter("output.txt")) {
            outputFile.write(Integer.toString(outputIntervals1.length) + '\n');
            for (int i = 0; i < outputIntervals1.length; i++) {
                String output = Integer.toString(outputIntervals1[i][0]) + ' ' + outputIntervals1[i][1] + '\n';
                outputFile.write(output);
            }

            outputFile.write(Integer.toString(outputIntervals2.length) + '\n');
            for (int i = 0; i < outputIntervals2.length; i++) {
                String output = Integer.toString(outputIntervals2[i][0]) + ' ' + outputIntervals2[i][1] + '\n';
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
        int[] currentInterval = inputIntervals[0].clone();
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
        mergedIntervals[counter++] = currentInterval;                                 /* adds the last interval */
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

    public static int[][] solution2 (int[][] inputIntervals) {
        if (inputIntervals == null || inputIntervals.length <= 1) return inputIntervals;
        int[][] mergedIntervals = new int[inputIntervals.length][2];
        int maxValue = 1000;                    /* value was given in the task description */
        int[] numberLine = new int[maxValue];   /* number line would have an offset of -1 */

        // Fills the number line with '1' (start with '2') if there is interval lying at this position from the given data. Otherwise, it's '0'
        for (int i = 0; i < inputIntervals.length; i++) {
            int j = inputIntervals[i][0] - 1;
            numberLine[j] = numberLine[j++] == 1 ? 1 : 2;
            while(j < inputIntervals[i][1]) {
                numberLine[j++] = 1;
            }
        }
        // Reformats merged intervals from a number line into an array of limits
        int counter = 0;
        boolean insideInterval = false;
        for (int i = 0; i < maxValue; i++) {
            if (!insideInterval && numberLine[i] == 2) {
                mergedIntervals[counter][0] = i + 1;
                insideInterval = true;
            }
            else if (insideInterval && (numberLine[i] == 0 || i + 1 == maxValue)) {
                mergedIntervals[counter++][1] = i;
                insideInterval = false;
            }
            else if (insideInterval && numberLine[i] == 2) {
                mergedIntervals[counter++][1] = i;
                mergedIntervals[counter][0] = i + 1;
            }
        }
        // Creates a new array outputIntervals, containing only non-empty elements
        int[][] outputIntervals = new int[counter][2];
        System.arraycopy(mergedIntervals, 0, outputIntervals, 0, counter);

        return outputIntervals;
    }
}