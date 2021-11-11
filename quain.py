# %%
from collections import defaultdict
from itertools import product

# %%
cube_list_default = [ 
    "00000",
    "00001",
    "00010",
    "00100",
    "01000",
    "10000",
    "00110",
    "01010",
    "01100",
    "10001",
    "10010",
    "10100",
    "11000",
    "00111",
    "01011",
    "10101",
    "10110",
    "11010",
    "11100",
    "01111",
    "11011",
    "11110",
    "11111",
]


def cubelist2dict(cube_list):
    cube_dict = defaultdict(list)
    for term in cube_list:
        ones = term.count("1")
        cube_dict[ones].append(term)
    return cube_dict

def cubedict2list(cube_dict):
    cube_list = []
    for terms in cube_dict.values():
        cube_list += [*terms]
    return cube_list

def get_merge_idx(term1, term2):
    diff_indexes = []
    for idx, (j,k) in enumerate(zip(term1, term2)):
        if j == k:
            continue

        # j!=k. X position in terms is different.
        if "X" in (j,k):
            return None

        diff_indexes.append(idx)

    if len(diff_indexes) != 1:
        return None

    return diff_indexes[0]


def calculate_cubes(cube, cube_number):
    next_cube = defaultdict(lambda: defaultdict(list))
    unused_terms = cubedict2list(cube)
    terms_in_cube = unused_terms.copy()

    for i in range(len(cube) - 1):
        for a, b in product(cube[i], cube[i+1]):
            if (diff_idx := get_merge_idx(a, b)) is None:
                continue

            idx = diff_idx
            res = a[:idx] + "X" + a[idx+1:]

            next_cube[i][res].append(f"{terms_in_cube.index(a)+1}-{terms_in_cube.index(b)+1}")

            # debug info. Prints "1XXX0 | '10XX0'+'11XX0' '1X0X0'+'1X1X0' '1XX00'+'1XX10'"
            # next_cube[i][res].append(f"'{a}'+'{b}'")

            if a in unused_terms: unused_terms.remove(a)
            if b in unused_terms: unused_terms.remove(b)
    
    print(f"Unused from cube {cube_number}: {unused_terms}\n")

    if len(next_cube) == 0:
        return unused_terms

    print_cube(next_cube, cube_number + 1)
    return calculate_cubes(next_cube, cube_number + 1) + unused_terms


def print_cube(cube, cube_number):
    print(f"Cube {cube_number} starts: ")
    for section in cube.values():
        [ print(term, "|", *parts) for term, parts in section.items() ]
        print("------------")
    print(f"Cube {cube_number} ends")
    print("============")
        

if __name__ == "__main__":
    cube_dict = cubelist2dict(cube_list_default)
    total_unused = calculate_cubes(cube_dict, 0)
    unused_column = "\n".join(total_unused)
    print(f"\n\nTotal unused\n: {unused_column}")