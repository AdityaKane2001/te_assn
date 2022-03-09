class Graph:
    def __init__(self, n):
        self.n = n
        self.adj_mat = []
    
    def addEdge(self, edge):
        self.adj_mat.append(edge)
    
    def kruskal_mst(self):
        
        sorted_edges = sorted(self.adj_mat, key=lambda x: x[-1])
        print(sorted_edges)

        parent = [-1] * self.n
        rank = [0] * self.n
        in_mst = [False] * self.n

        for _ in range(self.n - 1):
            


g = Graph(4)
g.addEdge((0, 1, 10))
g.addEdge((0, 2, 6))
g.addEdge((0, 3, 5))
g.addEdge((1, 3, 15))
g.addEdge((2, 3, 4))

g.kruskal_mst()