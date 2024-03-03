class HumanPlayer:
    def __init__(self,name,table):
        self.name= name
        self.table=table
        self.number_of_pices=4

    def set_oponent(self,oponent):
        self.oponent=oponent

    def take_turn(self):
      print(self.table)
      if self.number_of_pices !=0:
        self.number_of_pices -=1
        position_set = False
        while position_set ==  False:
          try:
            x_coord = input("Enter the x coordinate of where you want to move: ")
            y_coord = input("Enter the y coord of where you want to move: ")
            if not self.table.set_argument('x',x_coord,y_coord):
                raise ValueError("The coordinates you entered are incorrect!"
                                 "The values you enter should be integers!"
                                 "You can make your move only on the board and on the empy spaces!")
            else:
                position_set = True
                return self.table.check_if_winner(x_coord, y_coord)
          except ValueError as ve:
              print(str(ve))


    def change_position(self):
        print(self.table)
        position_set = False
        while position_set == False:
            try:
                x_coord = input("Enter the x coordinate of where you want to move: ")
                y_coord = input("Enter the y coord of where you want to move: ")
                if not self.table.move_position('x', x_coord, y_coord):
                    raise ValueError("The coordinates you entered are incorrect!"
                                     "You can change only the place of the elements near to the empty space"
                                     "The values you enter should be integers!"
                                     "You can make your move only on the board and on the emtpy spaces!")
                else :
                    position_set = True
            except ValueError as ve:
                print(str(ve))






