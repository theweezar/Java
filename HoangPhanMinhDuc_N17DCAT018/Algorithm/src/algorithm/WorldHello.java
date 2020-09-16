package algorithm;

public class WorldHello {

	public static void main(String[] args) {
		String a = "Hello The Beautiful World";
		char[] b = new char[a.length()];
		int t = 0;
		for(int i = a.length() - 1; i >= 0; i--) {
			if (a.charAt(i) == ' ' || i == 0) {
				if (i == 0) b[t++] = a.charAt(i);
				int j = i + 1;
				while(t < a.length()) {
					b[t++] = a.charAt(j++);
					if (j == a.length() || a.charAt(j) == ' ') break;
				}
				if (t < a.length()) b[t++] = ' ';
			}
		}
		System.out.println(b);
	}
}

