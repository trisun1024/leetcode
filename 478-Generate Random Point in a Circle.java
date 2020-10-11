
class GenerateRandomPointInACircle {

    // Reject Sampling.
    class SolutionI {

        double rad;
        double xc;
        double yc;

        public SolutionI(double radius, double x_center, double y_center) {
            rad = radius;
            xc = x_center;
            yc = y_center;
        }

        public double[] randPoint() {
            double x0 = xc - rad;
            double y0 = yc - rad;
            while (true) {
                double xg = x0 + Math.random() * rad * 2;
                double yg = y0 + Math.random() * rad * 2;
                if (Math.sqrt(Math.pow(xg - xc, 2) + Math.pow(yg - yc, 2)) <= rad) {
                    return new double[] { xg, yg };
                }
            }
        }
    }
 
    // Math.

    class Solution {
        double rad, xc, yc;

        public Solution(double radius, double x_center, double y_center) {
            rad = radius;
            xc = x_center;
            yc = y_center;
        }

        public double[] randPoint() {
            double d = rad * Math.sqrt(Math.random());
            double theta = Math.random() * 2 * Math.PI;
            return new double[] { d * Math.cos(theta) + xc, d * Math.sin(theta) + yc };
        }
    }
}