/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.strata.report.trade;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableList;
import com.opengamma.strata.engine.config.Measure;

/**
 * Describes a column in a report that corresponds to a calculated measure.
 */
@BeanDefinition
public class TradeReportColumn implements ImmutableBean {

  /** The calculated measure. */
  @PropertyDefinition(validate = "notNull")
  private final Measure measure;

  /** The path to evalute against the measure in order to traverse the object graph.  */
  @PropertyDefinition(get = "optional")
  private final ImmutableList<String> path;

  /** The column header. */
  @PropertyDefinition(get = "field")
  private final String header;
  
  /** Whether to ignore failures, or report the errors. */
  @PropertyDefinition
  private final boolean ignoreFailure;

  //-------------------------------------------------------------------------
  /**
   * Gets the report column header, for display purposes.
   * 
   * @return the header
   */
  public String getHeader() {
    return header != null ? header : measure.toString();
  }

  //-------------------------------------------------------------------------
  public static TradeReportColumn of(Measure measure) {
    return TradeReportColumn.builder().measure(measure).build();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code TradeReportColumn}.
   * @return the meta-bean, not null
   */
  public static TradeReportColumn.Meta meta() {
    return TradeReportColumn.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(TradeReportColumn.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static TradeReportColumn.Builder builder() {
    return new TradeReportColumn.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected TradeReportColumn(TradeReportColumn.Builder builder) {
    JodaBeanUtils.notNull(builder.measure, "measure");
    this.measure = builder.measure;
    this.path = (builder.path != null ? ImmutableList.copyOf(builder.path) : null);
    this.header = builder.header;
    this.ignoreFailure = builder.ignoreFailure;
  }

  @Override
  public TradeReportColumn.Meta metaBean() {
    return TradeReportColumn.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the calculated measure.
   * @return the value of the property, not null
   */
  public Measure getMeasure() {
    return measure;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the path to evalute against the measure in order to traverse the object graph.
   * @return the optional value of the property, not null
   */
  public Optional<ImmutableList<String>> getPath() {
    return Optional.ofNullable(path);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to ignore failures, or report the errors.
   * @return the value of the property
   */
  public boolean isIgnoreFailure() {
    return ignoreFailure;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      TradeReportColumn other = (TradeReportColumn) obj;
      return JodaBeanUtils.equal(getMeasure(), other.getMeasure()) &&
          JodaBeanUtils.equal(path, other.path) &&
          JodaBeanUtils.equal(header, other.header) &&
          (isIgnoreFailure() == other.isIgnoreFailure());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getMeasure());
    hash = hash * 31 + JodaBeanUtils.hashCode(path);
    hash = hash * 31 + JodaBeanUtils.hashCode(header);
    hash = hash * 31 + JodaBeanUtils.hashCode(isIgnoreFailure());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("TradeReportColumn{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("measure").append('=').append(JodaBeanUtils.toString(getMeasure())).append(',').append(' ');
    buf.append("path").append('=').append(JodaBeanUtils.toString(path)).append(',').append(' ');
    buf.append("header").append('=').append(JodaBeanUtils.toString(header)).append(',').append(' ');
    buf.append("ignoreFailure").append('=').append(JodaBeanUtils.toString(isIgnoreFailure())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code TradeReportColumn}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code measure} property.
     */
    private final MetaProperty<Measure> measure = DirectMetaProperty.ofImmutable(
        this, "measure", TradeReportColumn.class, Measure.class);
    /**
     * The meta-property for the {@code path} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableList<String>> path = DirectMetaProperty.ofImmutable(
        this, "path", TradeReportColumn.class, (Class) ImmutableList.class);
    /**
     * The meta-property for the {@code header} property.
     */
    private final MetaProperty<String> header = DirectMetaProperty.ofImmutable(
        this, "header", TradeReportColumn.class, String.class);
    /**
     * The meta-property for the {@code ignoreFailure} property.
     */
    private final MetaProperty<Boolean> ignoreFailure = DirectMetaProperty.ofImmutable(
        this, "ignoreFailure", TradeReportColumn.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "measure",
        "path",
        "header",
        "ignoreFailure");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 938321246:  // measure
          return measure;
        case 3433509:  // path
          return path;
        case -1221270899:  // header
          return header;
        case -149357736:  // ignoreFailure
          return ignoreFailure;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public TradeReportColumn.Builder builder() {
      return new TradeReportColumn.Builder();
    }

    @Override
    public Class<? extends TradeReportColumn> beanType() {
      return TradeReportColumn.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code measure} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Measure> measure() {
      return measure;
    }

    /**
     * The meta-property for the {@code path} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ImmutableList<String>> path() {
      return path;
    }

    /**
     * The meta-property for the {@code header} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> header() {
      return header;
    }

    /**
     * The meta-property for the {@code ignoreFailure} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> ignoreFailure() {
      return ignoreFailure;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 938321246:  // measure
          return ((TradeReportColumn) bean).getMeasure();
        case 3433509:  // path
          return ((TradeReportColumn) bean).path;
        case -1221270899:  // header
          return ((TradeReportColumn) bean).header;
        case -149357736:  // ignoreFailure
          return ((TradeReportColumn) bean).isIgnoreFailure();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code TradeReportColumn}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<TradeReportColumn> {

    private Measure measure;
    private List<String> path;
    private String header;
    private boolean ignoreFailure;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(TradeReportColumn beanToCopy) {
      this.measure = beanToCopy.getMeasure();
      this.path = beanToCopy.path;
      this.header = beanToCopy.header;
      this.ignoreFailure = beanToCopy.isIgnoreFailure();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 938321246:  // measure
          return measure;
        case 3433509:  // path
          return path;
        case -1221270899:  // header
          return header;
        case -149357736:  // ignoreFailure
          return ignoreFailure;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 938321246:  // measure
          this.measure = (Measure) newValue;
          break;
        case 3433509:  // path
          this.path = (List<String>) newValue;
          break;
        case -1221270899:  // header
          this.header = (String) newValue;
          break;
        case -149357736:  // ignoreFailure
          this.ignoreFailure = (Boolean) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public TradeReportColumn build() {
      return new TradeReportColumn(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code measure} property in the builder.
     * @param measure  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder measure(Measure measure) {
      JodaBeanUtils.notNull(measure, "measure");
      this.measure = measure;
      return this;
    }

    /**
     * Sets the {@code path} property in the builder.
     * @param path  the new value
     * @return this, for chaining, not null
     */
    public Builder path(List<String> path) {
      this.path = path;
      return this;
    }

    /**
     * Sets the {@code path} property in the builder
     * from an array of objects.
     * @param path  the new value
     * @return this, for chaining, not null
     */
    public Builder path(String... path) {
      return path(ImmutableList.copyOf(path));
    }

    /**
     * Sets the {@code header} property in the builder.
     * @param header  the new value
     * @return this, for chaining, not null
     */
    public Builder header(String header) {
      this.header = header;
      return this;
    }

    /**
     * Sets the {@code ignoreFailure} property in the builder.
     * @param ignoreFailure  the new value
     * @return this, for chaining, not null
     */
    public Builder ignoreFailure(boolean ignoreFailure) {
      this.ignoreFailure = ignoreFailure;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("TradeReportColumn.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("measure").append('=').append(JodaBeanUtils.toString(measure)).append(',').append(' ');
      buf.append("path").append('=').append(JodaBeanUtils.toString(path)).append(',').append(' ');
      buf.append("header").append('=').append(JodaBeanUtils.toString(header)).append(',').append(' ');
      buf.append("ignoreFailure").append('=').append(JodaBeanUtils.toString(ignoreFailure)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
