nums = [
"00001",
"00010",
"01000",
"10000",
"00011",
"01001",
"01010",
"10001",
"10010",
"11000",
"01011",
"11001",
"10111",
"11110",
"11111",
]

cubes = [
"0X0X1",
"XX001",
"0X01X",
"010XX",
"X100X",
"1X00X",
"X0010",
"100X0",
"1X111",
"1111X",
]


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

def check_num(num, term):
  for j,k in zip(num,term):
    if j != k and "X" not in (j,k):
      return False
  return True

  # term = "1101X"
for term in cubes:
  print(term, end='\t')
  for i, n in enumerate(nums):
    if check_num(n, term):
      print(i+1, end=' ')
  print()