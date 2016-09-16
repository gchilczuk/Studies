import java.util.ArrayList;

/**
 * @author Grzegorz Chilczuk
 *         Created on 17 kwi 2016
 */
public class ShellSort {

    public void Shellv1(Integer[] tab, char var){
        int len, hindex, h, j, i, isnert, x;
        len = tab.length;
        ArrayList<Integer> hset = chooseSet(var, len);

        hindex = hset.size()-1;
        h = hset.get(hindex);
        while (hindex != -1){
            for (j = len - h - 1; j >= 0; j--){
                isnert = tab[j];
                i = j+h;
                while((i < len) && isnert > tab[i]){
                    tab[i - h] = tab[i];
                    i += h;
                }

                tab[i - h] = isnert;
            }
            x = hindex == 0 ? --hindex+1 : --hindex;
            h = hset.get(x);
        }
    }

    public void Shellv2(Integer[] tab, char var){
        int len, hindex, h, j, i, isnert, x,y;
        len = tab.length;
        ArrayList<Integer> hset = chooseSet(var, len);

        hindex = hset.size()-1;
        h = hset.get(hindex);
        while (hindex > 0){
            for (j = len - h - 1; j >= 0; j--){
                isnert = tab[j];
                for (i = j + h; i < len && isnert > tab[i]; i += h)
                    tab[i - h] = tab[i];
                tab[i - h] = isnert;
            }
            h = hset.get(--hindex);
        }

        for (x = 0; x < len-1; x++)
            for (y = 0; y < len-1; y++)
                if (tab[y] > tab[y+1])
                    swap(tab, y, y+1);
    }

    public void Shellv3(Integer[] tab, char var){
        int len, hindex, h, j, i, isnert, x;
        len = tab.length;
        ArrayList<Integer> hset = chooseSet(var, len);

        hindex = hset.size()-1;
        h = hset.get(hindex);
        while (hindex > 0){
            for (i = 0; i+h < len-1; i += h)
                for (j = 0; j+h <= len-1; j += h)
                    if (tab[j] > tab[j+h])
                        swap(tab, j, j+h);
            h = hset.get( --hindex);
        }

        for(int before = tab.length - 2; before >= 0; before--){
            isnert = tab[before];
            x = before + 1;
            while (x < tab.length && isnert > tab[x]){
                tab[x-1] = tab[x];
                x++;
            }
            tab[x-1] = isnert;
        }
    }

    private ArrayList<Integer> chooseSet(char var, Integer len){
        ArrayList<Integer> hset;
        switch (var){
            case 'a':
                hset = a(len);
                break;
            case 'b':
                hset = b(len);
                break;
            case 'c':
                hset = c(len);
                break;
            case 'f': case'd':
                hset = fib(len);
                break;
            default:
                hset = new ArrayList<>();
                hset.add(1);
        }
        return hset;
    }

    private void swap (Integer[] tab, int a, int b){
        Integer tmp = tab[a];
        tab[a] = tab[b];
        tab[b] = tmp;
    }

    private ArrayList<Integer> a (int len){
        ArrayList<Integer> ret = new ArrayList<>();
        Integer hi = 1;
        while (hi < len){
            ret.add(hi);
            hi = 3 * hi + 1;
        }
        return ret;
    }

    private ArrayList<Integer> b (int len){
        ArrayList<Integer> ret = new ArrayList<>();
        Integer hi = 1;
        int k = 2;
        while (hi < len){
            ret.add(hi);
            hi = (int)Math.pow(2, k++) - 1;
        }
        return ret;
    }

    private ArrayList<Integer> c (int len){
        ArrayList<Integer> ret = new ArrayList<>();
        Integer hi = 1;
        int k = 1;
        while (hi < len){
            ret.add(hi);
            hi = (int)Math.pow(2, k++) + 1;
        }
        return ret;
    }

    private ArrayList<Integer> fib (int len){
        ArrayList<Integer> ret = new ArrayList<>();
        Integer b = 1;
        Integer hi = 1;
        while (hi <= len){
            ret.add(hi);
            hi = hi + b;
            b = hi - b;
        }
        return ret;
    }

    public void test(int a){
        System.out.println(a(a));
        System.out.println(b(a));
        System.out.println(c(a));
        System.out.println(fib(a));
    }


}
