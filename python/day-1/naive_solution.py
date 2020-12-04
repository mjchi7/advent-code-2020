import utils

"""
Assuming there can only be a pair of values that sum to 2020
"""

def sum_two_vals_2020(inp):
  n_elemns = len(inp)
  for i in range(n_elemns):
    for j in range(i + 1, n_elemns):
      if inp[i] + inp[j] == 2020:
        return inp[i] * inp[j]
  raise Exception("Pair that sums to 2020 not found.")

def sum_three_vals_2020(inp):
  n_elemns = len(inp)
  for i in range(n_elemns):
    for j in range(i + 1, n_elemns):
      for k in range(j + 1, n_elemns):
        if inp[i] + inp[j] + inp[k] == 2020:
          return inp[i] * inp[j] * inp[k]
  raise Exception("Pair that sums to 2020 not found.")


def run_main():
  inp = utils.load_input()
  result_sum_two = sum_two_vals_2020(inp)
  print("===========================")
  print("The multiplication of the pair of numbers that adds to 2020: " + str(result_sum_two))
  print("===========================")
  result_sum_three = sum_three_vals_2020(inp)
  print("===========================")
  print("The multiplication of the 3 of numbers that adds to 2020: " + str(result_sum_three))
  print("===========================")

if __name__ == "__main__":
  run_main()