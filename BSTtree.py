#This takes a start node from a graph in an adjacency list format. It returns a list
#that shows the minimum distance each node in the graph is from the start node. It uses
#a breadth-first search algorithm to explore the tree. 
def function(start_node, graph):
    from collections import deque
    finalList = []
    distanceDict = {}
    parentDict = {}
    queue = deque([])
    for n in range (0, len(graph)):
        distanceDict[n] = "infinity"
        parentDict[n] = "none"
    distanceDict[start_node] = 0
    queue.append(start_node)
    while queue:
        currentNode = queue.popleft()
        for thing in graph[currentNode]:
            if distanceDict[thing] is "infinity":
                distanceDict[thing] = distanceDict[currentNode] + 1
                parentDict[thing] = currentNode
                queue.append(thing)
    for number in range(0, len(graph)):
        if(distanceDict[number] is "infinity"):
            finalList.append(-1)
        else:
            finalList.append(distanceDict[number])
    return finalList