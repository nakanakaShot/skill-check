package q009;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 * Q009 重い処理を別スレッドで実行
 *
 * 標準入力から整数を受け取り、別スレッドで素因数分解して、その整数を構成する2以上の素数を求めるプログラムを記述してください。
 * - 素因数分解は重い処理であるため、別スレッドで実行してください
 * - 標準入力から整数を受け取った後は、再度標準入力に戻ります
 * - 空文字が入力された場合は、素因数分解を実行中の状態を表示します（「実行中」あるいは処理結果を表示）
 * - 素因数分解の効率的なアルゴリズムを求めるのが問題ではないため、あえて単純なアルゴリズムで良い（遅いほどよい）
 *   （例えば、2および3以上の奇数で割り切れるかを全部チェックする方法でも良い）
 * - BigIntegerなどを使って、大きな数値も扱えるようにしてください
 * </pre>
 * <pre>
 * [実行イメージ]
 * 入力) 65536
 * 入力)
 * 65536: 実行中  <-- もし65536の素因数分解が実行中だった場合はこのように表示する
 * 入力) 12345
 * 入力)
 * 65536: 2      <-- 実行が終わっていたら結果を表示する。2の16乗だが、16乗の部分の表示は不要（複数溜まっていた場合の順番は問わない）
 * 12345: 実行中
 * 入力)
 * 12345: 3,5,823
 * </pre>
 */
public class Q009 {

    public static void main(String[] args) {

        Map<BigDecimal, CompletableFuture> executors = new HashMap<>();

        while (true) {
            Optional<BigDecimal> input = readIntegerFromStd();

            if (!input.isPresent()) {
                printStatus(executors);
                continue;
            }

            if (input.get().equals(BigDecimal.ZERO)) {
                if (confirmExit()) {
                    break;
                }
                continue;
            }

            executors.put(input.get(), awaitPrimeFactorize(input.get()));
        }
        System.out.println("終了中...");
        executors.values().forEach(CompletableFuture::join);

        System.out.println("実行結果");
        printStatus(executors);
    }

    private static CompletableFuture<Set<BigDecimal>> awaitPrimeFactorize(BigDecimal target) {
        return CompletableFuture
            .supplyAsync(() -> PrimeFactorize.factorize(target))
            .thenApply(bigDecimals -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return bigDecimals;
            });
    }

    private static void printStatus(Map<BigDecimal, CompletableFuture> map) {
        map.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
            .forEach(entry -> printStatus(entry.getKey(), entry.getValue()));
    }

    private static void printStatus(BigDecimal value, CompletableFuture cf) {
        String status = cf.isDone() ? cf.join().toString() : "実行中";
        System.out.println(value + ": " + status);
    }

    private static Optional<BigDecimal> readIntegerFromStd() {
        Scanner sc = new Scanner(System.in);
        System.out.println("■素因数分解する整数を入力してください。(整数: 処理実行, 0: 終了)");

        String input = sc.nextLine();

        if (input.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(
                BigDecimal.valueOf(Long.parseLong(input))
            );
        } catch (NumberFormatException ex) {
            throw new RuntimeException();
        }
    }

    private static boolean confirmExit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("処理を終了しますか？(y/N)");
        String input = sc.nextLine();

        return "y".equals(input.toLowerCase());
    }
}
// 完成までの時間: 1時間 20分