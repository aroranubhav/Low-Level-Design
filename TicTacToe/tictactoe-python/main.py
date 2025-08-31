from player import Player
from board import Board
from game import Game

if __name__ == "__main__":
    p1 = Player("Al", "X")
    p2 = Player("Maxi", "0")

    board = Board(3)

    game = Game(p1, p2, board)
    game.play()