def perfect_power(n, verbose=False):

  for base in range(2, int(math.sqrt(n) + 1)):
    power = max(int(math.log(n)/math.log(base))-1, 1)
    comparison = n - math.pow(base, power)

    while comparison > 0:
      power += 1
      comparison = n - math.pow(base, power)

    if comparison == 0:
      if verbose:
        print(n, "es potencia perfecta de", base, "elevado a", power)
      return True

  if verbose:
    print(n, "NO es potencia perfecta")
  return False




def calculate_r(n, verbose=False):
  r = 1
  k = 0

  limit = math.pow(math.log2(n), 2)

  while k <= limit: # Esto se hace hasta encontrar r. Como r toma valor máximo de log^5(n), este bucle es log^5(n)
    r += 1
    k = 1
    result = pow(n, k, r)

    # multiplicativeOrder
    while result != 1 and r > k:
      k += 1
      result = pow(n, k, r)

    if verbose:
      print('Con un valor de r de', r, '- k ha alcanzado un valor de', k)

    if r <= k:
      k = -1

  return r



def calculate_gcd(n, r, verbose=False):
  for i in range(2, r+1):
    gcd = math.gcd(n, i)

    if verbose:
      print("MCD de ", n, "y", i, "es", gcd)

    if gcd > 1 and gcd < n:
      return False

  return True


def totient(n):

  result = n
  i = 2

  while math.pow(i, 2) <= n:
    if n % i == 0:
      result -= result/i

    while n % i == 0:
      n /= i

    i += 1

  if n > 1:
    result -= result / n

  return int(result)


perfect_power(7, True)

calculate_r(3, True)

calculate_gcd(5954, 97, True)

totient(11)
