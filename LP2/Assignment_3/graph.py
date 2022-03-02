class Graph:
    def __init__(self, num_vertices, name="my_graph"):
        self.graph = []
        self.num_vertices = num_vertices
        self.name = name
    
    def add_edge(self, start, end, weight):
        if (start, end) in self:
            raise ValueError(f"Edge ({start}, {end}) already in graph.")
        elif start >= self.num_vertices or end >= self.num_vertices or start < 0 or end <= 0 or weight <=0:
            raise ValueError(f"Please check inputs. recvd: {start}, {end}, {weight}")
        else:
            self.graph.append((start, end, weight))
        return True
    
    def get_edge(self, start, end):
        for edge in self.graph:
            if edge[0] == start and edge[1] == end:
                return edge, self.graph.index(edge)
        print("W: Edge not found")
        return (-1,-1,-1), -1

    def remove_edge(self, start, end):
        _, idx = self.get_edge(start, end)
        self.graph.remove(idx)
        return True

    def get_edges_on_vertex(self, start):
        edges = []
        for edge in self.graph:
            if edge[0] == start or edge[1] == start:
                edges.append(edge)
        return edges


    def check_cycle(self):
        traversed = []
        for edge in self.graph:
            traversed.append(edge[1])


    def __contains__(self, key):
        if key in self.edges:
            return True
        return False