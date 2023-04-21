package model;

/**
 * A class that represents a HSL pixel.
 */
public class HSL {
  private double hue;
  private double saturation;
  private double lightness;

  /**
   * Initializes a HSL pixel.
   * @param hue The hue of the pixel.
   * @param saturation The saturation of the pixel.
   * @param lightness The lightness of the pixel.
   */
  public HSL(double hue, double saturation, double lightness) {
    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
  }

  public HSL multiply(HSL background) {
    double newLightness = this.lightness * background.lightness;
    return new HSL(this.hue, this.saturation, newLightness);
  }

  public HSL screen(HSL background) {
    double newLightness = (1 - ((1 - this.lightness) * (1 - background.lightness)));
    return new HSL(this.hue, this.saturation, newLightness);
  }

  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }

  /**
   * Converts the HSL image to RGB.
   * @return A RGB pixel.
   */
  public model.RGB convertHSLtoRGB() {
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;
    return new RGB((int) r, (int) g, (int) b);
  }
}
