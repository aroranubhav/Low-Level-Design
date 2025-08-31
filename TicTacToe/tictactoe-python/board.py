from collections import defaultdict

class Board:
    def __init__(self, size):
        self.reset(size)

    def reset(self, size):
        self.board = [['' for _ in range(size)] for _ in range(size)]
        self.rowCounts = defaultdict(lambda: defaultdict(int))
        self.colCounts = defaultdict(lambda: defaultdict(int))
        self.diagCounts = defaultdict(lambda: defaultdict(int))
        self.size = size
        self.currCounter = 0
    
    def place(self, player, x, y):
        identifier = player.identifier

        if not (0 <= x < self.size and 0 <= y < self.size):
            raise ValueError("Invalid move: out of bounds")

        if self.board[x][y] != '':
            raise ValueError("Invalid move: cell already occupied.")

        
        self.board[x][y] = identifier

        self.currCounter += 1
        
        #row 
        self.rowCounts[x][identifier] += 1
        
        if self.rowCounts[x][identifier] == self.size:
            return True
            
        #column
        self.colCounts[y][identifier] += 1

        if self.colCounts[y][identifier] == self.size:
            return True
            

        #diagonals

        #forward
        if x == y:
            self.diagCounts['forwards'][identifier] += 1
        
            if self.diagCounts['forwards'][identifier] == self.size:
                return True
            
        #backward
        if x + y == self.size - 1:
            self.diagCounts['backwards'][identifier] += 1
        
            if self.diagCounts['backwards'][identifier] == self.size:
                return True
            
        #tie check
        if self.currCounter == self.size ** 2:
            print("Game Tied!")
            return None
            
        return False