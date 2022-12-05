import java.util.List;

public class HeapSort {
    public static int[] doHeapSort(int[] _arr) {
        heapSort(_arr);
        return _arr;
    }

    public static List<CosSimilarityPair> doHeapSort(List<CosSimilarityPair> _list) {
        //TODO Task 4.1
        return null;
    }

    private static void heapSort(int[] _arr) {
        int n = _arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(_arr, i, n);

        for (int j = n - 1; j > 0; j--) {
            int temp = _arr[0];
            _arr[0] = _arr[j];
            _arr[j] = temp;

            heapify(_arr, 0, j);
        }
    }

    private static void heapSort(List<CosSimilarityPair> _list) {
        //TODO Task 4.2
    }

    private static void heapify(int[] _tree, int _rootindex, int _n) {
        while (2 * _rootindex + 2 <= _n) {
            int crIndex = 2 * _rootindex + 2;  //Index of the right child
            int clIndex = 2 * _rootindex + 1;  //Index of the left child
            int smallest = clIndex;

            if (crIndex < _n && _tree[clIndex] > _tree[crIndex])
                smallest = crIndex;
            if (_tree[_rootindex] <= _tree[smallest])
                break;

            //Swap the root with the largest node.
            int temp = _tree[_rootindex];
            _tree[_rootindex] = _tree[smallest];
            _tree[smallest] = temp;
            _rootindex = smallest;
        }
    }

    private static void heapify(List<CosSimilarityPair> _tree, int _rootindex, int _n) {
        //TODO Task 4.3
    }
}
