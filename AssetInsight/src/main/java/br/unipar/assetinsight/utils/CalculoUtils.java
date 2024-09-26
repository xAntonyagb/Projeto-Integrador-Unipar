package br.unipar.assetinsight.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoUtils {

    public static double arredondar(double valor, int casas) {
        BigDecimal bd = new BigDecimal(Double.toString(valor));
        bd = bd.setScale(casas, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
