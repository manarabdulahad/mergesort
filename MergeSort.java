import java.io.*;
import java.util.*;

public class SortComparison {

    // Method to create a random array
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);  // Random integer between 0 and 100
        }
        return array;
    }

    // Method to write the array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(Integer.toString(num));
                writer.newLine();  
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Method to read the array from a file
    public static int[] readFileToArray(String filename) {
        List<Integer> tempList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        
        
        int[] array = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            array[i] = tempList.get(i);
        }
        return array;
    }

    // Bubble Sort Algorithm
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort Algorithm
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;  // Base case: array is already sorted
        }
        
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    // Merge two sorted arrays into one
    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        
        // Copy remaining elements
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user to enter array length
        System.out.println("Enter the length of the array to create:");
        int length = scanner.nextInt();
        
        // Create a random array
        int[] array = createRandomArray(length);
        
        // Display the generated random array
        System.out.println("Generated random array:");
        System.out.println(Arrays.toString(array));

        // BubbleSort Test
        int[] bubbleSortedArray = Arrays.copyOf(array, array.length);  // Copy array to preserve original
        long startTime = System.nanoTime();
        bubbleSort(bubbleSortedArray);
        long endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;
        System.out.println("Bubble sorted array:");
        System.out.println(Arrays.toString(bubbleSortedArray));
        System.out.println("BubbleSort took " + bubbleSortTime + " nanoseconds");

        // MergeSort Test
        int[] mergeSortedArray = Arrays.copyOf(array, array.length);  // Copy array to preserve original
        startTime = System.nanoTime();
        mergeSort(mergeSortedArray);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;
        System.out.println("Merge sorted array:");
        System.out.println(Arrays.toString(mergeSortedArray));
        System.out.println("MergeSort took " + mergeSortTime + " nanoseconds");
        
      
        System.out.println("Enter a filename to save the array:");
        scanner.nextLine();  // Consume the newline character
        String filename = scanner.nextLine();
        
        writeArrayToFile(array, filename);
        System.out.println("Array has been written to " + filename);
        
        int[] arrayFromFile = readFileToArray(filename);
        System.out.println("Array read from the file:");
        System.out.println(Arrays.toString(arrayFromFile));

        scanner.close();
    }
}
