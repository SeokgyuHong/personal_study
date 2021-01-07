def solution(people, limit):
    answer = 0
    people.sort()
    stack = []
    cnt = 0
    weight = 0
    start = 0
    end = len(people)
    while (start <= end):
        if people[start] + people[end] <= limit:
            start += 1
            end -= 1
            answer += 1
        else:
            end -= 1
            answer += 1

    return answer

print(solution([70,50,80,50],100))