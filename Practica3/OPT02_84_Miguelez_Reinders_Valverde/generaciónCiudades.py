import random

def create_map(size, min_value, max_value, simmetric=True):
    map = []
    for i in range(size):
        map.append([])

        if simmetric:
            for j in range(size):
                if i == j:
                    map[i].append(0)
                if j > i:
                    value = random.randint(min_value, max_value)
                    map[i].append(value)
                if i > j:
                    map[i].append(map[j][i])       
        else:
            for j in range(size):
                if i != j:
                    value = random.randint(min_value, max_value)
                    map[i].append(value)
                else:
                    map[i].append(0)
    
    return map


def save_map(file, map):
    with open(file, 'w') as f:
        for row in map:
            for i, value in enumerate(row):
                f.write(str(value))
                if i < len(map)-1:
                    f.write(',')
            f.write('\n')

for i in range(3, 20):
    for j in range(10):
        file_name_s ='mapas/t' + str(i) + '-s_' + str(j+1) + '.txt'
        file_name_ns ='mapas/t' + str(i) + '-ns_' + str(j+1) + '.txt'

        save_map(file_name_ns, create_map(i, 1, 50, False))
        save_map(file_name_s, create_map(i, 1, 50, True))