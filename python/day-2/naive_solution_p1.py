class PasswordValidator(object):

    def __init__(self, raw):
        policy, password = raw.split(':')
        diced = policy.split(' ')
        freq_diced = diced[0].split('-')
        self.raw = raw
        self.char_to_match = diced[1]
        self.min_freq = int(freq_diced[0])
        self.max_freq = int(freq_diced[1])
        self.password = password
        print("[DEBUG]: min_freq: {}\tmax_freq: {}\tchar_to_match: {}\tpassword: {}\traw: {}".format(
            self.min_freq, self.max_freq, self.char_to_match, self.password, self.raw))

    def validate(self):
        # validate min_freq
        n_occurence = self.password.count(self.char_to_match)
        if n_occurence < self.min_freq or n_occurence > self.max_freq:
            return False
        return True


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
