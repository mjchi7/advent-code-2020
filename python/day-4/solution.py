class Passport(object):
    def __init__(self, pp_raw):
        self.is_valid = False
        compulsory_fields = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"]
        self.validation = {
            "byr": [lambda x: len(x) == 4 and int(x) > 1920 and int(x) < 2002],
            "iyr": [lambda x: len(x) == 4 and int(x) > 2010 and int(x) < 2020],
            "eyr": [lambda x: len(x) == 4 and int(x) > 2020 and int(x) < 2030],
            "hgt": [], # TODO
            "hcl": [lambda x: len(x) == 5], # TODO
            "ecl": [lambda x: x in ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"]],
            "pid": [lambda x: len(x) == 9],
            "cid": []
        }
        self.props = {}
        skippable = ["cid"]
        raw = pp_raw.replace("\n", " ")
        diced = raw.split(" ")
        fields = []
        for d in diced:
            if d == "":
                continue
            key, val = d.split(":")
            self.props[key] = val
            fields.append(key)
        absent_fields = list(set(compulsory_fields) - set(fields))
        absent_compulsory_fields = list(set(absent_fields) - set(skippable))
        if len(absent_compulsory_fields) > 0:
            self.is_valid = False
        else:
            self.is_valid = True
    
    def validate(self):
        pass

    def get_is_valid(self):
        return self.is_valid


class PassportsParser(object):

    def __init__(self, raw):
        assert type(raw) == str, "The raw should be a string"
        # split by whitespace in between
        diced = raw.split("\n\n")
        # print(diced)
        self.pps = [Passport(d) for d in diced]
    
    def get_valid_passport(self):
        valid_pp = [p for p in self.pps if p.get_is_valid()]
        return valid_pp

def load_data():
    with open('input.txt', 'r') as f:
        data = f.read()
    return data

def run_main():
    data = load_data()
    parser = PassportsParser(data)
    valid_pps = parser.get_valid_passport()
    print("Number of valid passports: " + str(len(valid_pps)))


if __name__ == "__main__":
    run_main()
