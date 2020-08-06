class PowerOfFour {

  // Math
  public boolean isPowerOfFourI(int num) {
    return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
  }

  // Bit Manipulation

  public boolean isPowerOfFourII(int num) {
    return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
  }

  // Bit Manipulation + Math
  public boolean isPowerOfFour(int num) {
    return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
  }
}