
arr = [6,5,3,8,2,7] 
result = [] # 8,8,8,-1,7,-1

# While 배열 다 순회할 때까지
for i in range(len(arr)):
    a = arr[i]
    futurei=i
    l = len(arr)
    result.append(-1)
    while futurei < l: # 여기서 어케 스택을 써야할까..?        
        target = arr[futurei]
        if(target>a):
            result[i]=target
            break
        
        
        futurei+=1

print(result)

