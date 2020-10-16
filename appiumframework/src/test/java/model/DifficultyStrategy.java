package model;

import java.lang.annotation.Annotation;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class DifficultyStrategy implements AttributeStrategy<String>  {

  @Override
  public String getValue(Class<?> aClass, List<Annotation> list) {
    return "Medium";
  }
}
