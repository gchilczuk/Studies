import java.util.Random;

/**
 * @author Grzegorz Chilczuk
 *         Created on 08 kwi 2016
 */
public class Sort {

    public void insertSort(Integer[] array){
        Integer isnert;
        for(int before = array.length - 2; before >= 0; before--){
            isnert = array[before];
            int i = before + 1;
            while (i < array.length && isnert > array[i]){
                array[i-1] = array[i];
                i++;
            }
            array[i-1] = isnert;
        }        
    }

    public void selectSort(Integer[] array){
        int min;
        for(int i = 0; i < array.length; i++){
            min = min(array, i);
            swap(array, min, i);
        }
    }

    public int min(Integer[] array, int start){
        int min = start;
        for (int i = start+1; i < array.length; i++){
            if (array[i] < array[min])
                min = i;
        }
        return min;
    }

    public void bubbleSort(Integer[] array){
        int lastSwap = array.length - 1;
        while(lastSwap > 0) {
            Integer end = lastSwap;
            lastSwap = 0;
            for (Integer left = 0; left < end; ++left) {
                if (array[left] > array[left + 1]) {
                    Integer temp = array[left];
                    while (left < end && temp > array[left + 1]) {
                        array[left] = array[left + 1];
                        left++;
                    }
                    lastSwap = left;
                    array[left] = temp;
                }
            }
        }
    }

    public void quickSort(Integer[] array){
        qSort(array, 0, array.length-1);
    }

    private void qSort(Integer[] array, int left, int right){
        if (left < right){
            int part = qsortPartition(array, left, right);
            if(part - left > right - part) {
                qSort(array, left, part-1);
                qSort(array, part + 1, right);
            }else {
                qSort(array, part + 1, right);
                qSort(array, left, part-1);
            }
        }
    }

    private int qsortPartition(Integer[] array, int left, int right){
        //int pivot =
        int i = left;
        boolean w = true;
        while(left <= right){
            if (w){
                if(array[right] < array[i]){
                    swap(array, i, right);
                    i = right;
                    w = false;
                }
                right--;
            }else {
                if (array[left] > array[i]){
                    swap(array, i, left);
                    i = left;
                    w = true;
                }
                left++;
            }
        }
        return i;
    }

    public Integer[] mergeSort(Integer[] array){
        if(array.length == 1) return array;
        Integer[] left = new Integer[array.length/2];
        int i;
        for (i = 0; i < left.length; i++)
            left[i] = array[i];
        Integer[] right = new Integer[array.length - i];
        for (int j = 0; j < right.length; j++)
            right[j] = array[i++];
        return merge(mergeSort(left), mergeSort(right));
    }

    public Integer[] merge(Integer[] left, Integer[] right){
        Integer[] sum = new Integer[left.length + right.length];
        int l = 0;
        int r = 0;
        for (int i = 0; i < sum.length; i++){
            if (l < left.length && r < right.length){
                if (left[l] <= right[r]){
                    sum[i] = left[l];
                    l++;
                }else{
                    sum[i] = right[r];
                    r++;
                }
            }else if (l >= left.length)
                sum[i] = right[r++];
            else sum[i] = left[l++];
        }
        return sum;
    }

    private void swap(Integer[] array, int l, int r){
        Integer tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;
    }


    public Integer[] sortDSC(Integer[] tab){
        if(tab.length == 1) return tab;
        Integer[] left = new Integer[tab.length/2];
        int i;
        for (i = 0; i < left.length; i++)
            left[i] = tab[i];
        Integer[] right = new Integer[tab.length - i];
        for (int j = 0; j < right.length; j++)
            right[j] = tab[i++];
        return mergeDSC(sortDSC(left), sortDSC(right));
    }

    public Integer[] mergeDSC(Integer[] left, Integer[] right){
        Integer[] sum = new Integer[left.length + right.length];
        int l = 0;
        int r = 0;
        for (int i = 0; i < sum.length; i++){
            if (l < left.length && r < right.length){
                if (left[l] >= right[r]){
                    sum[i] = left[l];
                    l++;
                }else{
                    sum[i] = right[r];
                    r++;
                }
            }else if (l == left.length)
                sum[i] = right[r++];
            else sum[i] = left[l++];
        }
        return sum;
    }

    public void heapSort(Integer[] array){
        buildHeap(array);
        for (int i = array.length-1; i > 0; i--){
            swap(array, 0, i); // korzeń na koniec
            int j = 0; // przodek
            int k; // lewy potomek
            for(k = 1; k < i; k = j*2){
                int m; // większy z potomków
                if (k+1 < i && array[k+1] > array[k]) m = k+1;
                else m = k;
                if (array[m] < array[j]) break; // potomek < przodek więc jest porządek
                swap(array, m, j);
                j = m; // przodek spada w dół
            }
        }

    }

    private void buildHeap(Integer[] array){
        int n = array.length;
        for (int i = 1; i < n; i++){
            int j = i; // nowy liść
            int k = (j-1) / 2; // przodek
            Integer x = array[i]; // wstawiany element

            while (k >= 0 && array[k] < x){ // nie dotarliśmy do korzenia i przodek jest młodszy
                array[j] = array[k]; // przodek spada w dół
                j = k; // liść idzie na górę
                if(k == 0) break;
                k = j / 2; // następny przodek liścia
            }
            array[j] = x; // mocujemy liść
        }
    }

    public static void qSorty(Integer[] array, int lewy, int prawy) {
        int i, j, piwot;

        i = (lewy+prawy)/2;
        piwot = array[i];
        array[i] = array[prawy];

        for (j = i = lewy; i < prawy; i++)
            if (array[i] < piwot) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                j++;
            }
        array[prawy] = array[j];
        array[j] = piwot;
        if (lewy < j-1) qSorty(array, lewy, j-1);
        if (j+1 < prawy) qSorty(array, j+1, prawy);

    }




}
