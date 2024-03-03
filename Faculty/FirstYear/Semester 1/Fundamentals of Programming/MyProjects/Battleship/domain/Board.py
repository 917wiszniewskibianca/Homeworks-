class Board:

    def __init__(self):
        self.grid = [[" _"]*10 for i in range(10)]
        self.hit_count = 0

    def __str__(self):
        str_val = "  0 1 2 3 4 5 6 7 8 9\n"
        for i in range(10):
            str_val += str(i)
            for j in range(10):
                str_val += self.grid[i][j]
            if i != 9:
                str_val += "\n"
        return str_val

    def get_public_view(self):
        str_val = "  0 1 2 3 4 5 6 7 8 9\n"
        for i in range(10):
            str_val += str(i)
            for j in range(10):
                if self.grid[i][j] == " B":
                    str_val += " _"
                else:
                    str_val += self.grid[i][j]
            if i != 9:
                str_val += "\n"
        return str_val

    def add_boat(self, boat):
        width = 1
        height = 1
        if boat.orientation == "v":
            height = boat.size
        else:
            width = boat.size

        if (boat.x < 0) or (boat.y < 0) or (boat.x+width > 10) or (boat.y+height > 10):
            return False

        for x in range(width):
            for y in range(height):
                if self.grid[boat.y + y][boat.x + x] != " _":
                    return False

        for x in range(width):
            for y in range(height):
                self.grid[boat.y + y][boat.x + x] = " B"
        return True

    def attack(self, x, y):
        current_value = self.grid[y][x]
        if current_value == " B":
            self.grid[y][x] = " X"
            self.hit_count += 1
            return True
        elif current_value == " _":
            self.grid[y][x] = " O"
            return False
        else:
            return False

    def is_defeated(self):
        if self.hit_count == 17:
            return True
        else:
            return False
