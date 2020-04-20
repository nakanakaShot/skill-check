package q002;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Q002 並べ替える
 * <p>
 * dataListに "ID,名字" の形式で20個のデータがあります。 これをID順に並べて表示するプログラムを記述してください。
 * <p>
 * dataListの定義を変更してはいけません。
 * <p>
 * <p>
 * [出力結果イメージ] 1,伊藤 2,井上 （省略） 9,清水 10,鈴木 11,高橋 （省略） 20,渡辺
 */
public class Q002 {

    /**
     * データ一覧
     */
    private static final String[] dataList = {
        "8,佐藤",
        "10,鈴木",
        "11,高橋",
        "12,田中",
        "20,渡辺",
        "1,伊藤",
        "18,山本",
        "13,中村",
        "5,小林",
        "3,加藤",
        "19,吉田",
        "17,山田",
        "7,佐々木",
        "16,山口",
        "6,斉藤",
        "15,松本",
        "2,井上",
        "4,木村",
        "14,林",
        "9,清水"
    };

    public static void main(String[] args) {
        List<String> list = Arrays.asList(dataList);
        Map<Integer, String> map = new HashMap<>();

        list.forEach(str -> {
            String[] arr = str.split(",");
            map.put(Integer.parseInt(arr[0]), arr[1]);
        });

        Stream.of(map).forEach(System.out::println);
    }
}
// 完成までの時間: 5分20秒