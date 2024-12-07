package org.gfijalkowski.pba_lab_4.users.api.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import org.gfijalkowski.pba_lab_4.users.api.model.Citizenship;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * User
 */

@Entity
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-29T21:13:27.795420100+01:00[Europe/Warsaw]")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Min(18)
  @Max(120)
  private int age;

  @NotNull
  @Size(min = 11, max = 11)
  private String pesel;

  @Enumerated(EnumType.STRING)
  private Citizenship citizenship;

  @NotNull
  @Email
  private String email;


  /**
   * Default constructor
   * @deprecated Use {@link User#User(String, String, Integer, String, Citizenship, String)}
   */
  @Deprecated
  public User() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public User(String firstName, String lastName, Integer age, String pesel, Citizenship citizenship, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.pesel = pesel;
    this.citizenship = citizenship;
    this.email = email;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @NotNull @Size(min = 1, max = 50) 
  @Schema(name = "firstName", example = "John", required = true)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  @NotNull @Size(min = 1, max = 50) 
  @Schema(name = "lastName", example = "Lennon", required = true)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * minimum: 0
   * @return age
  */
  @NotNull @Min(0) 
  @Schema(name = "age", example = "30", required = true)
  @JsonProperty("age")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public User pesel(String pesel) {
    this.pesel = pesel;
    return this;
  }

  /**
   * Get pesel
   * @return pesel
  */
  @NotNull @Pattern(regexp = "^\\d{11}$") 
  @Schema(name = "pesel", example = "12345678901", required = true)
  @JsonProperty("pesel")
  public String getPesel() {
    return pesel;
  }

  public void setPesel(String pesel) {
    this.pesel = pesel;
  }

  public User citizenship(Citizenship citizenship) {
    this.citizenship = citizenship;
    return this;
  }

  /**
   * Get citizenship
   * @return citizenship
  */
  @NotNull @Valid 
  @Schema(name = "citizenship", required = true)
  @JsonProperty("citizenship")
  public Citizenship getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(Citizenship citizenship) {
    this.citizenship = citizenship;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @jakarta.validation.constraints.Email
  @Schema(name = "email", example = "John.Lennon@example.com", required = true)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.age, user.age) &&
        Objects.equals(this.pesel, user.pesel) &&
        Objects.equals(this.citizenship, user.citizenship) &&
        Objects.equals(this.email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, age, pesel, citizenship, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    pesel: ").append(toIndentedString(pesel)).append("\n");
    sb.append("    citizenship: ").append(toIndentedString(citizenship)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

