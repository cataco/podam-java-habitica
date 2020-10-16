package com.test;

import model.Habit;
import org.testng.annotations.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class tets {

  @Test
  public void testName() {
    PodamFactory factory = new PodamFactoryImpl();

// This will use constructor with minimum arguments and
// then setters to populate POJO
    Habit habit = factory.manufacturePojo(Habit.class);
    System.out.println("Title: " + habit.getTitle());
    System.out.println("Notes: " + habit.getNotes());
  }
}
