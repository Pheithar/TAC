import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.stream.LongStream;

public class heuristicas extends Thread{
	public static BigInteger n;
	public static double logSave = -1;
	private static boolean n_isprime;
	private static BigInteger factor;
	public static boolean verbose = false;
	public static BigInteger k = BigInteger.ONE;
	public static BigInteger r = BigInteger.ONE;

	public static void main(String[] args) throws IOException{
		long inicio,fin,resultado;
		int [] primes11k = {
				2,     3,    5,    7,   11,   13,   17,   19,   23,   29,
				31,   37,   41,   43,   47,   53,   59,   61,   67,   71,
				73,   79,   83,   89,   97,  101,  103,  107,  109,  113,
				127,  131,  137,  139,  149,  151,  157,  163,  167,  173,
				179,  181,  191,  193,  197,  199,  211,  223,  227,  229,
				233,  239,  241,  251,  257,  263,  269,  271,  277,  281,
				283,  293,  307,  311,  313,  317,  331,  337,  347,  349,
				353,  359,  367,  373,  379,  383,  389,  397,  401,  409,
				419,  421,  431,  433,  439,  443,  449,  457,  461,  463,
				467,  479,  487,  491,  499,  503,  509,  521,  523,  541,
				547,  557,  563,  569,  571,  577,  587,  593,  599,  601,
				607,  613,  617,  619,  631,  641,  643,  647,  653,  659,
				661,  673,  677,  683,  691,  701,  709,  719,  727,  733,
				739,  743,  751,  757,  761,  769,  773,  787,  797,  809,
				811,  821,  823,  827,  829,  839,  853,  857,  859,  863,
				877,  881,  883,  887,  907,  911,  919,  929,  937,  941,
				947,  953,  967,  971,  977,  983,  991,  997, 1009, 1013,
				1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069,
				1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151,
				1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223,
				1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291,
				1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373,
				1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451,
				1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511,
				1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583,
				1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657,
				1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733,
				1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811,
				1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889,
				1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987,
				1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053,
				2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129,
				2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213,
				2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287,
				2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357,
				2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423,
				2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531,
				2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617,
				2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687,
				2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741,
				2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819,
				2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903,
				2909, 2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999,
				3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079,
				3083, 3089, 3109, 3119, 3121, 3137, 3163, 3167, 3169, 3181,
				3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257,
				3259, 3271, 3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331,
				3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413,
				3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511,
				3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571,
				3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643,
				3659, 3671, 3673, 3677, 3691, 3697, 3701, 3709, 3719, 3727,
				3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803, 3821,
				3823, 3833, 3847, 3851, 3853, 3863, 3877, 3881, 3889, 3907,
				3911, 3917, 3919, 3923, 3929, 3931, 3943, 3947, 3967, 3989,
				4001, 4003, 4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057,
				4073, 4079, 4091, 4093, 4099, 4111, 4127, 4129, 4133, 4139,
				4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231,
				4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283, 4289, 4297,
				4327, 4337, 4339, 4349, 4357, 4363, 4373, 4391, 4397, 4409,
				4421, 4423, 4441, 4447, 4451, 4457, 4463, 4481, 4483, 4493,
				4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583,
				4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657,
				4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751,
				4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831,
				4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937,
				4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003,
				5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087,
				5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179,
				5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279,
				5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387,
				5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443,
				5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521,
				5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639,
				5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693,
				5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791,
				5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857,
				5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939,
				5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053,
				6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133,
				6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221,
				6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301,
				6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367,
				6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473,
				6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571,
				6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673,
				6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761,
				6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833,
				6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917,
				6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997,
				7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103,
				7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207,
				7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297,
				7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411,
				7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499,
				7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561,
				7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643,
				7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723,
				7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829,
				7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919};
		
		int [] primes1k = {
				283,601,191,971,137,883,467,577,587,677,257,269,829,397,733,977,439,433,331,449,719,761,487,199,941,887,229,547,641,863,787,251,571,929,151,919,263,809,317,857,911,227,643,337,881,499,223,653,613,743,709,463,353,701,811,563,179,823,673,313,389,983,647,379,241,419,109,173,503,233,113,727,367,991,859,967,659,277,163,797,271,239,197,557,509,193,409,937,593,691,619,521,131,631,769,127,347,373,401,421,2903,5431,5827,1223,4007,3769,3967,5791,9467,8627,6217,1879,5801,4027,8111,5087,3469,9203,6733,3023,4079,2459,4987,1481,4363,1987,9109,9967,6619,9277,8581,3853,7177,7853,2467,2447,5843,8369,1549,6199,5273,9811,5749,1609,6421,1237,1801,1283,1531,9697,9151,5099,1301,1873,4729,2473,2969,4001,5417,4421,2153,9623,1571,8527,6203,2111,4271,3557,8419,2719,6361,1129,4591,8689,1019,9323,9013,2953,8831,1471,7549,6637,3191,4391,6197,5227,8363,8779,6449,8237,2819,8677,9791,5441,3691,4409,7789,2411,8923,6761
		};
		long[][] times = new long[200][10];
		long [] times_mean = new long[200];
		long sum;
		long[] lista = new long[200];
		long result;
		BigInteger resultI;
		BigInteger valueoflista;
		BigInteger t;

		/*BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Usuario\\eclipse-workspace\\Primos\\src\\selected_primes.csv"));
		String row;
		int count = 0;
		csvReader.readLine();
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			lista[count] = Long.parseLong(data[0]);
			count++;
		}
		csvReader.close();*/
		
		/*inicio = System.nanoTime();
		for(int i = 0; i < 1000; i++) {
			n = BigInteger.valueOf(primes1k[i]);
			for (int j = 0; j < 10; j++) {
				inicio = System.nanoTime();
				h1();
				fin = System.nanoTime();
				times[i][j] = fin-inicio;
			}
		}*/
		
		for(int i=0;i<200;i++) {
			System.out.println(i);
			valueoflista = BigInteger.valueOf(primes1k[i]);
			for(int j=0;j<10;j++) {
				n = valueoflista;
				logSave = -1;
				h1();
				h2();
				t = totient(r);
				inicio = System.nanoTime();
				h3(t);
				fin = System.nanoTime();
				result = fin-inicio;
				times[i][j] = result;
			}
			sum = 0;
			for (int j = 0; j < 10; j++) {
				sum = sum + times[i][j];
			}
			times_mean[i] = sum/10;
		}
		/*csvReader = new BufferedReader(new FileReader("C:\\Users\\Usuario\\eclipse-workspace\\Primos\\src\\selected_primes.csv"));
		count = 0;
		csvReader.readLine();
		long[] lista2 = new long[500];
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			lista2[count] = Long.parseLong(data[1]);
			count++;
		}
		csvReader.close();*/
		
		String output = "number,digit,time\n";
		for(int i = 0; i < 200; i++) {
			output = output + primes1k[i]+ ", " + /*lista2[i]*/Math.ceil(Math.log10(primes1k[i])) + ", " + times_mean[i] + "\n";
		}
		FileWriter f = new FileWriter("C:\\Users\\Usuario\\eclipse-workspace\\Primos\\src\\data.csv", true);
		f.write(output);
		f.close();
	}

	public static boolean h1() {
		// TODO: Do this in linear time http://www.ams.org/journals/mcom/1998-67-223/S0025-5718-98-00952-1/S0025-5718-98-00952-1.pdf
		// If ( n = a^b for a in natural numbers and b > 1), output COMPOSITE
		BigInteger a = BigInteger.valueOf(2);
		BigInteger aSquared;

		do {
			BigInteger result;

			int power = Math.max((int) (log() / log(a) - 2), 1);
			int comparison;

			do {
				power++;
				result = a.pow(power);
				comparison = n.compareTo(result);
			}
			while (comparison > 0 && power < Integer.MAX_VALUE);

			if (comparison == 0) {
				factor = a;
				n_isprime = false;
				return n_isprime;
			}


			a = a.add(BigInteger.ONE);
			aSquared = a.pow(2);
		}
		while (aSquared.compareTo(n) <= 0);
		return true;
	}

	public static boolean h2() {      
		double log = log();
        double logSquared = log * log;
        k = BigInteger.ONE;
        r = BigInteger.ONE;
        do {
            r = r.add(BigInteger.ONE);
            if (verbose) System.out.println("trying r = " + r);
            k = multiplicativeOrder(r);
        }
        while (k.doubleValue() < logSquared);
        if (verbose) System.out.println("r is " + r);


        // If 1 < gcd(a,n) < n for some a <= r, output COMPOSITE
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger gcd = n.gcd(i);
            if (verbose) System.out.println("gcd(" + n + "," + i + ") = " + gcd);
            if (gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(n) < 0) {
                factor = i;
                n_isprime = false;
                return false;
            }
        }


        // If n <= r, output PRIME
        if (n.compareTo(r) <= 0) {
            n_isprime = true;
            return true;
        }
		return false; //No significa que el número no sea primo si no que no se ha podido demostrar que lo sea
	}
	
	public static BigInteger totient(BigInteger n) {
		BigInteger result = n;
		
		for (BigInteger i = BigInteger.valueOf(2); n.compareTo(i.multiply(i)) > 0; i = i.add(BigInteger.ONE)) {
			if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
				result = result.subtract(result.divide(i));

			while (n.mod(i).compareTo(BigInteger.ZERO) == 0)
				n = n.divide(i);
		}

		if (n.compareTo(BigInteger.ONE) > 0)
			result = result.subtract(result.divide(n));
		return result;
	}
	
	public static boolean h3(BigInteger t) {
		//int limit = (int) (Math.sqrt(totient(r).doubleValue()) * log());
        int limit = (int) (Math.sqrt(t.doubleValue()) * log());
		// X^r - 1
        Poly modPoly = new Poly(BigInteger.ONE, r.intValue()).minus(new Poly(BigInteger.ONE, 0));
        // X^n (mod X^r - 1, n)
        Poly partialOutcome = new Poly(BigInteger.ONE, 1).modPow(n, modPoly, n);
        for (int i = 1; i <= limit; i++) {
            Poly polyI = new Poly(BigInteger.valueOf(i), 0);
            // X^n + i (mod X^r - 1, n)
            Poly outcome = partialOutcome.plus(polyI);
            Poly p = new Poly(BigInteger.ONE, 1).plus(polyI).modPow(n, modPoly, n);
            if (!outcome.equals(p)) {
                if (verbose)
                    System.out.println("(x+" + i + ")^" + n + " mod (x^" + r + " - 1, " + n + ") = " + outcome);
                if (verbose) System.out.println("x^" + n + " + " + i + " mod (x^" + r + " - 1, " + n + ") = " + p);
                // if (verbose) System.out.println("(x+i)^" + n + " = x^" + n + " + " + i + " (mod x^" + r + " - 1, " + n + ") failed");
                factor = BigInteger.valueOf(i);
                n_isprime = false;
                return n_isprime;
            } else if (verbose)
                System.out.println("(x+" + i + ")^" + n + " = x^" + n + " + " + i + " mod (x^" + r + " - 1, " + n + ") true");
        }
        
        n_isprime = true;
        return n_isprime;
	}
	
	private static double log() {
		if (logSave != -1)
			return logSave;

		// from http://world.std.com/~reinhold/BigNumCalcSource/BigNumCalc.java
		BigInteger b;

		int temp = n.bitLength() - 1000;
		if (temp > 0) {
			b = n.shiftRight(temp);
			logSave = (Math.log(b.doubleValue()) / Math.log(2) + temp);
		} else
			logSave = (Math.log(n.doubleValue())) / Math.log(2);

		return logSave;
	}


	/**
	 * Log base 2 method that takes a parameter
	 *
	 * @param x
	 * @return log base 2 of x
	 */
	private static double log(BigInteger x) {
		// from http://world.std.com/~reinhold/BigNumCalcSource/BigNumCalc.java
		BigInteger b;

		int temp = x.bitLength() - 1000;
		if (temp > 0) {
			b = x.shiftRight(temp);
			return (Math.log(b.doubleValue()) / Math.log(2.0D) + temp);
		} else
			return (Math.log(x.doubleValue()) / Math.log(2.0D));
	}

	public static BigInteger multiplicativeOrder(BigInteger r) {
		// TODO Consider implementing an alternative algorithm http://rosettacode.org/wiki/Multiplicative_order
		BigInteger k = BigInteger.ZERO;
		BigInteger result;

		do {
			k = k.add(BigInteger.ONE);
			result = n.modPow(k, r);
		}
		while (result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 0);

		if (r.compareTo(k) <= 0)
			return BigInteger.ONE.negate();
		else {
			return k;
		}
	}

	public static long[] Generador (int digitos) {
		double d=digitos;
		double m=digitos;
		d= Math.pow(10, d);
		m= Math.pow(10, m-1)-1;
		Random Numero= new Random();
		LongStream n= Numero.longs(200,(long)m,(long)d);
		long[] a=n.toArray();
		return a;	
	}
}
