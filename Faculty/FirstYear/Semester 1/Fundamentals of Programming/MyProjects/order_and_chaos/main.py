
from HumanPlayer import HumanPlayer
from ComputerPlayer import ComputerPlayer
from table import Table
class Game:
    def __init__(self):
        self.table=Table()
        self.players=[HumanPlayer("Human",self.table), ComputerPlayer("Computer",self.table)]
        self.players[0].set_oponent(self.players[1])
        self.players[1].set_oponent(self.players[0])


    def start_game(self):
        first_players_turn= True
        winner = False
        while winner == False:
           if int(self.players[0].number_of_pices) == 0 and int(self.players[1].number_of_pices) == 0:
              break

           if first_players_turn:
              if self.players[0].take_turn() is True:
                  winner = True
                  print("INFO: You won !!" )
           else:
               if self.players[1].take_turn() is True:
                   winner=True
                   print("INFO: The computer won !! ")
           first_players_turn = not first_players_turn

        if winner == False:
            print("The placement phase starts! sdMove your pieces in order to win!")
            self.table.find_empty_space()
            while winner != True:
                if first_players_turn:
                    self.players[0].change_position()
                    if self.table.check_if_winner is True:
                        winner = True
                        print("INFO: You won!!!")
                else:
                    winner= self.players[1].change_position()
                    if winner:
                        print("INFO: The computer won!!")
                first_players_turn = not first_players_turn
        else:
            exit()

game= Game()
game.start_game()


