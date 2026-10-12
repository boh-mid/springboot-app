package com.isikef.club.forms;
import org.springdoc.core.annotations.ParameterObject;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@ParameterObject
@Getter
@Setter
public class ClubForm {
@NotNull(message = "name is required")
private String name;
@NotNull(message = "description is required")
@Size(max = 1000, message = "description must be less than 1000 characters")
@Size(min = 3, message = "description must be at least 3 characters")
private String description;

public String toString(){
  return "ClubForm{" +
          "name='" + name + '\'' +
          ", description='" + description + '\'' +
          '}';
}

}
