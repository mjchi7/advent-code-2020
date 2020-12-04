def load_data():
    with open('input.txt', 'r') as f:
        data = f.readlines()
        data = [d.strip() for d in data]
    return data

def run_slope(x_move, y_move):
    data = load_data()
    tree_sym = "#"
    height = len(data)
    width = len(data[0])
    print("Total number of lines: " + str(height))
    print("Total number of char per line: " + str(width))
    x = 0
    y = 0
    moveset = (x_move, y_move)
    n_trees = 0
    while y < height - 1:
        x = x + moveset[0]
        y = y + moveset[1]
        print("Current coords: x=" + str(x) + "\ty=" + str(y))
        if x >= width:
            x = x - width
        obj = data[y][x]
        if obj == tree_sym:
            n_trees = n_trees + 1
    print("Number of trees: " + str(n_trees))
    return n_trees

def run_main():
    nt_p1 = run_slope(1, 1)
    nt_p2 = run_slope(3, 1)
    nt_p3 = run_slope(5, 1)
    nt_p4 = run_slope(7, 1)
    nt_p5 = run_slope(1, 2)

    print("Solution 1: " + str(nt_p2))
    print("Solution 2: " + str(nt_p1 * nt_p2 * nt_p3 * nt_p4 * nt_p5))

if __name__ == "__main__":
    run_main()
