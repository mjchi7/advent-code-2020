class PasswordValidator(object):

    def __init__(self, raw):
        policy, password = raw.split(': ')
        diced = policy.split(' ')
        freq_diced = diced[0].split('-')
        self.raw = raw
        self.char_to_match = diced[1]
        self.pos1 = int(freq_diced[0])
        self.pos2 = int(freq_diced[1])
        self.password = password

    def validate(self):
        # no concept of index 0, therefore - 1 to conver to index 0
        char_at_pos1 = self.password[self.pos1 - 1]
        char_at_pos2 = self.password[self.pos2 - 1]
        if char_at_pos1 == self.char_to_match:
            # return True
            if char_at_pos2 != self.char_to_match:
                result = True
            else:
                result = False
        elif char_at_pos2 == self.char_to_match:
            result = True  # Since this branch can only be reached if char_at_pos1 != self.char_to_match, we can confirm it is true
        else:
            result = False  # This case is neither is the char
        print("[DEBUG]: min_freq: {}\tmax_freq: {}\tchar_to_match: {}\tpassword: {}\traw: {}\t result: {}\tchar_at_pos1: {}\tchar_at_pos2: {}".format(
            self.pos1, self.pos2, self.char_to_match, self.password, self.raw, result, char_at_pos1, char_at_pos2))
        return result


def load_data():
    with open('input.txt', 'r') as f:
        data = f.readlines()
    return data


def get_valid_password():
    data = load_data()
    valid_passwords = []
    for datum in data:
        pv = PasswordValidator(datum)
        if pv.validate():
            valid_passwords.append(datum)
    return valid_passwords


def run_main():
    valid_apsswords = get_valid_password()
    print("Number of valid password: " + str(len(valid_apsswords)))


if __name__ == "__main__":
    run_main()
