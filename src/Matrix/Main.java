package Matrix;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        double[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] input1 = {{1, 0, 0, 1}, {0, 2, 1, 2},
                {2, 1, 0, 1}, {1, 0, 1, 4}};
        double[][] input2 = {{3, 6, 7}, {0, 2, 1}, {2, 3, 4}};
        double[][] input3 = {{1, 0, 3, 1}, {0, 5, 1, 2},
                {2, 7, 0, 1}, {1, 8, 1, 4}};
        double[][] input4 = {{1, 0, 0, 1, 1, 0}, {0, 2, 1, 2, 1, 6},
                {2, 1, 0, 1, 1, 1}, {1, 0, 1, 4, 1, 3},
                {1, 1, 3, 1, 1, 5}, {1, 4, 3, 7, 1, 4}};
        double[][] input5 = {{3, 0, 2}, {2, 0, -2}, {0, 1, 1}};
//        System.out.println("The input matrix:\n" + print(input5) + "\n");
//        double det = findDeterminant(input5);
//        System.out.println("The determinant is " + det + "\n");
//        double[][] cofactorsMat = matrixOfCofactors(input5);
//        System.out.println("The Matrix of Cofactors:\n" + print(cofactorsMat) + "\n");
        System.out.println("The input matrix:\n" + print(input3) + "\n");
        double[][] minorsMat = matrixOfMinors(input3);
        System.out.println("The Matrix of Minors:\n" + print(minorsMat) + "\n");
//        double det = findDeterminant(input3);
//        double[][] inverseMat = inverse(input3);
//        System.out.println("The inverse of the matrix:\n" + print(inverseMat) + "\n");
    }


    /**
     * Matrix-matrix multiplication (y = A * B)
     *
     * @param a the first input matrix
     * @param b the second input matrix
     * @return the resultant matrix y
     */
    public static double[][] mutiply(double[][] a, double[][] b) {
        int m1 = a.length; // input matrix1 row number
        int n1 = a[0].length; // input matrix1 column number
        int m2 = b.length; // input matrix2 row number
        int n2 = b[0].length; // input matrix2 column number
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] y = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    y[i][j] += a[i][k] * b[k][j];
        return y;
    }


    /**
     * Matrix-vector multiplication (y = A * x)
     *
     * @param a the input matrix
     * @param x the input vector
     * @return the multiplication vector y
     */
    public static double[] multiply(double[][] a, double[] x) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }


    /**
     * Vector-matrix multiplication (y = x^T A)
     * (Might be unnecessary)
     *
     * @param a the input vector
     * @param x the input matrix
     * @return the multiplication vector y
     */
    public static double[] multiply(double[] x, double[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }


    /**
     * Matrix-number multiplication (y = A * x)
     *
     * @param a the input matrix
     * @param x the input number
     * @return the resultant matrix y
     */
    public static double[][] multiply(double[][] a, double x) {
        int m = a.length; // input matrix1 row number
        int n = a[0].length; // input matrix1 column number
        double[][] y = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i][j] += a[i][j] * x;
        return y;
    }


    /**
     * Matrix-matrix addition (y = A + B)
     *
     * @param a the first input matrix
     * @param b the second input matrix
     * @return the addition of two matrices
     */
    public static double[][] add(double[][] a, double[][] b) {
        int m1 = a.length; // input matrix1 row number
        int n1 = a[0].length; // input matrix1 column number
        int m2 = b.length; // input matrix2 row number
        int n2 = b[0].length; // input matrix2 column number
        double[][] y = new double[m1][n1];
        if (m1 != m2 || n1 != n2) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n1; j++)
                y[i][j] = a[i][j] + b[i][j];
        return y;
    }


    /**
     * Matrix-matrix subtraction (y = A - B)
     *
     * @param a the first input matrix
     * @param b the second input matrix
     * @return the subtraction of two matrices
     */
    public static double[][] subtract(double[][] a, double[][] b) {
        int m1 = a.length; // input matrix1 row number
        int n1 = a[0].length; // input matrix1 column number
        int m2 = b.length; // input matrix2 row number
        int n2 = b[0].length; // input matrix2 column number
        double[][] y = new double[m1][n1];
        if (m1 != m2 || n1 != n2) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n1; j++)
                y[i][j] = a[i][j] - b[i][j];
        return y;
    }

    /**
     * Inverse the matrix
     * <p>
     * Reference website:
     * https://www.mathsisfun.com/algebra/matrix-inverse-minors-cofactors-adjugate.html
     *
     * @param mat the input matrix
     * @return the inverse of the matrix
     */
    public static double[][] inverse(double[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        double det = 0;
        double determinant = findDeterminant(mat);
        if (m != n)
            throw new RuntimeException("Not a square matrix.");

        // Step 1: Calculate the "Matrix of Minors"
        mat = matrixOfMinors(mat);

        // Step 2 & 3: Convert into "Matrix of Cofactors" and transpose all elements of it.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[j][i] = mat[i][j] * (double) Math.pow(-1, i + j);
            }
        }

        // Step 4: Multiply by 1/Determinant
        return multiply(mat, 1 / determinant);
    }

    /**
     * Find the determinant of a square matrix
     *
     * @param mat the input square matrix
     * @return the determinant of the matrix
     */
    public static double findDeterminant(double[][] mat) {
        int len = mat.length;
        double det = 0;
        if (mat.length != mat[0].length)
            throw new RuntimeException("Not a square matrix.");
        if (len == 2)
            return mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        else {
            for (int i = 0; i < len; i++) {
                double[][] subMatrix = breakdown(mat, 0, i);
                det += mat[0][i] * findDeterminant(subMatrix) *
                        (double) Math.pow(-1, i);
            }
        }
        return det;
    }


    /**
     * Break down a matrix into one with a smaller dimension.
     * <p>
     * Input matrix:{{ 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 9.0 }}
     * m = 0, n = 1 (removing all elements in row 0 and column 1)
     * <p>
     * Output matrix: {{ 2.0, 3.0 }, { 8.0, 9.0 }}
     *
     * @param mat input matrix to break down
     * @param m   row index
     * @param n   columm index
     * @return the breakdown matrix
     */
    private static double[][] breakdown(double[][] mat, int m, int n) {
        int len = mat.length;
        double[][] temp = new double[len - 1][];
        double[][] result = new double[len - 1][len - 1];
        if (mat == null || m >= len || n >= len || m < 0 || n < 0) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        int a = 0;
        if (m == 0) {
            for (int i = 1; i < len; i++)
                temp[a++] = mat[i];
        } else {
            for (int i = 0; i < m; i++)
                temp[i] = mat[i];
            for (int i = m; i < len - 1; i++)
                temp[i++] = mat[i];
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < n; j++)
                result[i][j] = temp[i][j];
            for (int j = n; j < len - 1; j++)
                result[i][j] = temp[i][j + 1];
        }
        return result;
    }

    /**
     * Calculate the "Matrix of Minors"
     * (Not Used, but in the purpose of testing methods)
     *
     * @param mat input matrix
     * @return Matrix of Minors
     */
    private static double[][] matrixOfMinors(double[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        double det = 0;
        double[][] newMat = new double[m][n];
        if (m != n)
            throw new RuntimeException("Not a square matrix.");
        // It works only when the dimension is larger than 2.
        if (m > 2) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    newMat[i][j] = findDeterminant(breakdown(mat, i, j));
                }
            }
        }
        return newMat;
    }

    /**
     * Calculate the "Matrix of Cofactors"
     * (Not Used, but in the purpose of testing methods)
     *
     * @param mat input matrix
     * @return Matrix of Cofactors
     */
    private static double[][] matrixOfCofactors(double[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] *= (double) Math.pow(-1, i + j);
            }
        }
        return mat;
    }

    /**
     * Transpose the matrix
     *
     * @param mat the input matrix
     * @return the transposed matrix
     */
    public static double[][] transpose(double[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                result[j][i] = mat[i][j];
        return result;
    }

    /**
     * Print the matrix in a much more readable way
     * <p>
     * [1.0  0.0]
     * [0.0  2.0]
     * [2.0  1.0]
     *
     * @param mat input matrix
     * @return formatted matrix
     */
    public static String print(double[][] mat) {
        StringBuilder builder = new StringBuilder();
        int m = mat.length;
        int n = mat[0].length;
        DecimalFormat dF = new DecimalFormat("0.###");
        // The pattern here could also be "0.00#" to
        // leave all integers and decimals to the thousandths

        for (int i = 0; i < m; i++) {
            builder.append("[");
            for (int j = 0; j < n; j++) {
                builder.append(dF.format(mat[i][j]));
                if (j != n - 1)
                    builder.append("  ");
                else
                    builder.append("]\n");
            }
        }
        return builder.toString();
    }

    /**
     * Return a string representation of the matrix
     *
     * @param mat the input matrix
     * @return a string representing the matrix
     */
    public static String toString(double[][] mat) {
        StringBuilder builder = new StringBuilder("{");
        int m = mat.length;
        int n = mat[0].length;
        DecimalFormat dF = new DecimalFormat("0.###");
        for (int i = 0; i < m; i++) {
            builder.append("{ ");
            for (int j = 0; j < n; j++) {
                builder.append(dF.format(mat[i][j]));
                if (j != n - 1) {
                    builder.append(", ");
                }
            }
            builder.append(" }");
            if (i != m - 1) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Return a string representation of the vector
     * Add convenience by using DecimalFormat
     * Available to substitute with "System.out.println(Arrays.toString(array))"
     *
     * @param vector the input vector
     * @return a string representing the vector
     */
    public static String toString(double[] vector) {
        DecimalFormat dF = new DecimalFormat("0.###");
        StringBuilder builder = new StringBuilder("[");
        int len = vector.length;
        for (int i = 0; i < len; i++) {
            builder.append(dF.format(vector[i]));
            if (i != len - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
