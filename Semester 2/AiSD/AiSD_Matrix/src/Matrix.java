import java.security.InvalidParameterException;

/**
 * @author Grzegorz Chilczuk
 *         Created on 29 mar 2016
 */
public class Matrix{
    private int _size = 0;
    private Lelement _first = null;

    public Matrix(int size){
        if (size < 1) throw new InvalidParameterException("Size isn't correct");
        _first = new Lelement(0,0,null);
        _size = size;
        Lelement current = _first;
        for (int c = 1; c <= _size; c++){
            current.setNextCol(new Lelement(0, c, null));
            current = current.getNextCol();
        }
        current = _first;
        for (int r = 1; r <= _size; r++){
            current.setNextRow(new Lelement(r, 0, null));
            current = current.getNextRow();
        }
    }

    public Matrix (int[][] mat){
        _size = mat.length;
        _first = new Lelement(0,0,null);
        Lelement current = _first;
        for (int c = 1; c <= _size; c++){
            current.setNextCol(new Lelement(0, c, null));
            current = current.getNextCol();
        }
        current = _first;
        for (int r = 1; r <= _size; r++){
            current.setNextRow(new Lelement(r, 0, null));
            current = current.getNextRow();
        }

        for (int r = 0; r < _size; r++){
            for (int c = 0; c < _size; c++){
                int val = mat[r][c];
                if (val != 0)
                    this.set(r+1,c+1,val);
            }
        }
    }

    public Integer set(int r, int c, Integer value) {
        if (r < 1 || c < 1 ||r > _size || c > _size) throw new IndexOutOfBoundsException("Out of bounds");
        Lelement newLelement = new Lelement(r, c, value);
        Integer oldValue = 0;

        Lelement current = _first;
        //idę do odpowiedniej kolumny w wierszu 0
        while (current.getCol() != c)
            current = current.getNextCol();

        //idę do odpowiedniego rzędu w znalezionej kolumnie
        while (current.getNextRow() != null && current.getNextRow().getRow() <= r) {
            if (value == 0 && current.getNextRow().getRow() == r)
                break;
            current = current.getNextRow();
        }

        if(value == 0){
            if (current.getNextRow() != null && current.getNextRow().getRow() == r) {
                oldValue = current.getNextRow().getValue();
                current.setNextRow(current.getNextRow().getNextRow());
            }}
        else if (current.getRow() < r){
            newLelement.setNextRow(current.getNextRow());
            current.setNextRow(newLelement);
        }
        else if (current.getRow() == r) {
            oldValue = current.getValue();
            current.setValue(value);
            return oldValue;
        }else System.out.println("—————————————————————————————————— cur > r");

        current = _first;
        // idę do odpowiedniego rzedu w kolumnie 0
        while (current.getRow() != r)
            current = current.getNextRow();

        //idę do odpowiedniej kolumny w znalezionym rzędzie
        while (current.getNextCol() != null && current.getNextCol().getCol() <= c) {
            if (value == 0 && current.getNextCol().getCol() == c)
                break;
            current = current.getNextCol();
        }

        if (value == 0)
            if (current.getNextCol() != null && current.getNextCol().getCol() == c)
                current.setNextCol(current.getNextCol().getNextCol());
            else if (current.getCol() < c) {
                newLelement.setNextCol(current.getNextCol());
                current.setNextCol(newLelement);
            }else System.out.println("—————————————————————————————————— cur > c");

        return oldValue;
    }

//    public void insert(int r, int c, Integer val) throws IndexOutOfBoundsException {}

    public Integer get(int r, int c) {
        Integer value = 0;

        Lelement current = _first;
        //idę do odpowiedniej kolumny w wierszu 0
        while (current.getCol() != c)
            current = current.getNextCol();

        //idę do odpowiedniego rzędu w znalezionej kolumnie
        while (current.getNextRow() != null && current.getNextRow().getRow() <= r)
            current = current.getNextRow();

        if (current.getCol() == c && current.getRow() == r)
            value = current.getValue();

        return value;
    }

    public int diagonalSum(){
        int sum = 0;
        for (int i = 1; i <= _size; i++)
            sum += this.get(i,i);
        return sum;
    }

    public Matrix addMatrix (Matrix matrix){
        Matrix mat = new Matrix (_size);

        if (_size != matrix.get_size()) throw new InvalidParameterException("Sizes must be equal");
        for (int r = 1; r <= _size; r++)
            for (int c = 1; c <= _size; c++)
                mat.set(r, c, this.get(r,c) + matrix.get(r,c));

        return mat;
    }

    public int get_size() {
        return _size;
    }

    public void sting() {
        for (int r = 1; r<= _size; r++){
            String row = "";
            for (int c = 1; c <= _size; c++)
                row += this.get(r, c);
            System.out.println(row);

        }
    }
}
