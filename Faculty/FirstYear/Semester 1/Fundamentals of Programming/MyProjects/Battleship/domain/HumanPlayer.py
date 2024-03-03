from domain.Boat import Boat
from domain.Board import Board
class HumanPlayer:

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
        input(self.player_name+": Are you ready to position your fleet?  Press enter to begin! ")

        for boat in self.fleet:
            self.position_boat(boat)

        print("Your fleet is ready to play.  Your board is positioned as follows:")
        print(self.board)

    def position_boat(self, boat):
        print(self.board)
        print("You need to position a", boat.label, "of length", boat.size, "on the board above.")
        orientation = None
        while orientation is None:
            orientation = input("Would you like to use a vertical or horizontal orientation? (v/h) ")
            if (orientation != "v") and (orientation != "h"):
                print("You must enter a 'v' or a 'h'.  Please try again.")
                orientation = None
        position = None
        while position is None:
            try:
                position = input("Please enter the position for the top-left location of the boat. " + \
                                 " Use the form x,y (e.g., 1,3): ")
                coords = position.split(",")
                x = int(coords[0])
                y = int(coords[1])
                boat.set_orientation(orientation)
                boat.set_position(x,y)
                if not self.board.add_boat(boat):
                    raise Exception
            except ValueError:
                print("You must a valid position for the boat.  Please try again.")
                position = None
            except:
                print("You must choose a position that is  on the board and  doesn't intersect" + \
                      "with any other boats.")
                position = None

    def take_turn(self):
        print(self.player_name+"'s board:")
        print(self.board)
        print()
        print("Your view of "+self.opponent.player_name+"'s board:")
        print(self.opponent.board.get_public_view())

        print(self.player_name, "Statistics\nAttacks: ", self.log[0], "\tHits: ", self.log[1], "\tMisses: ", self.log[2])

        position = None
        while position is None:
            try:
                position = input("Please enter the position you would like to attack.  Use the form x,y (e.g., 1,3): ")
                coords = position.split(",")
                x = int(coords[0])
                y = int(coords[1])
                if (x < 0) or (x > 9) or (y < 0) or (y > 9):
                    raise Exception
                else:
                    break
            except:
                print("You must a valid position in the form x,y where both x and y are integers in the range of" + \
                      "0-9. Please try again.")
                position = None


        hit_flag = self.opponent.board.attack(x, y)
        self.log[0] += 1
        if hit_flag:
            self.log[1] += 1
            print("You hit a boat!")
        else:
            self.log[2] += 1
            print("You missed.")
        if self.opponent.board.is_defeated():
            return True
        else:
            return False
