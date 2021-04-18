package org.diatum.pageview.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "page", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Page implements Serializable {
  private Integer id;
  private String page;
  private Integer views;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  @JsonIgnore
  public Integer getId() {
    return this.id;
  }
  public void setId(Integer value) {
    this.id = value;
  }

  @JsonIgnore
  public String getPage() {
    return this.page;
  }
  public void setPage(String value) {
    this.page = value;
  }

  @JsonIgnore
  public Integer getViews() {
    return this.views;
  }
  public void setViews(Integer value) {
    this.views = value;
  }

}
