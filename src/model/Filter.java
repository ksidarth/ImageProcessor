package model;

/**
 * An enumeration representing all filters.
 */
public enum Filter {
  NORMAL("normal"),
  RED_COMPONENT("red-component"),
  GREEN_COMPONENT("green-component"),
  BLUE_COMPONENT("blue-component"),
  BRIGHTEN_VALUE("brighten-value"),
  DARKEN_VALUE("darken-value"),
  BLEND_BACKGROUND("blend-with-background"),
  MULTIPLY("multiply"),
  SCREEN("screen"),
  NOT_OPTION("null");

  private String representation;

  Filter(String s) {
    this.representation = s;
  }

  public String getRepresentation() {
    return this.representation;
  }

  /**
   * Finds the filter when given a string.
   * @param abbr String representation of a filter.
   * @return Filter.
   */
  public static Filter findByAbbr(String abbr) {
    for (Filter filter : Filter.values()) {
      if (filter.getRepresentation().equalsIgnoreCase(abbr)) {
        return filter;
      }
    }
    return null;
  }
}
