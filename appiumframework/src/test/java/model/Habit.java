package model;

import uk.co.jemos.podam.common.PodamStrategyValue;
import uk.co.jemos.podam.common.PodamStringValue;

public class Habit {
  @PodamStringValue(length = 10)
  private String title;

  @PodamStringValue(length = 50)
  private String notes;

  @PodamStrategyValue(DifficultyStrategy.class)
  private String difficulty;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
}
