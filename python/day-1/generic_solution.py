import utils
import functools

# WIP
def generic_sum_2020(inp, n_numbers=2):
  n_elemns = len(inp)
  if n_numbers > n_elemns:
    raise Exception("The number of numbers to be found cannot be greater than the total length of input")
  # The arithmetic behind n_elemns - n_numbers + 1 is so that we don't 
  # loop to the part where it will cause list index out of range
  for i in range(n_elemns - n_numbers + 1):
    round_sum = 0
    captured_numbers = []
    for n in range(n_numbers):
      # Capturing the current number
      captured_numbers.append(inp[i + n])
      round_sum = round_sum + inp[i + n]
    print(round_sum)
    if round_sum == 2020:
      return functools.reduce(lambda x, y: x * y, captured_numbers)

  raise Exception("No {} numbers summed to 2020".format(n_elemns))

def run_main():
  inp = utils.load_input()
  sum_two_2020 = generic_sum_2020(inp, 2)
  print("Multiplication of two vals: " + str(sum_two_2020))
  sum_three_2020 = generic_sum_2020(inp, 3)
  print("Multiplication of two vals: " + str(sum_three_2020))

if __name__ == "__main__":
  run_main()