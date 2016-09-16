import java.util.Random;

/**
 * @author Grzegorz Chilczuk
 *         Created on 08 kwi 2016
 */
public class Main {
    public static Integer[] gen(Integer x){
        Integer[] ret = new Integer[x];
        Random random = new Random();
        for (--x; x-5 >= -5; --x)
            ret[x] = random.nextInt(100);
        return ret;
    }

    public static String intTabToString(Integer[] tab) {
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        for (Integer x : tab)
            buffer.append(x).append(", ");
        buffer.setLength(buffer.length() - 2);
        buffer.append(']');
        return buffer.toString();
    }

    public static void main(String[] args) {
        long timeStart, timeEnd;
        Sort sort = new Sort();

        System.out.println("————————————————————————————————————————————");
        System.out.println("Generuję tablicę");
        timeStart = System.currentTimeMillis();
        int ile = 20000;
        Integer[] random = gen(ile);
        Integer[][] rand = new Integer[6][ile];
        for (int v = 0; v < 6; v++)
            rand[v] = random.clone();

        timeEnd = System.currentTimeMillis();
        System.out.println("Generowanie tablicy "+ile+" losowych wartości zajęło: "+(timeEnd-timeStart)+" milisekund");


        System.out.println("————————————————————————————————————————————");
        System.out.println("Sortuję DSC");
        timeStart = System.currentTimeMillis();
        Integer[] dscc = sort.sortDSC(random);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie DSC zajęło: "+(timeEnd-timeStart)+" milisekund");
        Integer[] ascc = sort.mergeSort(random);

        Integer[][] dsc = new Integer[6][ile];
        for (int v = 0; v < 6; v++)
            dsc[v] = dscc.clone();

        Integer[][] asc = new Integer[6][ile];
        for (int v = 0; v < 6; v++)
            asc[v] = ascc.clone();


//        ————————————————————————————————————————————————————————————————— Insert

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Insert Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        sort.insertSort(rand[0]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Insert Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.insertSort(asc[0]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Insert Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.insertSort(dsc[0]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Insert Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

//        ————————————————————————————————————————————— BUBBLE

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Bubble Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        sort.bubbleSort(rand[1]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Bubble Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.bubbleSort(asc[1]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Bubble Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.bubbleSort(dsc[1]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Bubble Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

//        ———————————————————————————————————————————————————————————— Select

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Select Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        sort.selectSort(rand[2]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Select Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.selectSort(asc[2]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Select Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.selectSort(dsc[2]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Select Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

//        ————————————————————————————————————————————————————————————————— Quick

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Quick Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        sort.quickSort(rand[3]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Quick Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.quickSort(asc[3]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Quick Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.quickSort(dsc[3]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Quick Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");


//      ————————————————————————————————————————————————————————————————— Merge

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Merge Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        rand[4] = sort.mergeSort(rand[4]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Merge Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.mergeSort(asc[4]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Merge Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.mergeSort(dsc[4]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Merge Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");
        
//        ————————————————————————————————————————————————————————————————— Heap

        System.out.println("————————————————————————————————————————————");
        System.out.println("——————————————— Heap Sort ———————————————");
        System.out.println("————————————————————————————————————————————");
        timeStart = System.currentTimeMillis();
        sort.heapSort(rand[5]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości losowych metodą Heap Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.heapSort(asc[5]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości już posortowanych metodą Heap Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

        timeStart = System.currentTimeMillis();
        sort.heapSort(dsc[5]);
        timeEnd = System.currentTimeMillis();
        System.out.println("Sortowanie wartości posortowanych odwrotnie metodą Heap Sort zajęło: "
                +(timeEnd-timeStart)+" milisekund");

    }
}
