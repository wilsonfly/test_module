
def fibs(num):
    result=[0,1]
    for i in range(2,num):
        result.append(result[i-2]+result[i-1])
    return result

def fibs_no2(num):
    result=[0,1]
    for i in range(num-2):
        result.append(result[-2]+result[-1])
    return result


print fibs(10)
print fibs_no2(10)
