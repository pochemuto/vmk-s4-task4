package org.pochemuto.termo.core;

/**
 * @author pochemuto
 */
public class TDMA {

    /**
     * Прогонка
     * @param a
     * @param b
     * @param c
     * @param f
     * @return
     */
    public static double[] solve(double[] a, double[] b, double[] c, double[] f) {
        int n = f.length;
        double[] x = new double[n], alpha = new double[n], beta = new double[n];

        alpha[0] = 0;
        beta[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            alpha[i + 1] = -b[i] / (a[i] * alpha[i] + c[i]);
            beta[i + 1] = (f[i] - a[i] * beta[i]) / (a[i] * alpha[i] + c[i]);
        }
        x[n - 1] = (f[n - 1] - a[n - 1] * beta[n - 1]) / (c[n - 1] + a[n - 1] * alpha[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            x[i] = alpha[i + 1] * x[i + 1] + beta[i + 1];
        }
        return x;
    }

}
