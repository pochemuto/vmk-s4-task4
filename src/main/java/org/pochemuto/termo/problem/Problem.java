package org.pochemuto.termo.problem;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

import java.util.function.DoubleUnaryOperator;

/**
 * @author pochemuto
 */
public class Problem {
    public double[][] solve() {
        int T = 10, N = 50;
        double h = 1.0 / (N - 1), tau = 1 / (T - 1);
        double[][] U = new double[T][N];

        double mu1 = 2, mu2 = -1;
        double k = 0.18, c = 0.1203;

        DoubleUnaryOperator f = x -> 8 * sin(PI * x * 3) + (mu2 - mu1) * x + mu1;

        double x = 0;
        for (int i = 0; i < N; i++) {
            double v = f.applyAsDouble(x);
            U[0][i] = v;
            x+= h;
        }

        return U;
    }
}
