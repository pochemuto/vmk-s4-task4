package org.pochemuto.termo.core;

import static org.junit.Assert.assertArrayEquals;

import java.util.Random;

import org.junit.Test;

public class TDMATest {

    @Test
    public void testSolveRandom() throws Exception {
        int n = 10;
        double[] a = new double[n];
        double[] b = new double[n];
        double[] c = new double[n];
        double[] x = new double[n];
        double[] f = new double[n];

        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextDouble();
            b[i] = r.nextDouble();
            c[i] = r.nextDouble();
            x[i] = r.nextDouble();
        }
        a[0] = 0;
        b[n - 1] = 0;

        for (int i = 0; i < n; i++) {
            double cf = 0;
            if (i > 0) {
                cf = a[i] * x[i - 1];
            }
            cf += c[i] * x[i];
            if (i + 1 < n) {
                cf += b[i] * x[i + 1];
            }
            f[i] = cf;
        }


        double[] result = TDMA.solve(a, b, c, f);

        assertArrayEquals(x, result, 1e-9);
    }

    @Test
    public void testFixed() throws Exception {
        int n = 5;
        double a[] = {0, 3, 6, 6, 5}, b[] = {2, 5, 3, 7, 0}, c[] = {1, 4, 7, 3, 6}, x[] = {10, 11, 12, 13, 14}, f[] = new double[5];

        for (int i = 0; i < n; i++) {
            double cf = 0;
            if (i > 0) {
                cf = a[i] * x[i - 1];
            }
            cf += c[i] * x[i];
            if (i + 1 < n) {
                cf += b[i] * x[i + 1];
            }
            f[i] = cf;
        }

        double[] result = TDMA.solve(a, b, c, f);

        assertArrayEquals(x, result, 1e-9);
    }
}