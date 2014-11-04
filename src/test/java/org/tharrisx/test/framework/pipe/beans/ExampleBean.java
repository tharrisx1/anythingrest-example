package org.tharrisx.test.framework.pipe.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.framework.bean.Bean;
import org.tharrisx.framework.pipe.ProtectionType;
import org.tharrisx.framework.pipe.annotations.PipeEncryption;
import org.tharrisx.framework.pipe.annotations.PipeProtection;
import org.tharrisx.framework.pipe.codec.EncryptedIdCodec;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("bean")
public class ExampleBean extends Bean implements Comparable<ExampleBean> {

  private String name = null;

  public String getName() {
    return this.name;
  }

  public void setName(String arg) {
    this.name = arg;
  }

  @XStreamAlias("exposedNumber")
  @XStreamAsAttribute
  private int someNumber = 0;

  public int getSomeNumber() {
    return this.someNumber;
  }

  public void setSomeNumber(int arg) {
    this.someNumber = arg;
  }

  @XStreamAsAttribute
  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  private Long someBigNumber = null;

  public Long getSomeBigNumber() {
    return this.someBigNumber;
  }

  public void setSomeBigNumber(Long arg) {
    this.someBigNumber = arg;
  }

  @PipeProtection(ProtectionType.NAME_HIDDEN)
  private Short omittedNumber = null;

  public Short getOmittedNumber() {
    return this.omittedNumber;
  }

  public void setOmittedNumber(Short arg) {
    this.omittedNumber = arg;
  }

  @XStreamAlias("encryptedString")
  @PipeEncryption(EncryptedIdCodec.class)
  private String encryptedValue = null;

  public String getEncryptedValue() {
    return this.encryptedValue;
  }

  public void setEncryptedValue(String arg) {
    this.encryptedValue = arg;
  }

  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  @XStreamImplicit(itemFieldName = "crazyMap")
  private List<Map<String, ExampleInnerBean>> crazyListOfMaps = null;

  public List<Map<String, ExampleInnerBean>> getCrazyListOfMaps() {
    return this.crazyListOfMaps;
  }

  public void setCrazyListOfMaps(List<Map<String, ExampleInnerBean>> arg) {
    this.crazyListOfMaps = arg;
  }

  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  @XStreamImplicit(itemFieldName = "exampleInnerObject")
  private Set<ExampleInnerBean> setOfInnerBeans = null;

  public Set<ExampleInnerBean> getSetOfInnerBeans() {
    return this.setOfInnerBeans;
  }

  public void setSetOfInnerBeans(Set<ExampleInnerBean> arg) {
    this.setOfInnerBeans = arg;
  }

  @Override
  public int compareTo(ExampleBean other) {
    return getName().compareTo(other.getName());
  }

  @Override
  public boolean equals(Object object) {
    if(!(object instanceof ExampleBean))
      return false;
    ExampleBean rhs = (ExampleBean) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(getName(), rhs.getName()).append(getSomeNumber(), rhs.getSomeNumber()).append(getSomeBigNumber(), rhs.getSomeBigNumber())
        .append(getOmittedNumber(), rhs.getOmittedNumber()).append(getCrazyListOfMaps(), rhs.getCrazyListOfMaps()).append(getSetOfInnerBeans(), rhs.getSetOfInnerBeans()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(1306984737, -1666574839).appendSuper(super.hashCode()).append(getName()).append(getSomeNumber()).append(getSomeBigNumber()).append(getOmittedNumber())
        .append(getCrazyListOfMaps()).append(getSetOfInnerBeans()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("name", getName()).append("someNumber", getSomeNumber()).append("someBigNumber", getSomeBigNumber()).append("omittedNumber", getOmittedNumber())
        .append("crazyListOfMaps", getCrazyListOfMaps()).append("setOfInnerBeans", getSetOfInnerBeans()).toString();
  }
}
