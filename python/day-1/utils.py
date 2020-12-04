def load_input():
  with open('input.txt', 'r') as f:
    inp_raw = f.readlines()
  # Converting to int
  inp = list(map(lambda val: int(val), inp_raw))
  return inp