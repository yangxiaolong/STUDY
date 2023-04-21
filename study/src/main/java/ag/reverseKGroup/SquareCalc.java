package ag.reverseKGroup;

public class SquareCalc {

	public static void main(String[] args) {
		double h = 1.5;
		double l = 1.4;
		double c = 0.00000000000001;
		double m = (h + l) / 2;
		while (h - l > c) {
			if (m * m > 2) {
				h = m;
			} else {
				l = m;
			}
			m = (h + l) / 2;
		}
		System.out.println(m);
	}

}
