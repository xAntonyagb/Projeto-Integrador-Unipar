package br.unipar.assetinsight.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DataUtils {

    public static Timestamp getNow() {
        return Timestamp.from(
                LocalDateTime.now()
                        .atOffset(ZoneOffset.of("-03:00"))
                        .toInstant()
        );
    }

    public static String getFormatedNow() {
        Timestamp timestamp = Timestamp.from(
                LocalDateTime.now()
                        .atOffset(ZoneOffset.of("-03:00"))
                        .toInstant()
        );

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(timestamp);
    }

}
