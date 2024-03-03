from domain.Boat import Boat
from domain.Board import Board
import random
class ComputerPlayer:
    def __init__(self, player_name):
        self.player_name = player_name
        self.board = Board()
        self.fleet = [Boat("Aircraft Carrier", 5), Boat("Battleship", 4), Boat("Submarine", 3), Boat("Destroyer", 3),
                      Boat("Patrol Boat", 2)]
        self.opponent = None
        self.log = [0,0,0]

    def set_opponent(self, opponent):
        self.opponent = opponent

    def position_fleet(self):
        for boat in self.fleet:
            self.position_boat(boat)
        input("The Computer's fleet is ready to play.  Press enter to continue...")


    def position_boat(self, boat):
        position = False
        while position == False:
            random_number = random.randint(0, 1)
            if random_number== 0:
                orientation = "v"
            else:
                orientation = "h"

            x = random.randint(1, 10) - 1
            y = random.randint(1, 10) - 1

            boat.set_orientation(orientation)
            boat.set_position(x,y)

            result = self.board.add_boat(boat)
            if result == True:
                position = True

    def take_turn(self):
        x = random.randint(1, 10) - 1
        y = random.randint(1, 10) - 1

        print(self.player_name, "Statistics\nAttacks: ", self.log[0], "\tHits: ", self.log[1], "\tMisses: ",
              self.log[2])

        hit_flag = self.opponent.board.attack(x, y)
        self.log[0] += 1
        if hit_flag:
            self.log[1] += 1
            print("\nThe Computer hit a boat!")
        else:
            self.log[2] += 1
            print("\nThe Computer missed.")

        if self.opponent.board.is_defeated():
            return True
        else:
            return False