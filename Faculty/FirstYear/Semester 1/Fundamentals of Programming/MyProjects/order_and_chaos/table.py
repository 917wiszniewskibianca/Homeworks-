class Table:
    def __init__(self):
        self.table=[[" _"]*3 for i in range(3)]
        self.x_coord_empty_space=0
        self.y_coord_empty_space= 0

    def __str__(self):
        str_val=""
        for i in range(3):
            for j in range(3):
                str_val +=" "+ self.table[i][j]
            if i != 2:
                str_val += "\n"
        return str_val

    def set_argument(self, argument,x_coord, y_coord):
         if str(x_coord).isnumeric() != True or str(y_coord).isnumeric() != True:
             return False
         if int(x_coord) < 0 or int(x_coord ) > 2:
             return False
         if int(y_coord ) <0 or int(x_coord) > 2:
             return False
         x_coord = int(x_coord)
         y_coord = int(y_coord)
         if self.table[x_coord][y_coord] != " _":
             return False
         x_coord= int(x_coord)
         y_coord=int(y_coord)
         self.table[x_coord][y_coord] = argument
         return True


    def find_empty_space(self):
        for i in range(3):
            for j in range(3):
                if self.table[i][j] == "_":
                    self.x_coord_empty_space=int(i)
                    self.y_coord_empty_space=int(j)
                    break


    def move_position(self,arg, x_coord, y_coord):
        if str(x_coord).isnumeric() != True or str(y_coord).isnumeric() != True:
            return False
        if int(x_coord) < 0 or int(x_coord) > 2:
            return False
        if int(y_coord) < 0 or int(x_coord) > 2:
            return False
        x_coord = int(x_coord)
        y_coord = int(y_coord)
        if self.table[x_coord][y_coord] != arg:
            return False
        if self.validate(move)==False:
            return False
        self.table[self.x_coord_empty_space][self.y_coord_empty_space]= arg
        self.table[x_coord][y_coord]=" _"
        self.x_coord_empty_space= x_coord
        self.y_coord_empty_space = y_coord
        return True


    def check_if_winner(self, x_coord,y_coord):
        x_coord=int(x_coord)
        y_coord=int(y_coord)
        arg = self.table[x_coord][y_coord]
        if x_coord == 0:
            if y_coord == 0:
              if self.table[x_coord][y_coord+1] == arg and self.table[x_coord][y_coord+2] == arg:
                  return True
              elif self.table[x_coord+1][y_coord] == arg and self.table[x_coord+2][y_coord] == arg:
                  return True
              elif self.table[x_coord+1][y_coord+1]== arg and self.table[x_coord+2][y_coord+2]== arg:
                  return True
            elif y_coord ==1:
                if self.table[x_coord][y_coord -1] == arg and self.table[x_coord][y_coord + 1] == arg:
                    return True
                elif self.table[x_coord + 1][y_coord] == arg and self.table[x_coord + 2][y_coord] == arg:
                    return True
            elif y_coord ==2:
                if self.table[x_coord][y_coord - 1] == arg and self.table[x_coord][y_coord - 2] == arg:
                    return True
                elif self.table[x_coord + 1][y_coord] == arg and self.table[x_coord + 2][y_coord] == arg:
                    return True
                elif self.table[x_coord + 1][y_coord -1] == arg and self.table[x_coord + 2][y_coord - 2] == arg:
                    return True
        elif x_coord==1:
            if y_coord == 0:
              if self.table[x_coord][y_coord+1] == arg and self.table[x_coord][y_coord+2] == arg:
                  return True
              elif self.table[x_coord+1][y_coord] == arg and self.table[x_coord-1][y_coord] == arg:
                  return True
            elif y_coord ==1:
                if self.table[x_coord][y_coord -1] == arg and self.table[x_coord][y_coord + 1] == arg:
                    return True
                elif self.table[x_coord + 1][y_coord] == arg and self.table[x_coord - 1][y_coord] == arg:
                    return True
                elif self.table[x_coord - 1][y_coord +1] == arg and self.table[x_coord +1][y_coord - 1] == arg:
                    return True
            elif y_coord ==2:
                if self.table[x_coord][y_coord - 1] == arg and self.table[x_coord][y_coord - 2] == arg:
                    return True
                elif self.table[x_coord + 1][y_coord] == arg and self.table[x_coord -1][y_coord] == arg:
                    return True
        elif x_coord == 2:
            if y_coord == 0:
                if self.table[x_coord][y_coord + 1] == arg and self.table[x_coord][y_coord + 2] == arg:
                    return True
                elif self.table[x_coord -1 ][y_coord] == arg and self.table[x_coord - 2][y_coord] == arg:
                    return True
                elif self.table[x_coord - 1][y_coord + 1] == arg and self.table[x_coord -2][y_coord +2] == arg:
                    return True
            elif y_coord == 1:
                if self.table[x_coord][y_coord - 1] == arg and self.table[x_coord][y_coord + 1] == arg:
                    return True
                elif self.table[x_coord -1][y_coord] == arg and self.table[x_coord - 2][y_coord] == arg:
                    return True

            elif y_coord == 2:
                if self.table[x_coord][y_coord - 1] == arg and self.table[x_coord][y_coord - 2] == arg:
                    return True
                elif self.table[x_coord - 1][y_coord] == arg and self.table[x_coord - 2][y_coord] == arg:
                    return True
                elif self.table[x_coord - 1][y_coord-1] == arg and self.table[x_coord - 2][y_coord-2] == arg:
                    return True
        else:
            return False





