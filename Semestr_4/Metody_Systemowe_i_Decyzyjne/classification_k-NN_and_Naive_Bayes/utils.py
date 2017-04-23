def merge_sort(seq, pred):
    """Accepts a mutable sequence. Utilizes merge_sort to sort in place, return
    a sorted sequence"""
    if len(seq) == 1:
        return seq
    else:
        #recursion: break sequence down into chunks of 1
        mid = len(seq)//2
        left = merge_sort(seq[:mid], pred)
        right = merge_sort(seq[mid:], pred)

        i, j, k = 0, 0, 0 #i= left counter, j= right counter, k= master counter

        #run until left or right is out
        while i < len(left) and j < len(right):
            #if current left val is < current right val; assign to master list
            if pred(left[i], right[j]):
                seq[k] = left[i]
                i += 1; k += 1
            #else assign right to master
            else:
                seq[k] = right[j]
                j += 1; k += 1

        #handle remaining items in remaining list
        remaining = left if i < j else right
        r = i if remaining == left else j

        while r < len(remaining):
            seq[k] = remaining[r]
            r += 1; k += 1

        return seq

def predd(a, b):
    return a[0] <= b[0]

def unzip2(para):
    return para[1]

def unzip2_seq(seq):
    wyn = []
    for el in seq:
        wyn.append(unzip2(el))
    return wyn