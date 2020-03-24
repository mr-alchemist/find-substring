import java.util.Arrays;

import storage.FactorArray;

public class Program {

	public static void main(String[] args) {
		Program program = new Program();
		program.run();
	}
	
	void run() {
		//int x = searchBM("BABBARA BAR BARBOR BARBARATOR","BARBA");
		int x = searchBM("ротоколокол пища колокол","колокол");
		System.out.println(x);
		
		x = searchKMP("ротоколокол пища колокол","колокол");
		System.out.println(x);
	}
	
	public int searchBM(String text, String pattern) {//находит и возвращает индекс первого вхождения (по алгоритму Бойера-Мура)
		int[] prefix = createPrefixArr(pattern);
		int[] suffix = createSuffixArray(pattern);
		
		System.out.println(pattern);
		for(int i = 0; i < pattern.length();i++) 
			System.out.print(prefix[pattern.charAt(i)]);
		System.out.println("");
		
		for(int i = 0; i < text.length() - pattern.length() + 1; ) {
			System.out.println(text);
			System.out.println( padLeft( pattern, i) );
			int j = pattern.length() - 1;
			while(text.charAt(i + j) == pattern.charAt(j))
				if(j-- == 0)
					return i;
			
			i += Math.max(j - prefix[text.charAt(i + j)], suffix[j + 1]);
			//i += Math.max(j - prefix[text.charAt(i + j)], 1 );
			
		}
			
		return -1;
	} 
	
	int[] createPrefixArr(String pattern) {
		int[] prefix = new int[65535];
		Arrays.fill(prefix, -1);
		for(int i = 0; i < pattern.length() - 1; i++) 
			prefix[pattern.charAt(i)] = i;
		
		return prefix;
	}
	
	int[] createSuffixArray(String pattern) {
		int[] res = new int[pattern.length() + 1];
		Arrays.fill(res, pattern.length());
		res[res.length - 1] = 1;
		
		for(int j = pattern.length() - 1; j >= 0  ; j--)
			for(int at = j; at < pattern.length() ;at++)
				for(int i = at - 1; i >= 0;i--) {
					String a = pattern.substring(at);
					String b = pattern.substring(i, i + (pattern.length() - at) );
					if(a.equals(b)) {
						res[j] = at - i;
						at = pattern.length();
						break;
					}
				}
		
		
		return res;
	}
	
	String padLeft(String s, int n) {
		for(int i = 0; i < n; i++)
			s = " " + s;
		return s;
	}
	
	int[] getPiFast(String pattern) {
		int n = pattern.length();
		int[] pi = new int[n];
		Arrays.fill(pi, 0);
		for(int i = 1; i < n; i++) {
			int q = pi[i - 1];
			while(q > 0 && pattern.charAt(i) != pattern.charAt(q) )
				q = pi[q - 1];
			
			if(pattern.charAt(i) == pattern.charAt(q))
				q++;
			
			pi[i] = q;
		}
		return pi;
	}
	
	public int searchKMP(String text, String pattern) {//находит и возвращает индекс первого вхождения (по алгоритму Кнута-Морриса-Пратта)
		String line = pattern + "#" + text;
		int ptnL = pattern.length();
		int[] pi = getPiFast(line);
		for(int i = ptnL + 1;i < line.length();i++) {
			if(pi[i] == ptnL)
				return (i - 2*ptnL);
		}
		return -1;
	}
	
	public int[] searchKMPAll(String text, String pattern) {//находит и возвращает индексы всех вхождений (по алгоритму Кнута-Морриса-Пратта)
		FactorArray<Integer> faRes = new FactorArray<Integer>();
		String line = pattern + "#" + text;
		int ptnL = pattern.length();
		int[] pi = getPiFast(line);
		for(int i = ptnL + 1; i < line.length(); i++) {
			if(pi[i] == ptnL) 
				faRes.add(i - 2*ptnL);
		}
		
		int[] res = new int[faRes.size()];
		for(int i = 0; i < faRes.size(); i++) 
			res[i] = faRes.get(i);
		
		return res;
	}
}
