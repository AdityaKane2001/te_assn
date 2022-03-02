from copy import deepcopy

def get_largest(subarr):
    return max(subarr)

def selection_sort(arr):
    sorted_arr = []
    unsorted_arr = deepcopy(arr)

    while len(unsorted_arr) != 0:
        largest = get_largest(unsorted_arr)
        sorted_arr.insert(0, largest)
        unsorted_arr.remove(largest)
    
    return sorted_arr

if __name__ == "__main__":
    # print()
    arr = list(map(int, input("Provide input array: ").split()))
    print()
    sorted_arr = selection_sort(arr)
    print(sorted_arr)