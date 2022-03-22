with open("insert.html", "r") as f:
    all_lines = f.readlines()

for line in all_lines:
    line = line.rstrip("\n")
    print(f'out.println("{line}");')