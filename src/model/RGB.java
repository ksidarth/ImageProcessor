package model;

/**
 * A class that represents an individual pixel.
 */
public class RGB {
  private int red;
  private int green;
  private int blue;
  private int alpha; // Transparency value.

  /**
   * Initializes the RGB class.
   * @param red the red val.
   * @param green the green val.
   * @param blue the blue val.
   * @param alpha the alpha val.
   */
  public RGB(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Initializes RGB without alpha.
   * @param red red val.
   * @param green green val.
   * @param blue blue val.
   */
  public RGB(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = 255;
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  public int getAlpha() {
    return this.alpha;
  }

  /**
   * Gets the pixel with background.
   * @param background The background pixel.
   * @return A new RGB pixel.
   */
  public RGB imageWithBackground(RGB background) {
    double alphaConstant = this.alpha / 255;
    double backgroundConstant = background.getAlpha() / 255;
    double alphaRatio = alphaConstant + (backgroundConstant
            * (1 - alphaConstant));
    int finalAlpha = (int) (alphaRatio * 255);
    int finalRed = (int) ((alphaConstant * this.red) + (background.getRed() * (backgroundConstant)
                * (1 - alphaConstant) * (1 / alphaRatio)));
    int finalGreen = (int) ((alphaConstant * this.green) + (background.getGreen()
            * (backgroundConstant)
                * (1 - alphaConstant) * (1 / alphaRatio)));
    int finalBlue = (int) ((alphaConstant * this.blue) + (background.getBlue()
            * (backgroundConstant)
                * (1 - alphaConstant) * (1 / alphaRatio)));
    return new RGB(finalRed, finalGreen, finalBlue, finalAlpha);
  }

  /**
   * Blends pixel with background.
   * @param background Background pixel.
   * @return blended pixel.
   */
  public RGB blendWithBackground(RGB background) {
    double alphaConstant = this.alpha / 255;
    double backgroundConstant = background.getAlpha() / 255;
    int newRed = (int) Math.abs((this.red * alphaConstant) - (background.red * backgroundConstant));
    int newGreen = (int) Math.abs((this.green * alphaConstant)
            - (background.green * backgroundConstant));
    int newBlue = (int) Math.abs((this.blue * alphaConstant)
            - (background.blue * backgroundConstant));
    return new RGB(newRed, newGreen, newBlue);
  }

  /**
   * Edits RGB values.
   * @param rVal edit red value.
   * @param gVal edit green value.
   * @param bVal edit blue value.
   */
  public void editRGB(int rVal, int gVal, int bVal) {
    this.red = this.red * rVal;
    this.green = this.green * gVal;
    this.blue = this.blue * bVal;
  }

  /**
   * Edits the intensity of RGB values.
   * @param value new val.
   */
  public void editIntensity(int value) {
    this.red = value;
    this.green = value;
    this.blue = value;
  }

  /**
   * Converts a RGB pixel to HSL pixel.
   * @return HSL pixel.
   */
  public HSL convertRGBtoHSL() {
    double tempRed = (this.red / 255.0);
    double tempBlue = (this.blue / 255.0);
    double tempGreen = (this.green / 255.0);
    double componentMax = Math.max(tempRed, Math.max(tempGreen, tempBlue));
    double componentMin = Math.min(tempRed, Math.min(tempGreen, tempBlue));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == tempRed) {
        hue = (tempGreen - tempBlue) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == tempGreen) {
        hue = (tempBlue - tempRed) / delta;
        hue += 2;
      } else if (componentMax == tempBlue) {
        hue = (tempRed - tempGreen) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }
    return new HSL(hue, saturation, lightness);
  }

  public String toString() {
    return this.red + " " + this.green + " " + this.blue;
  }
}
