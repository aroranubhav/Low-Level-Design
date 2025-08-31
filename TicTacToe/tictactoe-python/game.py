class Game:
    def __init__(self, player1, player2, board):
        self.player1 = player1
        self.player2 = player2
        self.board = board
    
    def play(self):
        currTurn = 1
        gameCompleted = False

        while not gameCompleted:
            currPlayer = self.player1 if currTurn % 2 == 1 else self.player2
            print(f"{currPlayer.name}'s turn ({currPlayer.identifier})")

            try:
                x = int(input("Input x position of the move"))
                y = int(input("Input y position of the move"))
                result = self.board.place(currPlayer, x, y)

                if result is True:
                    print(f"{currPlayer.name} wins!")
                    gameCompleted = True
                elif result is None:
                    gameCompleted = True
                else:
                    currTurn += 1 
            
            except ValueError as e:
                print(e)
                continue   