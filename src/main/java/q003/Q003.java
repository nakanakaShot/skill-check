package q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Q003 集計と並べ替え
 * <p>
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。 resources/q003/data.txt 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う） - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語） -
 * アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 * <p>
 * 出力形式:単語=数
 * <p>
 * [出力イメージ] （省略） highest=1 I=3 if=2 ignorance=1 （省略）
 * <p>
 * 参考 http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {

    /**
     * データファイルを開く resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        InputStream is = Q003.openDataFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String resource;
        Map<String, Integer> map = new HashMap<>();

        try {
            while ((resource = br.readLine()) != null) {
                String DELIM = " ";
                Arrays.asList(resource.split(DELIM)).forEach(
                    word -> {
                        word = excludeSpecialChar(word);

                        if (isOnlySpecialChar(word)) {
                            return;
                        }

                        if (map.containsKey(word)) {
                            map.put(word, map.get(word) + 1);
                        } else {
                            map.put(word, 1);
                        }
                    }
                );
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }

        map.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .forEach(System.out::println);

    }

    private static String excludeSpecialChar(String str) {
        return str
            .replace(".", "")
            .replace("–", "")
            .replace(",", "");
    }

    private static boolean isOnlySpecialChar(String str) {
        return str.matches("-");
    }
}
// 完成までの時間: 28分