package org.tharrisx.test.framework.pipe.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.framework.bean.Bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("innerBean")
public class ExampleInnerBean extends Bean implements Comparable<ExampleInnerBean> {

  private String name = null;

  public String getName() {
    return this.name;
  }

  public void setName(String arg) {
    this.name = arg;
  }

  private String name2 = null;

  public String getName2() {
    return this.name2;
  }

  public void setName2(String arg) {
    this.name2 = arg;
  }

  @Override
  public int compareTo(ExampleInnerBean other) {
    return getName().compareTo(other.getName());
  }

  @Override
  public boolean equals(Object object) {
    if(!(object instanceof ExampleInnerBean))
      return false;
    ExampleInnerBean rhs = (ExampleInnerBean) object;
    return new EqualsBuilder().appendSuper(super.equals(object)).append(getName(), rhs.getName()).append(getName2(), rhs.getName2()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(925349647, -842220045).appendSuper(super.hashCode()).append(getName()).append(getName2()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("name", getName()).append("name2", getName2()).toString();
  }
}
