/*
 * Copyright (C) 2018 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.bond;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaBean;
import org.joda.beans.TypedMetaBean;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.PropertyDefinition;
import org.joda.beans.impl.light.LightMetaBean;

import com.opengamma.strata.collect.ArgChecker;

/**
 * The settlement details of a capital indexed bond trade.
 * <p>
 * When a trade in a capital indexed bond occurs there is an agreed settlement process.
 * This class captures details of that process for the purpose of pricing.
 * <p>
 * Once the trade has settled, end of day processing typically aggregates the trades into positions.
 * As a position combines multiple trades at different prices, the information in this class does not apply.
 * 
 * <h4>Price</h4>
 * Strata uses <i>decimal prices</i> for bonds in the trade model, pricers and market data.
 * For example, a price of 99.32% is represented in Strata by 0.9932.
 */
@BeanDefinition(style = "light")
public final class ResolvedCapitalIndexedBondSettlement
    implements ImmutableBean, Serializable {

  /**
   * The settlement date.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate settlementDate;
  /**
   * The <i>clean</i> price at which the bond was traded.
   * <p>
   * The "clean" price excludes any accrued interest.
   * <p>
   * Strata uses <i>decimal prices</i> for bonds in the trade model, pricers and market data.
   * For example, a price of 99.32% is represented in Strata by 0.9932.
   */
  @PropertyDefinition(validate = "ArgChecker.notNegative")
  private final double price;
  /**
   * The payment of the settlement.
   * <p>
   * The payment sign should be compatible with the product notional and trade quantity, 
   * thus the payment is negative for positive quantity and positive for negative quantity.
   * <p>
   * This is effectively a fixed amount payment once the inflation rate is fixed.
   */
  @PropertyDefinition(validate = "notNull")
  private final BondPaymentPeriod payment;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance from the settlement date, price and amount.
   * 
   * @param settlementDate  the settlement date
   * @param price  the price at which the trade was agreed
   * @param amount  the amount of the settlement
   * @return the settlement information
   */
  public static ResolvedCapitalIndexedBondSettlement of(LocalDate settlementDate, double price, BondPaymentPeriod amount) {
    return new ResolvedCapitalIndexedBondSettlement(settlementDate, price, amount);
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code ResolvedCapitalIndexedBondSettlement}.
   */
  private static final TypedMetaBean<ResolvedCapitalIndexedBondSettlement> META_BEAN =
      LightMetaBean.of(
          ResolvedCapitalIndexedBondSettlement.class,
          MethodHandles.lookup(),
          new String[] {
              "settlementDate",
              "price",
              "payment"},
          new Object[0]);

  /**
   * The meta-bean for {@code ResolvedCapitalIndexedBondSettlement}.
   * @return the meta-bean, not null
   */
  public static TypedMetaBean<ResolvedCapitalIndexedBondSettlement> meta() {
    return META_BEAN;
  }

  static {
    MetaBean.register(META_BEAN);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private ResolvedCapitalIndexedBondSettlement(
      LocalDate settlementDate,
      double price,
      BondPaymentPeriod payment) {
    JodaBeanUtils.notNull(settlementDate, "settlementDate");
    ArgChecker.notNegative(price, "price");
    JodaBeanUtils.notNull(payment, "payment");
    this.settlementDate = settlementDate;
    this.price = price;
    this.payment = payment;
  }

  @Override
  public TypedMetaBean<ResolvedCapitalIndexedBondSettlement> metaBean() {
    return META_BEAN;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement date.
   * @return the value of the property, not null
   */
  public LocalDate getSettlementDate() {
    return settlementDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the <i>clean</i> price at which the bond was traded.
   * <p>
   * The "clean" price excludes any accrued interest.
   * <p>
   * Strata uses <i>decimal prices</i> for bonds in the trade model, pricers and market data.
   * For example, a price of 99.32% is represented in Strata by 0.9932.
   * @return the value of the property
   */
  public double getPrice() {
    return price;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment of the settlement.
   * <p>
   * The payment sign should be compatible with the product notional and trade quantity,
   * thus the payment is negative for positive quantity and positive for negative quantity.
   * <p>
   * This is effectively a fixed amount payment once the inflation rate is fixed.
   * @return the value of the property, not null
   */
  public BondPaymentPeriod getPayment() {
    return payment;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedCapitalIndexedBondSettlement other = (ResolvedCapitalIndexedBondSettlement) obj;
      return JodaBeanUtils.equal(settlementDate, other.settlementDate) &&
          JodaBeanUtils.equal(price, other.price) &&
          JodaBeanUtils.equal(payment, other.payment);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(settlementDate);
    hash = hash * 31 + JodaBeanUtils.hashCode(price);
    hash = hash * 31 + JodaBeanUtils.hashCode(payment);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("ResolvedCapitalIndexedBondSettlement{");
    buf.append("settlementDate").append('=').append(JodaBeanUtils.toString(settlementDate)).append(',').append(' ');
    buf.append("price").append('=').append(JodaBeanUtils.toString(price)).append(',').append(' ');
    buf.append("payment").append('=').append(JodaBeanUtils.toString(payment));
    buf.append('}');
    return buf.toString();
  }

  //-------------------------- AUTOGENERATED END --------------------------
}
