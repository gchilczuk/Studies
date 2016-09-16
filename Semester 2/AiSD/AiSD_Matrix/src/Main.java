/**
 * @author Grzegorz Chilczuk
 *         Created on 29 mar 2016
 */
public class Main {
    public static void main(String[] args) {
       /* Matrix matrix = new Matrix(5);
        matrix.set(1,1,4);?
        Matrix mac = new Matrix(5);
        mac.set(1,1,4);
        matrix.set(3,3,1);
        matrix.set(2,3,2);
        mac.set(2,1,1);
        mac.set(1,2,8);
        mac.set(4,4,3);
        matrix.addMatrix(mac);
        System.out.println();
        matrix.set(4,4,4);
        System.out.println(matrix.get(2,2));
        matrix.sting();*/
        Matrix matrix = new Matrix(new int [][] {{5,0,0}, {0,1,0}, {0,0,2}});
        Matrix matrix1 = new Matrix(new int[][] {{1,0,0}, {0,-1,0},{0,2,0}});
        System.out.println("M1:");
        matrix.sting();
        System.out.printf("M1 diagon: ");
        System.out.println(matrix.diagonalSum());
        System.out.println();
        System.out.println("M2:");
        matrix1.sting();
        System.out.printf("M2 diagon: ");
        System.out.println(matrix1.diagonalSum());
        System.out.println();
        Matrix sum = matrix1.addMatrix(matrix);
        System.out.println("SUM:");
        sum.sting();
        System.out.printf("sum diagon: ");
        System.out.println(sum.diagonalSum());


    }
}
