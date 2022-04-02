from itertools import product

maxS = 2**15 - 1
minS = - 2**15

A = -2883
B = 122

constInterval = [-2883, 0]

cases = [
  {
    "X": [-3976],
    "Y": [-2], 
    "Z": [55], 
  }, 
    # {
    # "X": constInterval,
    # "Y": [-5420, 0-1, -A+1, 4981], 
    # "Z": [-4981, A-1, 0+1, 5419]
  # }, 
  # {
    # "X": [-4981, A-1, 0+1, 5461],
    # "Y": constInterval, 
    # "Z": [-4981, A-1, 0+1, 5419],
  # }, {
    # "X": [-4981, A-1, 0+1, 5461],
    # "Y": [-5941, 0-1, -A+1, 4981], 
    # "Z": constInterval,
  # }, 
  # { # 5
    # "X": constInterval,
    # "Y": constInterval,
    # "Z": [-5461, A-1, 0+1, 5419]
  # }, { # 6
    # "X": [-10922, A-1, 0+1, 10880], 
    # "Y": constInterval,
    # "Z": constInterval,
  # }, { # 7
    # "X": constInterval,
    # "Y": [-10922, A-1, 0+1, 5420], 
    # "Z": constInterval,
  # }, { # 8
    # "X": constInterval,
    # "Y": constInterval, 
    # "Z": constInterval,
  # },
]


def check(x):
  if x > maxS:
    raise Exception(f"{x=} is greater than {maxS}")
  if x < minS:
    raise Exception(f"{x=} is less than {minS}")

def sub_prog(x):
  if x in range(A, 0+1):
    return A
  try:
    check(x)
    check(3*x)
    check(3*x+B)
  except Exception as e:
    raise Exception(f"subProg, {e}")
  return 3*x+B

def prog(X, Y, Z):
  f1 = sub_prog(Z) + 1
  check(f1)
  f2 = sub_prog(Y) - 1
  check(f2)
  f3 = sub_prog(X) + 1
  check(f3)

  sub_f1_f2 = f1 - f2
  check(sub_f1_f2)

  result = sub_f1_f2 + f3
  check(result)
  return result


def main():
  for i, case in enumerate(cases):
    for (x, y, z) in product(case["X"], case["Y"], case["Z"]):
      # try:
      # print(f"case {i+1}")
      print(f"case {i+1} with values {x=}, {y=}, {z=}")
      res = prog(x, y, z)
      print(res)
      # except Exception as e:
        # print(f"case {i+1} with values {x=}, {y=}, {z=}: {e}")


if __name__ == "__main__":
  main()
