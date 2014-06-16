package org.pochemuto.termo.problem;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

import java.util.function.DoubleUnaryOperator;

import org.pochemuto.termo.core.TDMA;

/**
 * @author pochemuto
 */
public class Problem {
    public double[][] solve() {
        int T = 10, N = 50;
        double h = 1.0 / (N - 1), tau = 1 / (T - 1);
        double[][] U = new double[T][N];
        double[][] F = new double[T][N];

        double mu1 = 2, mu2 = -1;
        double k = 0.18, c = 0.1203;

        DoubleUnaryOperator f = x -> 8 * sin(PI * x * 3) + (mu2 - mu1) * x + mu1;

        double x = 0;
        for (int i = 0; i < N; i++) {
            double v = f.applyAsDouble(x);
            U[0][i] = v;
            x += h;
        }

        double mu = c * tau / (2 * h * h);
        double[] A = new double[N], B = new double[N], C = new double[N];
        for (int i = 0; i < N; i++) {
            A[i] = -mu;
            B[i] = -mu;
            C[i] = 1 + 2 * mu;
            C[i] *= -1;
        }

        for (int i = 0; i < T; i++) {
            U[i][0] = mu1;
            U[i][N-1] = mu2;
        }

        for (int i = 0; i < T - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                F[i][j] = U[i][j - 1] * mu + U[i][j] * (1 - 2 * mu) + U[i][j + 1] * mu;
                F[i][j] *= -1;
            }
            U[i] = TDMA.solve(A, B, C, F[i]);
        }

        return U;
    }
}
