#This function takes the adjacency matrix of an undirected graph and returns
# a minimum spanning tree of the graph.

import heapq
def MSTfunction(graph):
    weightDict = {}
    edgeDict = {}
    nodeQueue = [] #Priority Queue
    finalList = []
    visitedList = []
    for x in range(0, len(graph)):
        if(x is 0):
            weightDict[x] = -1
            edgeDict[x] = -1
            heapq.heappush(nodeQueue, (-1, x))
        else:
            weightDict[x] = float("inf")
            edgeDict[x] = "notConnected"
        visitedList.append(x)
    while(visitedList):
        a = heapq.heappop(nodeQueue)
        node = a[1]
        edgeList = graph[node]
        for z in range(0, len(edgeList)):
            adjNode = z
            edgeWeight = edgeList[adjNode]
            if(edgeWeight != -1 and (z in visitedList)):
                if(edgeWeight < weightDict[adjNode]):
                    weightDict[adjNode] = edgeWeight
                    edgeDict[adjNode] = node
                    heapq.heappush(nodeQueue, (edgeWeight, adjNode))
        if(node in visitedList):
            visitedList.remove(node)
    for b in range(0, len(graph)):
        finalList.append(edgeDict[b])
    return finalList
