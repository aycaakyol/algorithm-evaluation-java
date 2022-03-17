

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.FileReader; 

public class Main {
    public static void insertionSort(int[] a) {
        for(int j = 1; j < a.length; j++) {
            int key = a[j];
            int i = j - 1;

            while(i >= 0 && a[i] > key) {
                a[i+1] = a[i];
                i = i -1;
            }
            a[i+1] = key;
        }
    }

    public static void pigeonholeSort(int[] a) {
        int n = a.length;
        int minOfArray = a[0];
        int maxOfArray = a[0];

        for(int i = 0; i < n; i++) {
            if(a[i] > maxOfArray) {
                maxOfArray = a[i];
            }

            if(a[i] < minOfArray) {
                minOfArray = a[i];
            }
        }

        int range = maxOfArray - minOfArray + 1;
        int[] holes = new int[range];
        Arrays.fill(holes, 0);

        for(int i = 0; i < n; i++) {
            holes[a[i] - minOfArray]++;
        }

        int index = 0;
        for(int i = 0; i < range; i++) {
            for(int j = 0; j < holes[i]; j++) {
                a[index] = i + minOfArray;
                index++;
            }
        }
    }

    public static int[] countingSort(int[] a, int k) {
        int[] count = new int[k+1];
        int[] output = new int[a.length];
        int size = a.length;
        Arrays.fill(count, 0);

        for(int i = 0; i < size; i++) {
            int j = a[i];
            count[j] = count[j] + 1;
        }

        for(int i = 1; i < k + 1; i++) {
            count[i] = count[i] + count[i - 1];
        }

        for(int i = size-1; i > -1; i--) {
            int j = a[i];
            count[j]--;
            output[count[j]] = a[i];
        }

        return output;
    }

    public static int[] mergesort(int[] a) {
        int n = a.length;
        if(n <= 1) {
            return a;
        }

        int[] left = Arrays.copyOfRange(a, 0, n/2);
        int[] right = Arrays.copyOfRange(a, n/2, n);
        
        left = mergesort(left);
        right = mergesort(right);
        return merge(left, right);
    }

    public static int[] merge(int[] a, int[] b) {
        int[] mergedArray = new int[a.length + b.length];
        int index = 0;
        int indexA = 0, indexB = 0;

        while(a[a.length-1] != -1 && b[b.length-1] != -1) {
            if(a[indexA] > b[indexB]) {
                mergedArray[index++] = b[indexB];
                b[indexB] = -1;
                indexB++;
            } else {
                mergedArray[index++] = a[indexA];
                a[indexA] = -1;
                indexA++;
            }
        }

        for(int i = a.length-1; i > -1; i--) {
            if(a[i] == -1) {break;}
            else {
                mergedArray[index++] = a[i];
                a[i] = -1;
            }
        }

        for(int i = b.length-1; i > -1; i--) {
            if(b[i] == -1) {break;}
            else {
                mergedArray[index++] = b[i];
                b[i] = -1;
            }
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        //System.out.println("hey");
        int[] subData1 = new int[512];
        int[] subData2 = new int[1024];
        int[] subData3 = new int[2048];
        int[] subData4 = new int[4096];
        int[] subData5 = new int[8192];
        int[] subData6 = new int[16384];
        int[] subData7 = new int[32768];
        int[] subData8 = new int[65536];
        int[] subData9 = new int[131072];
        int[] subData10 = new int[251281];

        String line = "";

        try { // src\\com\\assignment1\\
            BufferedReader br = new BufferedReader(new FileReader("TrafficFlowDataset.csv"));  
            String headerLine = br.readLine();
            int p = 0;
            while((line = br.readLine()) != null) {
                String[] l = line.split(",");
                subData10[p] = Integer.parseInt(l[7]);
                p++;
            }
            br.close();

            for(int i = 0; i < 131072; i++) {
                if(i < 512) {subData1[i] = subData10[i];}
                if(i < 1024) {subData2[i] = subData10[i];}
                if(i < 2048) {subData3[i] = subData10[i];}
                if(i < 4096) {subData4[i] = subData10[i];}
                if(i < 8192) {subData5[i] = subData10[i];}
                if(i < 16384) {subData6[i] = subData10[i];}
                if(i < 32768) {subData7[i] = subData10[i];}
                if(i < 65536) {subData8[i] = subData10[i];}
                if(i < 131072) {subData9[i] = subData10[i];}
            }
    
            //insertion sort random data
            //System.out.println("Insertion sort");
            double duration = 0;

            for(int i = 0; i < 10; i++) {
                int[] i1 = new int[512];
                System.arraycopy(subData1, 0, i1, 0, 512);
                double startTime = System.nanoTime();
                insertionSort(i1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion512Average = duration / 10 / 1000000;
            //System.out.println("Insertion 512: " + insertion512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i2 = new int[1024];
                System.arraycopy(subData2, 0, i2, 0, 1024);
                double startTime = System.nanoTime();
                insertionSort(i2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion1024Average = duration / 10 / 1000000;
            //System.out.println("Insertion 1024: " + insertion1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i3 = new int[2048];
                System.arraycopy(subData3, 0, i3, 0, 2048);
                double startTime = System.nanoTime();
                insertionSort(i3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion2048Average = duration / 10 / 1000000;
            //System.out.println("Insertion 2048: " + insertion2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i4 = new int[4096];
                System.arraycopy(subData4, 0, i4, 0, 4096);
                double startTime = System.nanoTime();
                insertionSort(i4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion4096Average = duration / 10 / 1000000;
            //System.out.println("Insertion 4096: " + insertion4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i5 = new int[8192];
                System.arraycopy(subData5, 0, i5, 0, 8192);
                double startTime = System.nanoTime();
                insertionSort(i5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion8192Average = duration / 10 / 1000000;
            //System.out.println("Insertion 8192: " + insertion8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i6 = new int[16384];
                System.arraycopy(subData6, 0, i6, 0, 16384);
                double startTime = System.nanoTime();
                insertionSort(i6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion16384Average = duration / 10 / 1000000;
            //System.out.println("Insertion 16384: " + insertion16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i7 = new int[32768];
                System.arraycopy(subData7, 0, i7, 0, 32768);
                double startTime = System.nanoTime();
                insertionSort(i7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion32768Average = duration / 10 / 1000000;
            //System.out.println("Insertion 32768: " + insertion32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i8 = new int[65536];
                System.arraycopy(subData8, 0, i8, 0, 65536);
                double startTime = System.nanoTime();
                insertionSort(i8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion65536Average = duration / 10 / 1000000;
            //System.out.println("Insertion 65536: " + insertion65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i9 = new int[131072];
                System.arraycopy(subData9, 0, i9, 0, 131072);
                double startTime = System.nanoTime();
                insertionSort(i9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion131072Average = duration / 10 / 1000000;
            //System.out.println("Insertion 131072: " + insertion131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i10 = new int[251281];
                System.arraycopy(subData10, 0, i10, 0, 251281);
                double startTime = System.nanoTime();
                insertionSort(i10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double insertion251281Average = duration / 10 / 1000000;
            //System.out.println("Insertion 251281: " + insertion251281Average);


            //mergesort random data
            //System.out.println("Merge sort");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m1 = new int[512];
                System.arraycopy(subData1, 0, m1, 0, 512);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge512Average = duration / 10 / 1000000;
            //System.out.println("Merge 512: " + merge512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m2 = new int[1024];
                System.arraycopy(subData2, 0, m2, 0, 1024);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge1024Average = duration / 10 / 1000000;
            //System.out.println("Merge 1024: " + merge1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m3 = new int[2048];
                System.arraycopy(subData3, 0, m3, 0, 2048);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge2048Average = duration / 10 / 1000000;
            //System.out.println("Merge 2048: " + merge2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m4 = new int[4096];
                System.arraycopy(subData4, 0, m4, 0, 4096);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge4096Average = duration / 10 / 1000000;
            //System.out.println("Merge 4096: " + merge4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m5 = new int[8192];
                System.arraycopy(subData5, 0, m5, 0, 8192);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge8192Average = duration / 10 / 1000000;
            //System.out.println("Merge 8192: " + merge8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m6 = new int[16384];
                System.arraycopy(subData6, 0, m6, 0, 16384);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge16384Average = duration / 10 / 1000000;
            //System.out.println("Merge 16384: " + merge16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m7 = new int[32768];
                System.arraycopy(subData7, 0, m7, 0, 32768);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge32768Average = duration / 10 / 1000000;
            //System.out.println("Merge 32768: " + merge32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m8 = new int[65536];
                System.arraycopy(subData8, 0, m8, 0, 65536);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge65536Average = duration / 10 / 1000000;
            //System.out.println("Merge 65536: " + merge65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m9 = new int[131072];
                System.arraycopy(subData9, 0, m9, 0, 131072);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge131072Average = duration / 10 / 1000000;
            //System.out.println("Merge 131072: " + merge131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m10 = new int[251281];
                System.arraycopy(subData10, 0, m10, 0, 251281);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double merge251281Average = duration / 10 / 1000000;
            //System.out.println("Merge 251281: " + merge251281Average);


            // pigeonhole sort random data
            //System.out.println("Pigeonhole sort");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p1 = new int[512];
                System.arraycopy(subData1, 0, p1, 0, 512);
                double startTime = System.nanoTime();
                pigeonholeSort(p1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole512Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 512: " + pigeonhole512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p2 = new int[1024];
                System.arraycopy(subData2, 0, p2, 0, 1024);
                double startTime = System.nanoTime();
                pigeonholeSort(p2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole1024Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 1024: " + pigeonhole1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p3 = new int[2048];
                System.arraycopy(subData3, 0, p3, 0, 2048);
                double startTime = System.nanoTime();
                pigeonholeSort(p3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole2048Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 2048: " + pigeonhole2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p4 = new int[4096];
                System.arraycopy(subData4, 0, p4, 0, 4096);
                double startTime = System.nanoTime();
                pigeonholeSort(p4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole4096Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 4096: " + pigeonhole4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p5 = new int[8192];
                System.arraycopy(subData5, 0, p5, 0, 8192);
                double startTime = System.nanoTime();
                pigeonholeSort(p5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole8192Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 8192: " + pigeonhole8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p6 = new int[16384];
                System.arraycopy(subData6, 0, p6, 0, 16384);
                double startTime = System.nanoTime();
                pigeonholeSort(p6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole16384Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 16384: " + pigeonhole16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p7 = new int[32768];
                System.arraycopy(subData7, 0, p7, 0, 32768);
                double startTime = System.nanoTime();
                pigeonholeSort(p7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole32768Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 32768: " + pigeonhole32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p8 = new int[65536];
                System.arraycopy(subData8, 0, p8, 0, 65536);
                double startTime = System.nanoTime();
                pigeonholeSort(p8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole65536Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 65536: " + pigeonhole65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p9 = new int[131072];
                System.arraycopy(subData9, 0, p9, 0, 131072);
                double startTime = System.nanoTime();
                pigeonholeSort(p9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole131072Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 131072: " + pigeonhole131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p10 = new int[251281];
                System.arraycopy(subData10, 0, p10, 0, 251281);
                double startTime = System.nanoTime();
                pigeonholeSort(p10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double pigeonhole251281Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 251281: " + pigeonhole251281Average);
            

            // countingsort random data

            //System.out.println("Counting sort");

            int maxNum;
            duration = 0;
            maxNum = Arrays.stream(subData1).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c1 = new int[512];
                System.arraycopy(subData1, 0, c1, 0, 512);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c1, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting512Average = duration / 10 / 1000000;
            //System.out.println("Counting 512: " + counting512Average);

            duration = 0;
            maxNum = Arrays.stream(subData2).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c2 = new int[1024];
                System.arraycopy(subData2, 0, c2, 0, 1024);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c2, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting1024Average = duration / 10 / 1000000;
            //System.out.println("Counting 1024: " + counting1024Average);

            duration = 0;
            maxNum = Arrays.stream(subData3).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c3 = new int[2048];
                System.arraycopy(subData3, 0, c3, 0, 2048);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c3, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting2048Average = duration / 10 / 1000000;
            //System.out.println("Counting 2048: " + counting2048Average);

            duration = 0;
            maxNum = Arrays.stream(subData4).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c4 = new int[4096];
                System.arraycopy(subData4, 0, c4, 0, 4096);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c4, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting4096Average = duration / 10 / 1000000;
            //System.out.println("Counting 4096: " + counting4096Average);

            duration = 0;
            maxNum = Arrays.stream(subData5).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c5 = new int[8192];
                System.arraycopy(subData5, 0, c5, 0, 8192);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c5, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting8192Average = duration / 10 / 1000000;
            //System.out.println("Counting 8192: " + counting8192Average);

            duration = 0;
            maxNum = Arrays.stream(subData6).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c6 = new int[16384];
                System.arraycopy(subData6, 0, c6, 0, 16384);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c6, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting16384Average = duration / 10 / 1000000;
            //System.out.println("Counting 16384: " + counting16384Average);

            duration = 0;
            maxNum = Arrays.stream(subData7).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c7 = new int[32768];
                System.arraycopy(subData7, 0, c7, 0, 32768);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c7, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting32768Average = duration / 10 / 1000000;
            //System.out.println("Counting 32768: " + counting32768Average);

            duration = 0;
            maxNum = Arrays.stream(subData8).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c8 = new int[65536];
                System.arraycopy(subData8, 0, c8, 0, 65536);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c8, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting65536Average = duration / 10 / 1000000;
            //System.out.println("Counting 65536: " + counting65536Average);

            duration = 0;
            maxNum = Arrays.stream(subData9).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c9 = new int[131072];
                System.arraycopy(subData9, 0, c9, 0, 131072);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c9, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting131072Average = duration / 10 / 1000000;
            //System.out.println("Counting 131072: " + counting131072Average);

            duration = 0;
            maxNum = Arrays.stream(subData10).max().getAsInt();
            for(int i = 0; i < 10; i++) {
                int[] c10 = new int[251281];
                System.arraycopy(subData10, 0, c10, 0, 251281);
                double startTime = System.nanoTime();
                int[] arr = countingSort(c10, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double counting251281Average = duration / 10 / 1000000;
            //System.out.println("Counting 251281: " + counting251281Average);


            // sorted data

            // insertion sort
            //System.out.println("Sorted Insertion");
            insertionSort(subData10);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i1 = new int[512];
                System.arraycopy(subData10, 0, i1, 0, 512);
                double startTime = System.nanoTime();
                insertionSort(i1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion512Average = duration / 10 / 1000000;
            //System.out.println("Insertion 512: " + sortedInsertion512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i2 = new int[1024];
                System.arraycopy(subData10, 0, i2, 0, 1024);
                double startTime = System.nanoTime();
                insertionSort(i2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion1024Average = duration / 10 / 1000000;
            //System.out.println("Insertion 1024: " + sortedInsertion1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i3 = new int[2048];
                System.arraycopy(subData10, 0, i3, 0, 2048);
                double startTime = System.nanoTime();
                insertionSort(i3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion2048Average = duration / 10 / 1000000;
            //System.out.println("Insertion 2048: " + sortedInsertion2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i4 = new int[4096];
                System.arraycopy(subData10, 0, i4, 0, 4096);
                double startTime = System.nanoTime();
                insertionSort(i4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion4096Average = duration / 10 / 1000000;
            //System.out.println("Insertion 4096: " + sortedInsertion4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i5 = new int[8192];
                System.arraycopy(subData10, 0, i5, 0, 8192);
                double startTime = System.nanoTime();
                insertionSort(i5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion8192Average = duration / 10 / 1000000;
            //System.out.println("Insertion 8192: " + sortedInsertion8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i6 = new int[16384];
                System.arraycopy(subData10, 0, i6, 0, 16384);
                double startTime = System.nanoTime();
                insertionSort(i6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion16384Average = duration / 10 / 1000000;
            //System.out.println("Insertion 16384: " + sortedInsertion16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i7 = new int[32768];
                System.arraycopy(subData10, 0, i7, 0, 32768);
                double startTime = System.nanoTime();
                insertionSort(i7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion32768Average = duration / 10 / 1000000;
            //System.out.println("Insertion 32768: " + sortedInsertion32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i8 = new int[65536];
                System.arraycopy(subData10, 0, i8, 0, 65536);
                double startTime = System.nanoTime();
                insertionSort(i8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion65536Average = duration / 10 / 1000000;
            //System.out.println("Insertion 65536: " + sortedInsertion65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i9 = new int[131072];
                System.arraycopy(subData10, 0, i9, 0, 131072);
                double startTime = System.nanoTime();
                insertionSort(i9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion131072Average = duration / 10 / 1000000;
            //System.out.println("Insertion 131072: " + sortedInsertion131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i10 = new int[251281];
                System.arraycopy(subData10, 0, i10, 0, 251281);
                double startTime = System.nanoTime();
                insertionSort(i10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedInsertion251281Average = duration / 10 / 1000000;
            //System.out.println("Insertion 251281: " + sortedInsertion251281Average);

            //System.out.println("Sorted Merge");

            
            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m1 = new int[512];
                System.arraycopy(subData10, 0, m1, 0, 512);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge512Average = duration / 10 / 1000000;
            //System.out.println("Merge 512: " + sortedMerge512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m2 = new int[1024];
                System.arraycopy(subData10, 0, m2, 0, 1024);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge1024Average = duration / 10 / 1000000;
            //System.out.println("Merge 1024: " + sortedMerge1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m3 = new int[2048];
                System.arraycopy(subData10, 0, m3, 0, 2048);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge2048Average = duration / 10 / 1000000;
            //System.out.println("Merge 2048: " + sortedMerge2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m4 = new int[4096];
                System.arraycopy(subData10, 0, m4, 0, 4096);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge4096Average = duration / 10 / 1000000;
            //System.out.println("Merge 4096: " + sortedMerge4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m5 = new int[8192];
                System.arraycopy(subData10, 0, m5, 0, 8192);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge8192Average = duration / 10 / 1000000;
            //System.out.println("Merge 8192: " + sortedMerge8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m6 = new int[16384];
                System.arraycopy(subData10, 0, m6, 0, 16384);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge16384Average = duration / 10 / 1000000;
            //System.out.println("Merge 16384: " + sortedMerge16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m7 = new int[32768];
                System.arraycopy(subData10, 0, m7, 0, 32768);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge32768Average = duration / 10 / 1000000;
            //System.out.println("Merge 32768: " + sortedMerge32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m8 = new int[65536];
                System.arraycopy(subData10, 0, m8, 0, 65536);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge65536Average = duration / 10 / 1000000;
            //System.out.println("Merge 65536: " + sortedMerge65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m9 = new int[131072];
                System.arraycopy(subData10, 0, m9, 0, 131072);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge131072Average = duration / 10 / 1000000;
            //System.out.println("Merge 131072: " + sortedMerge131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m10 = new int[251281];
                System.arraycopy(subData10, 0, m10, 0, 251281);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedMerge251281Average = duration / 10 / 1000000;
            //System.out.println("Merge 251281: " + sortedMerge251281Average);

            //System.out.println("Sorted Pigeonhole");

            for(int i = 0; i < 10; i++) {
                int[] p1 = new int[512];
                System.arraycopy(subData10, 0, p1, 0, 512);
                double startTime = System.nanoTime();
                pigeonholeSort(p1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole512Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 512: " + sortedPigeonhole512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p2 = new int[1024];
                System.arraycopy(subData10, 0, p2, 0, 1024);
                double startTime = System.nanoTime();
                pigeonholeSort(p2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole1024Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 1024: " + sortedPigeonhole1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p3 = new int[2048];
                System.arraycopy(subData10, 0, p3, 0, 2048);
                double startTime = System.nanoTime();
                pigeonholeSort(p3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole2048Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 2048: " + sortedPigeonhole2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p4 = new int[4096];
                System.arraycopy(subData10, 0, p4, 0, 4096);
                double startTime = System.nanoTime();
                pigeonholeSort(p4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole4096Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 4096: " + sortedPigeonhole4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p5 = new int[8192];
                System.arraycopy(subData10, 0, p5, 0, 8192);
                double startTime = System.nanoTime();
                pigeonholeSort(p5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole8192Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 8192: " + sortedPigeonhole8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p6 = new int[16384];
                System.arraycopy(subData10, 0, p6, 0, 16384);
                double startTime = System.nanoTime();
                pigeonholeSort(p6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole16384Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 16384: " + sortedPigeonhole16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p7 = new int[32768];
                System.arraycopy(subData10, 0, p7, 0, 32768);
                double startTime = System.nanoTime();
                pigeonholeSort(p7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole32768Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 32768: " + sortedPigeonhole32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p8 = new int[65536];
                System.arraycopy(subData10, 0, p8, 0, 65536);
                double startTime = System.nanoTime();
                pigeonholeSort(p8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole65536Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 65536: " + sortedPigeonhole65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p9 = new int[131072];
                System.arraycopy(subData10, 0, p9, 0, 131072);
                double startTime = System.nanoTime();
                pigeonholeSort(p9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole131072Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 131072: " + sortedPigeonhole131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p10 = new int[251281];
                System.arraycopy(subData10, 0, p10, 0, 251281);
                double startTime = System.nanoTime();
                pigeonholeSort(p10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedPigeonhole251281Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 251281: " + sortedPigeonhole251281Average);


            //System.out.println("Sorted Counting");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c1 = new int[512];
                System.arraycopy(subData10, 0, c1, 0, 512);
                maxNum = c1[511];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c1, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting512Average = duration / 10 / 1000000;
            //System.out.println("Counting 512: " + sortedCounting512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c2 = new int[1024];
                System.arraycopy(subData10, 0, c2, 0, 1024);
                maxNum = c2[1023];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c2, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting1024Average = duration / 10 / 1000000;
            //System.out.println("Counting 1024: " + sortedCounting1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c3 = new int[2048];
                System.arraycopy(subData10, 0, c3, 0, 2048);
                maxNum = c3[2047];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c3, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting2048Average = duration / 10 / 1000000;
            //System.out.println("Counting 2048: " + sortedCounting2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c4 = new int[4096];
                System.arraycopy(subData10, 0, c4, 0, 4096);
                maxNum = c4[4095];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c4, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting4096Average = duration / 10 / 1000000;
            //System.out.println("Counting 4096: " + sortedCounting4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c5 = new int[8192];
                System.arraycopy(subData10, 0, c5, 0, 8192);
                maxNum = c5[8191];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c5, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting8192Average = duration / 10 / 1000000;
            //System.out.println("Counting 8192: " + sortedCounting8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c6 = new int[16384];
                System.arraycopy(subData10, 0, c6, 0, 16384);
                maxNum = c6[16383];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c6, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting16384Average = duration / 10 / 1000000;
            //System.out.println("Counting 16384: " + sortedCounting16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c7 = new int[32768];
                System.arraycopy(subData10, 0, c7, 0, 32768);
                maxNum = c7[32767];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c7, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting32768Average = duration / 10 / 1000000;
            //System.out.println("Counting 32768: " + sortedCounting32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c8 = new int[65536];
                System.arraycopy(subData10, 0, c8, 0, 65536);
                maxNum = c8[65535];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c8, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting65536Average = duration / 10 / 1000000;
            //System.out.println("Counting 65536: " + sortedCounting65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c9 = new int[131072];
                System.arraycopy(subData10, 0, c9, 0, 131072);
                maxNum = c9[131071];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c9, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting131072Average = duration / 10 / 1000000;
            //System.out.println("Counting 131072: " + sortedCounting131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c10 = new int[251281];
                System.arraycopy(subData10, 0, c10, 0, 251281);
                maxNum = c10[251280];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c10, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double sortedCounting251281Average = duration / 10 / 1000000;
            //System.out.println("Counting 251281: " + sortedCounting251281Average);


            int[] reversedData = new int[251281];

            for(int i = 0; i < 251281 ; i++) {
                reversedData[i] = subData10[251280-i];
            }

            //System.out.println("Reversed Insertion");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i1 = new int[512];
                System.arraycopy(reversedData, 0, i1, 0, 512);
                double startTime = System.nanoTime();
                insertionSort(i1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion512Average = duration / 10 / 1000000;
            //System.out.println("Insertion 512: " + reverseInsertion512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i2 = new int[1024];
                System.arraycopy(reversedData, 0, i2, 0, 1024);
                double startTime = System.nanoTime();
                insertionSort(i2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion1024Average = duration / 10 / 1000000;
            //System.out.println("Insertion 1024: " + reverseInsertion1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i3 = new int[2048];
                System.arraycopy(reversedData, 0, i3, 0, 2048);
                double startTime = System.nanoTime();
                insertionSort(i3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion2048Average = duration / 10 / 1000000;
            //System.out.println("Insertion 2048: " + reverseInsertion2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i4 = new int[4096];
                System.arraycopy(reversedData, 0, i4, 0, 4096);
                double startTime = System.nanoTime();
                insertionSort(i4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion4096Average = duration / 10 / 1000000;
            //System.out.println("Insertion 4096: " + reverseInsertion4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i5 = new int[8192];
                System.arraycopy(reversedData, 0, i5, 0, 8192);
                double  startTime = System.nanoTime();
                insertionSort(i5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion8192Average = duration / 10 / 1000000;
            //System.out.println("Insertion 8192: " + reverseInsertion8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i6 = new int[16384];
                System.arraycopy(reversedData, 0, i6, 0, 16384);
                double startTime = System.nanoTime();
                insertionSort(i6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion16384Average = duration / 10 / 1000000;
            //System.out.println("Insertion 16384: " + reverseInsertion16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i7 = new int[32768];
                System.arraycopy(reversedData, 0, i7, 0, 32768);
                double startTime = System.nanoTime();
                insertionSort(i7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion32768Average = duration / 10 / 1000000;
            //System.out.println("Insertion 32768: " + reverseInsertion32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i8 = new int[65536];
                System.arraycopy(reversedData, 0, i8, 0, 65536);
                double startTime = System.nanoTime();
                insertionSort(i8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion65536Average = duration / 10 / 1000000;
            //System.out.println("Insertion 65536: " + reverseInsertion65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i9 = new int[131072];
                System.arraycopy(reversedData, 0, i9, 0, 131072);
                double startTime = System.nanoTime();
                insertionSort(i9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion131072Average = duration / 10 / 1000000;
            //System.out.println("Insertion 131072: " + reverseInsertion131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] i10 = new int[251281];
                System.arraycopy(reversedData, 0, i10, 0, 251281);
                double startTime = System.nanoTime();
                insertionSort(i10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseInsertion251281Average = duration / 10 / 1000000;
            //System.out.println("Insertion 251281: " + reverseInsertion251281Average);


            //System.out.println("Reverse Merge");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m1 = new int[512];
                System.arraycopy(reversedData, 0, m1, 0, 512);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge512Average = duration / 10 / 1000000;
            //System.out.println("Merge 512: " + reverseMerge512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m2 = new int[1024];
                System.arraycopy(reversedData, 0, m2, 0, 1024);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge1024Average = duration / 10 / 1000000;
            //System.out.println("Merge 1024: " + reverseMerge1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m3 = new int[2048];
                System.arraycopy(reversedData, 0, m3, 0, 2048);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge2048Average = duration / 10 / 1000000;
            //System.out.println("Merge 2048: " + reverseMerge2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m4 = new int[4096];
                System.arraycopy(reversedData, 0, m4, 0, 4096);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge4096Average = duration / 10 / 1000000;
            //System.out.println("Merge 4096: " + reverseMerge4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m5 = new int[8192];
                System.arraycopy(reversedData, 0, m5, 0, 8192);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge8192Average = duration / 10 / 1000000;
            //System.out.println("Merge 8192: " + reverseMerge8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m6 = new int[16384];
                System.arraycopy(reversedData, 0, m6, 0, 16384);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge16384Average = duration / 10 / 1000000;
            //System.out.println("Merge 16384: " + reverseMerge16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m7 = new int[32768];
                System.arraycopy(reversedData, 0, m7, 0, 32768);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge32768Average = duration / 10 / 1000000;
            //System.out.println("Merge 32768: " + reverseMerge32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m8 = new int[65536];
                System.arraycopy(reversedData, 0, m8, 0, 65536);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge65536Average = duration / 10 / 1000000;
            //System.out.println("Merge 65536: " + reverseMerge65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m9 = new int[131072];
                System.arraycopy(reversedData, 0, m9, 0, 131072);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge131072Average = duration / 10 / 1000000;
            //System.out.println("Merge 131072: " + reverseMerge131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] m10 = new int[251281];
                System.arraycopy(reversedData, 0, m10, 0, 251281);
                double startTime = System.nanoTime();
                int[] arr = mergesort(m10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseMerge251281Average = duration / 10 / 1000000;
            //System.out.println("Merge 251281: " + reverseMerge251281Average);

            //System.out.println("Reverse Pigeonhole");

            for(int i = 0; i < 10; i++) {
                int[] p1 = new int[512];
                System.arraycopy(reversedData, 0, p1, 0, 512);
                double startTime = System.nanoTime();
                pigeonholeSort(p1);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole512Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 512: " + reversePigeonhole512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p2 = new int[1024];
                System.arraycopy(reversedData, 0, p2, 0, 1024);
                double startTime = System.nanoTime();
                pigeonholeSort(p2);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole1024Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 1024: " + reversePigeonhole1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p3 = new int[2048];
                System.arraycopy(reversedData, 0, p3, 0, 2048);
                double startTime = System.nanoTime();
                pigeonholeSort(p3);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole2048Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 2048: " + reversePigeonhole2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p4 = new int[4096];
                System.arraycopy(reversedData, 0, p4, 0, 4096);
                double startTime = System.nanoTime();
                pigeonholeSort(p4);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole4096Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 4096: " + reversePigeonhole4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p5 = new int[8192];
                System.arraycopy(reversedData, 0, p5, 0, 8192);
                double startTime = System.nanoTime();
                pigeonholeSort(p5);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole8192Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 8192: " + reversePigeonhole8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p6 = new int[16384];
                System.arraycopy(reversedData, 0, p6, 0, 16384);
                double startTime = System.nanoTime();
                pigeonholeSort(p6);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole16384Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 16384: " + reversePigeonhole16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p7 = new int[32768];
                System.arraycopy(reversedData, 0, p7, 0, 32768);
                double startTime = System.nanoTime();
                pigeonholeSort(p7);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole32768Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 32768: " + reversePigeonhole32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p8 = new int[65536];
                System.arraycopy(reversedData, 0, p8, 0, 65536);
                double startTime = System.nanoTime();
                pigeonholeSort(p8);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole65536Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 65536: " + reversePigeonhole65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p9 = new int[131072];
                System.arraycopy(reversedData, 0, p9, 0, 131072);
                double startTime = System.nanoTime();
                pigeonholeSort(p9);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole131072Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 131072: " + reversePigeonhole131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] p10 = new int[251281];
                System.arraycopy(reversedData, 0, p10, 0, 251281);
                double startTime = System.nanoTime();
                pigeonholeSort(p10);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reversePigeonhole251281Average = duration / 10 / 1000000;
            //System.out.println("Pigeonhole 251281: " + reversePigeonhole251281Average);


            //System.out.println("Reverse Counting");

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c1 = new int[512];
                System.arraycopy(reversedData, 0, c1, 0, 512);
                maxNum = c1[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c1, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting512Average = duration / 10 / 1000000;
            //System.out.println("Counting 512: " + reverseCounting512Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c2 = new int[1024];
                System.arraycopy(reversedData, 0, c2, 0, 1024);
                maxNum = c2[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c2, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting1024Average = duration / 10 / 1000000;
            //System.out.println("Counting 1024: " + reverseCounting1024Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c3 = new int[2048];
                System.arraycopy(reversedData, 0, c3, 0, 2048);
                maxNum = c3[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c3, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting2048Average = duration / 10 / 1000000;
            //System.out.println("Counting 2048: " + reverseCounting2048Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c4 = new int[4096];
                System.arraycopy(reversedData, 0, c4, 0, 4096);
                maxNum = c4[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c4, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting4096Average = duration / 10 / 1000000;
            //System.out.println("Counting 4096: " + reverseCounting4096Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c5 = new int[8192];
                System.arraycopy(reversedData, 0, c5, 0, 8192);
                maxNum = c5[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c5, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting8192Average = duration / 10 / 1000000;
            //System.out.println("Counting 8192: " + reverseCounting8192Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c6 = new int[16384];
                System.arraycopy(reversedData, 0, c6, 0, 16384);
                maxNum = c6[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c6, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting16384Average = duration / 10 / 1000000;
            //System.out.println("Counting 16384: " + reverseCounting16384Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c7 = new int[32768];
                System.arraycopy(reversedData, 0, c7, 0, 32768);
                maxNum = c7[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c7, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting32768Average = duration / 10 / 1000000;
            //System.out.println("Counting 32768: " + reverseCounting32768Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c8 = new int[65536];
                System.arraycopy(reversedData, 0, c8, 0, 65536);
                maxNum = c8[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c8, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting65536Average = duration / 10 / 1000000;
            //System.out.println("Counting 65536: " + reverseCounting65536Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c9 = new int[131072];
                System.arraycopy(reversedData, 0, c9, 0, 131072);
                maxNum = c9[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c9, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting131072Average = duration / 10 / 1000000;
            //System.out.println("Counting 131072: " + reverseCounting131072Average);

            duration = 0;
            for(int i = 0; i < 10; i++) {
                int[] c10 = new int[251281];
                System.arraycopy(reversedData, 0, c10, 0, 251281);
                maxNum = c10[0];
                double startTime = System.nanoTime();
                int[] arr = countingSort(c10, maxNum);
                double endTime = System.nanoTime();
                duration += (endTime - startTime);
            }
            double reverseCounting251281Average = duration / 10 / 1000000;
            //System.out.println("Counting 251281: " + reverseCounting251281Average);

            double[] randomInsertion = {insertion512Average, insertion1024Average, insertion2048Average, insertion4096Average, insertion8192Average,
                                        insertion16384Average, insertion32768Average, insertion65536Average, insertion131072Average, insertion251281Average};
            double[] randomMerge = {merge512Average, merge1024Average, merge2048Average, merge4096Average, merge8192Average, 
                                    merge16384Average,merge32768Average, merge65536Average, merge131072Average, merge251281Average};
            double[] randomPigeonhole = {pigeonhole512Average ,pigeonhole1024Average, pigeonhole2048Average, pigeonhole4096Average, pigeonhole8192Average,
                                        pigeonhole16384Average, pigeonhole32768Average, pigeonhole65536Average, pigeonhole131072Average, pigeonhole251281Average};
            double[] randomCounting = {counting512Average, counting1024Average, counting2048Average, counting4096Average, counting8192Average, 
                                    counting16384Average, counting32768Average, counting65536Average, counting131072Average, counting251281Average};

            double[] sortedInsertion = {sortedInsertion512Average, sortedInsertion1024Average, sortedInsertion2048Average, sortedInsertion4096Average, sortedInsertion8192Average,
                                        sortedInsertion16384Average, sortedInsertion32768Average, sortedInsertion65536Average, sortedInsertion131072Average, sortedInsertion251281Average};
            double[] sortedMerge = {sortedMerge512Average, sortedMerge1024Average, sortedMerge2048Average, sortedMerge4096Average, sortedMerge8192Average,
                                    sortedMerge16384Average, sortedMerge32768Average, sortedMerge65536Average, sortedMerge131072Average, sortedMerge251281Average};
            double[] sortedPigeonhole = {sortedPigeonhole512Average, sortedPigeonhole1024Average, sortedPigeonhole2048Average, sortedPigeonhole4096Average, sortedPigeonhole8192Average,
                                        sortedPigeonhole16384Average, sortedPigeonhole32768Average, sortedPigeonhole65536Average, sortedPigeonhole131072Average, sortedPigeonhole251281Average};
            double[] sortedCounting = {sortedCounting512Average, sortedCounting1024Average, sortedCounting2048Average, sortedCounting4096Average, sortedCounting8192Average,
                                        sortedCounting16384Average, sortedCounting32768Average, sortedCounting65536Average, sortedCounting131072Average, sortedCounting251281Average};
 
            double[] reverseInsertion = {reverseInsertion512Average, reverseInsertion1024Average, reverseInsertion2048Average, reverseInsertion4096Average, reverseInsertion8192Average,
                                        reverseInsertion16384Average, reverseInsertion32768Average, reverseInsertion65536Average, reverseInsertion131072Average, reverseInsertion251281Average};
            double[] reverseMerge = {reverseMerge512Average, reverseMerge1024Average, reverseMerge2048Average, reverseMerge4096Average, reverseMerge8192Average, 
                                    reverseMerge16384Average, reverseMerge32768Average, reverseMerge65536Average, reverseMerge131072Average, reverseMerge251281Average};
            double[] reversePigeonhole = {reversePigeonhole512Average, reversePigeonhole1024Average, reversePigeonhole2048Average, reversePigeonhole4096Average, reversePigeonhole8192Average, 
                                        reversePigeonhole16384Average, reversePigeonhole32768Average, reversePigeonhole65536Average, reversePigeonhole131072Average, reversePigeonhole251281Average};
            double[] reverseCounting = {reverseCounting512Average, reverseCounting1024Average, reverseCounting2048Average, reverseCounting4096Average, reverseCounting8192Average, 
                                        reverseCounting16384Average, reverseCounting32768Average, reverseCounting65536Average, reverseCounting131072Average, reverseCounting251281Average};

            int[] dataSize = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};  
            //System.out.println(sortedPigeonhole512Average);                       

            showAndSaveChart("Random Data", dataSize, randomInsertion, randomMerge, randomPigeonhole, randomCounting);
            showAndSaveChart("Sorted Data", dataSize, sortedInsertion, sortedMerge, sortedPigeonhole, sortedCounting);
            showAndSaveChart("Reverse Data", dataSize, reverseInsertion, reverseMerge, reversePigeonhole, reverseCounting);


        } catch(IOException e) {System.out.println(e);}
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[] yAxisInsertion, double[] yAxisMerge, double[] yAxisPigeonhole, double[] yAxisCounting) throws IOException {
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title).yAxisTitle("Time in Milliseconds")
                            .xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("Insertion Sort", doubleX, yAxisInsertion);
        chart.addSeries("Merge Sort", doubleX, yAxisMerge);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxisPigeonhole);
        chart.addSeries("Counting Sort", doubleX, yAxisCounting);

        BitmapEncoder.saveBitmap(chart, title + "3" + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();
    }
}
