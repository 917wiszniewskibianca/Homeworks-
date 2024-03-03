
print("*************** Welcome to BATTLESHIP! ***************")
from domain.HumanPlayer import HumanPlayer
from domain.ComputerPlayer import ComputerPlayer

class BattleshipGame:

    def __init__(self):
        self.players = [HumanPlayer("Player"), ComputerPlayer("The Computer")]
        self.players[0].set_opponent(self.players[1])
        self.players[1].set_opponent(self.players[0])

    def play(self):

        self.players[0].position_fleet()
        self.players[1].position_fleet()

        input("Both fleets are ready to play. Press enter to play... ")
        winner = False
        first_players_turn = True
        while not winner:
            if first_players_turn:
                winner = self.players[0].take_turn()
                if winner:
                    print("Game over!", self.players[0].player_name, "wins!")
            else:
                winner = self.players[1].take_turn()
                if winner:
                    print("Game over!", self.players[1].player_name, "wins!")

            first_players_turn = not first_players_turn

game = BattleshipGame()
game.play()