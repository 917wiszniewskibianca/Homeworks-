from random import randint
class ComputerPlayer:
    def __init__(self, name,table):
        self.name=name
        self.table=table
        self.number_of_pices=4

    def set_oponent(self,oponent):
        self.oponent=oponent

    def take_turn(self):
        if self.number_of_pices != 0:
            self.number_of_pices -= 1
            position_set = False
            while position_set == False:
                try:
                    x_coord = randint(0, 2)
                    y_coord = randint(0, 2)
                    if self.table.set_argument('0', x_coord, y_coord):
                        position_set = True
                        return(self.table.check_if_winner(x_coord,y_coord))
                except ValueError as ve:
                    print(str(ve))

    def change_position(self):
        position_set = False
        while position_set == False:
            try:
                x_coord = randint(0, 2)
                y_coord = randint(0, 2)
                if self.table.move_position('0', x_coord, y_coord):
                    position_set = True
            except ValueError as ve:
                print(str(ve))