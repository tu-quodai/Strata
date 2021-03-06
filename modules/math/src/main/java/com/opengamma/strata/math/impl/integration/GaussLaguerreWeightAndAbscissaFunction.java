/*
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.math.impl.integration;

import java.util.function.DoubleUnaryOperator;

import org.apache.commons.math3.util.CombinatoricsUtils;

import com.opengamma.strata.collect.ArgChecker;
import com.opengamma.strata.collect.tuple.Pair;
import com.opengamma.strata.math.impl.function.DoubleFunction1D;
import com.opengamma.strata.math.impl.function.special.GammaFunction;
import com.opengamma.strata.math.impl.function.special.LaguerrePolynomialFunction;
import com.opengamma.strata.math.impl.rootfinding.NewtonRaphsonSingleRootFinder;

/**
 * Class that generates weights and abscissas for Gauss-Laguerre quadrature.
 * The weights $w_i$ are given by:
 * $$
 * \begin{align*}
 * w_i = -\frac{\Gamma(\alpha + n)}{n!L_i'(x_i)L_{i-1}(x_i)}
 * \end{align*}
 * $$
 * where $x_i$ is the $i^{th}$ root of the orthogonal polynomial, $L_i$ is the
 * $i^{th}$ polynomial and $L_i'$ is the first derivative of the $i^{th}$
 * polynomial. The orthogonal polynomial is generated by
 * {@link LaguerrePolynomialFunction}.
 */
public class GaussLaguerreWeightAndAbscissaFunction implements QuadratureWeightAndAbscissaFunction {

  private static final LaguerrePolynomialFunction LAGUERRE = new LaguerrePolynomialFunction();
  private static final NewtonRaphsonSingleRootFinder ROOT_FINDER = new NewtonRaphsonSingleRootFinder(1e-10);
  private static final DoubleUnaryOperator GAMMA_FUNCTION = new GammaFunction();
  private final double _alpha;

  /**
   * Creates an instance.
   * Sets $\alpha = 0$
   */
  public GaussLaguerreWeightAndAbscissaFunction() {
    this(0);
  }

  /**
   * Creates an instance.
   * @param alpha The value of $\alpha$ to use when generating the polynomials.
   */
  public GaussLaguerreWeightAndAbscissaFunction(double alpha) {
    _alpha = alpha;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GaussianQuadratureData generate(int n) {
    ArgChecker.isTrue(n > 0);
    Pair<DoubleFunction1D, DoubleFunction1D>[] polynomials = LAGUERRE.getPolynomialsAndFirstDerivative(n, _alpha);
    Pair<DoubleFunction1D, DoubleFunction1D> pair = polynomials[n];
    DoubleFunction1D p1 = polynomials[n - 1].getFirst();
    DoubleFunction1D function = pair.getFirst();
    DoubleFunction1D derivative = pair.getSecond();
    double[] x = new double[n];
    double[] w = new double[n];
    double root = 0;
    for (int i = 0; i < n; i++) {
      root = ROOT_FINDER.getRoot(function, derivative, getInitialRootGuess(root, i, n, x));
      x[i] = root;
      w[i] =
          -GAMMA_FUNCTION.applyAsDouble(_alpha + n) / CombinatoricsUtils.factorialDouble(n) /
              (derivative.applyAsDouble(root) * p1.applyAsDouble(root));
    }
    return new GaussianQuadratureData(x, w);
  }

  private double getInitialRootGuess(double previousRoot, int i, int n, double[] x) {
    if (i == 0) {
      return (1 + _alpha) * (3 + 0.92 * _alpha) / (1 + 1.8 * _alpha + 2.4 * n);
    }
    if (i == 1) {
      return previousRoot + (15 + 6.25 * _alpha) / (1 + 0.9 * _alpha + 2.5 * n);
    }
    int j = i - 1;
    return previousRoot + ((1 + 2.55 * j) / 1.9 / j + 1.26 * j * _alpha / (1 + 3.5 * j)) * (previousRoot - x[i - 2]) /
        (1 + 0.3 * _alpha);
  }

}
