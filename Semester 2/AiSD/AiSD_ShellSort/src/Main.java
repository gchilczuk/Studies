import java.util.Random;

/**
 * @author Grzegorz Chilczuk
 *         Created on 17 kwi 2016
 */
public class Main {
    public static Integer[] gen(Integer x){
        Integer[] ret = new Integer[x];
        Random random = new Random();
        for (--x; x-5 >= -5; --x)
            ret[x] = random.nextInt(100);
        return ret;
    }

    public static void intTabToString(int[] tab) {
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        for (Integer x : tab)
            buffer.append(x).append(", ");
        buffer.setLength(buffer.length() - 2);
        buffer.append(']');
        System.out.println(buffer.toString());
    }

    public static void main(String[] args) {
        long start, stop;
        ShellSort sort = new ShellSort();

        sort.test(5000);

        int ilosc = 5000;
        while (ilosc <= 100000) {
            Integer[] d = gen(ilosc);

            System.out.println("Ilosc liczb: "+ilosc);

            System.out.println("############## Wersja 1 ##############");
            Integer[] wersja1a = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv1(wersja1a, 'a');
            stop = System.currentTimeMillis();
            System.out.println("a: "+ executionTime(start, stop));

            Integer[] wersja1b = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv1(wersja1b, 'b');
            stop = System.currentTimeMillis();
            System.out.println("b: "+ executionTime(start, stop));

            Integer[] wersja1c = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv1(wersja1c, 'c');
            stop = System.currentTimeMillis();
            System.out.println("c: "+ executionTime(start, stop));

            Integer[] wersja1d = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv1(wersja1d, 'd');
            stop = System.currentTimeMillis();
            System.out.println("d: "+ executionTime(start, stop));


            System.out.println("############## Wersja 2 ##############");
            Integer[] wersja2a = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv2(wersja2a, 'a');
            stop = System.currentTimeMillis();
            System.out.println("a: "+ executionTime(start, stop));

            Integer[] wersja2b = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv2(wersja2b, 'b');
            stop = System.currentTimeMillis();
            System.out.println("b: "+ executionTime(start, stop));

            Integer[] wersja2c = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv2(wersja2c, 'c');
            stop = System.currentTimeMillis();
            System.out.println("c: "+ executionTime(start, stop));

            Integer[] wersja2d = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv2(wersja2d, 'd');
            stop = System.currentTimeMillis();
            System.out.println("d: "+ executionTime(start, stop));


            System.out.println("############## Wersja 3 ##############");
            Integer[] wersja3a = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv3(wersja3a, 'a');
            stop = System.currentTimeMillis();
            System.out.println("a: "+ executionTime(start, stop));

            Integer[] wersja3b = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv3(wersja3b, 'b');
            stop = System.currentTimeMillis();
            System.out.println("b: "+ executionTime(start, stop));

            Integer[] wersja3c = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv3(wersja3c, 'c');
            stop = System.currentTimeMillis();
            System.out.println("c: "+ executionTime(start, stop));

            Integer[] wersja3d = d.clone();
            start = System.currentTimeMillis();
            sort.Shellv3(wersja3d, 'd');
            stop = System.currentTimeMillis();
            System.out.println("d: "+ executionTime(start, stop));

            System.out.println();

            switch (ilosc) {
                case 5000: ilosc = 10000; break;
                case 10000: ilosc = 50000; break;
                case 50000: ilosc = 100000; break;
                case 100000: ilosc = 100001; break;
                default: ilosc = 100001;
            }
        }
    }

    private static void printArray(Integer[] array) {
        for(int i: array) {
            System.out.println(i);
        }
    }

    private static String executionTime(long start, long stop) {
        return ""+(stop-start);
    }

    }

