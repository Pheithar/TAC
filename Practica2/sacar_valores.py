def complexity(n):
    total = n + 2*2**(n) + 3

    for i in range(1, n+1):
        total += 2 + (3*2**i)/2

        for j in range(1, 2**i + 1):
            total += j

    return int(total)


with open("data_palind_3.csv", 'w') as f:
    for i in range(26):
        f.write(str(complexity(i)) + '\n')
        print(i, complexity(i))
