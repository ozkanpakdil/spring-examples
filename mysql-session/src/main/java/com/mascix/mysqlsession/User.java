package com.mascix.mysqlsession;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import lombok.Data;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@SqlResultSetMapping(name="flagResult", columns = { @ColumnResult(name = "@FLAG")})

@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "native1",
      query = "select * from user where name=:name",
      resultClass = User.class
    ),
    @NamedNativeQuery(
      name = "nativeSessionSet",
      query = "set @FLAG=:name",
      resultClass = User.class
    ),
    @NamedNativeQuery(
      name = "nativeSessionGet",
      query = "select @FLAG",
      resultSetMapping = "flagResult"
    ),
    @NamedNativeQuery(
      name = "UpdateStoredProc",
      query = "call UPDATE_USER(:id, :name, :mail)",
      resultClass = User.class
    ),
  }
)
@Data
@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

}