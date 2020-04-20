package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import q005.model.WorkData;
import q005.model.WorkDataList;
import q005.model.WorkTimeSummary;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。 resources/q005/data.txt (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。 (1) 役職別の合計作業時間 (2) Pコード別の合計作業時間 (3) 社員番号別の合計作業時間 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。 また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ] 部長: xx時間xx分 課長: xx時間xx分 一般: xx時間xx分 Z-7-31100: xx時間xx分 I-7-31100: xx時間xx分 T-7-30002:
 * xx時間xx分 （省略） 194033: xx時間xx分 195052: xx時間xx分 195066: xx時間xx分 （省略）
 */
public class Q005 {

    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        WorkDataList list = fetchDataList();

        list.summarizeByPosition().forEach(Q005::printSummary);
        list.summarizeByPCode().forEach(Q005::printSummary);
        list.summarizeByEmployeeNumber().forEach(Q005::printSummary);
    }

    private static void printSummary(WorkTimeSummary summary) {
        System.out.println(summary.formatSummary());
    }

    private static WorkDataList fetchDataList() {
        InputStream is = openDataFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String resource;

        boolean isFirstLine = true;
        List<WorkData> list = new ArrayList<>();

        try {
            while ((resource = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String DELIM = ",";
                String[] values = resource.split(DELIM);

                list.add(WorkDataFactory.create(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4]
                ));
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        return new WorkDataList(list);
    }
}
// 完成までの時間: 65分