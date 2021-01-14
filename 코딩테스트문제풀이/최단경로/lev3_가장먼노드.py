import heapq

q = []


def solution(n, edge):
    answer = 0
    INF = int(1e9)
    distance = [INF] * (n + 1)
    graph = [[] * (n + 1) for _ in range(n + 1)]

    for temp in edge:
        graph[temp[0]].append((temp[1], 1))
        graph[temp[1]].append((temp[0], 1))
    # vertex, 간선 길이 순서대로 대입
    # graph = (벌텍스,길이)
    distance[1] = 0
    heapq.heappush(q, (0, 1))  # 길이, 벌텍
    while q:
        dist, cur = heapq.heappop(q)
        if distance[cur] < dist:
            continue
        for temp in graph[cur]:
            cost = dist + temp[1]
            if cost < distance[temp[0]]:
                distance[temp[0]] = cost
                heapq.heappush(q, (cost, temp[0]))
    for i in range(0, len(distance)):
        if distance[i] == INF:
            distance[i] = 0
    max_num = max(distance)
    for i in range(1, len(distance)):
        if distance[i] == max_num:
            answer += 1

    return answer