import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class bubbleSort extends Thread
{
    int currantSIndex;
    boolean sortSub = false;

    public  bubbleSort() {}
    public bubbleSort(int index)
    {
        this.currantSIndex = index;
        sortSub = true;
    }

    void bubbleSortArray (int [] list)
    {
        boolean swapped = false;
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public void run()
    {
        if(sortSub) {
            try {
                this.bubbleSortArray(sortArray.subArrays[currantSIndex]);
            } catch (Exception e) { System.err.println(e); }
            sortSub = false;
        }
        else
        {
            try {
                this.bubbleSortArray(sortArray.mergedArray);
            } catch (Exception e) { System.err.println(e); }
        }
    }
}

class sortArray
{
    static int [][] subArrays;
    static int [] mergedArray;

    static int [][] sliceArray( int [] array, int splitSize)
    {
        int numOfSubArrays = (int)Math.ceil((double)array.length / splitSize);
        int [][] slicedArrays = new int[numOfSubArrays][];
        for (int i=0; i < numOfSubArrays; i++)
        {
            int startIndex = i * splitSize;
            int subArrayLength = Math.min(array.length - startIndex, splitSize);
            int [] holdTemp = new int[subArrayLength];
            System.arraycopy(array,startIndex,holdTemp,0,subArrayLength);
            slicedArrays[i] = holdTemp;
        }
        return slicedArrays;
    }

    static int setSplitSize(int size)
    {
        int splitSize = 0;
        if(size<=1000) {splitSize = 10;}
        if(size>1000 && size<=10000) {splitSize = 100;}
        if(size>10000){splitSize = 500;}
        return splitSize;
    }

    static void print(int [] array)
    {
        for(int val: array)
        {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main ( String [] arg)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the size of Array : ");
        long  arraySize = scan.nextLong();
        int [] arrayToSort = new int[(int)arraySize];

        if (arraySize <= 0) {
            System.out.println("Invalid Array Size");
            System.exit(0);
        }

        System.out.println("Enter " + arraySize + " elements : ");
        for(int i=0; i < arraySize; i++)
        {
            arrayToSort[i] = scan.nextInt();
        }

        subArrays = sliceArray(arrayToSort,setSplitSize(arrayToSort.length));

        //Using a new thread for each subArray to sort
        for (int i=0; i < subArrays.length; i++)
        {
            Thread sort = new Thread(new bubbleSort(i));
            sort.start();
            try {
                Thread.sleep(10);
            }catch (Exception e){}
        }

        //Printing sorted subArrays
        System.out.println("Sorted SubArrays : ");
        for (int i=0; i < subArrays.length; i++)
        {
            System.out.println("-> SubArray " + (i+1) + " :");
            print(subArrays[i]);
        }

        //Merging SubArrays into a single 1D array
        mergedArray = Stream.of(subArrays).flatMapToInt(IntStream::of).toArray();

        System.out.println("\nFinal Array before Sorting: ");
        print(mergedArray);
        //Another Thread to sort the final merged array
        Thread sortFinal = new Thread(new bubbleSort());
        sortFinal.start();
        try {
            Thread.sleep(10);
        }catch (Exception e){}
        System.out.println("\nFinal Sorted Array: ");
        print(mergedArray);
    }
}