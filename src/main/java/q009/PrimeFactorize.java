package q009;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

class PrimeFactorize {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final BigDecimal MULTIPLY_TO_HALF = BigDecimal.valueOf(0.5);

    static Set<BigDecimal> factorize(BigDecimal value) {
        Set<BigDecimal> canDivide = new HashSet<>();
        BigDecimal halfOf = value.multiply(MULTIPLY_TO_HALF);
        BigDecimal divided = value;

        while (!divided.equals(BigDecimal.ZERO) && !divided.equals(BigDecimal.ONE)) {

            if (canDivide(divided, TWO)) {
                canDivide.add(TWO);
                divided = divided.divide(TWO, RoundingMode.UNNECESSARY);
                continue;
            }

            for (long cnt = 3; cnt <= halfOf.longValue(); cnt += 2) {
                BigDecimal divider = BigDecimal.valueOf(cnt);
                if (canDivide(divided, divider)) {
                    canDivide.add(divider);
                    divided = divided.divide(divider, RoundingMode.UNNECESSARY);
                    break;
                }
            }

            canDivide.add(divided);
            divided = divided.divide(divided, RoundingMode.UNNECESSARY);
        }
        return canDivide.stream().sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static boolean canDivide(BigDecimal target, BigDecimal divider) {
        return target.divideAndRemainder(divider)[1].equals(BigDecimal.ZERO);
    }
}
