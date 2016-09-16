/**
 * @author Grzegorz Chilczuk
 *         Created on 29 mar 2016
 */
public class Lelement {
    private Integer value;
    private Lelement nextCol = null;
    private Lelement nextRow = null;
    private int row;
    private int col;

    public Lelement(int row, int col, Integer value){
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public Lelement getNextCol() {
        return nextCol;
    }

    public Lelement getNextRow() {
        return nextRow;
    }

    public Integer getValue() {
        return value;
    }

    public void setNextCol(Lelement nextCol) {
        this.nextCol = nextCol;
    }

    public void setNextRow(Lelement nextRow) {
        this.nextRow = nextRow;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
    
        return row;
    }
}
