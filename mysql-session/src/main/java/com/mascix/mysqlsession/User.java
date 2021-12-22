package com.mascix.mysqlsession;

import javax.persistence.*;

import lombok.Data;

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