
class ConstructRectangle {

    // Math.
    public int[] constructRectangle(int area) {
        for (int w = (int) Math.sqrt(area); w > 0; w--) {
            int l = area / w;
            if (l * w == area) {
                return new int[] { l, w };
            }
        }
        return null;
    }
}