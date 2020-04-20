package q004;

/**
 * Q004 ソートアルゴリズム
 * <p>
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 * <p>
 * 実装イメージ: ListManager data = new ListManager();
 * <p>
 * - ListManagerクラスを修正してはいけません - ListManagerクラスの dataList を直接変更してはいけません - ListManagerクラスの比較 compare
 * と入れ替え exchange を使って実現してください
 */
public class Q004 {

    private static final int ARR_SIZE = 100;

    public static void main(String[] args) {
        ListManager listManager = new ListManager();

        sort(listManager, 0).checkResult();
    }

    private static ListManager sort(ListManager listManager, int recentlySortedIndex) {
        for (int pivot = ARR_SIZE - 1; pivot > recentlySortedIndex; pivot--) {
            if (listManager.compare(pivot - 1, pivot) == 1) {
                listManager.exchange(pivot - 1, pivot);
            }
        }
        recentlySortedIndex++;

        if (recentlySortedIndex == ARR_SIZE - 1) {
            return listManager;
        }
        return sort(listManager, recentlySortedIndex);
    }

}
// 完成までの時間: 6分