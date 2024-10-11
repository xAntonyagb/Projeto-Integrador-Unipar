package br.unipar.assetinsight.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class DataUtils {

    // --------------------------------------- Métodos de manipulação de data ---------------------------------------
    public static Timestamp getNow() {
        return Timestamp.from(
                LocalDateTime.now()
                        .atOffset(ZoneOffset.UTC)
                        .toInstant()
        );
    }

    public static String getFormatedNow() {
        Timestamp timestamp = Timestamp.from(
                LocalDateTime.now()
                        .atOffset(ZoneOffset.UTC)
                        .toInstant()
        );

        return formatarDataHora(timestamp);
    }


    // --------------------------------------- Métodos de manipulação de mes ---------------------------------------
    public static Month getMonth() {
        return LocalDateTime.now()
                .atOffset(ZoneOffset.UTC)
                .getMonth();
    }

    public static Month getMonth(LocalDateTime data) {
        return data.atOffset(ZoneOffset.UTC).getMonth();
    }


    // --------------------------------------- Métodos de formatação de data ---------------------------------------
    public static String formatarData(Timestamp data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public static String formatarDataHora(Timestamp data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(data);
    }

}
