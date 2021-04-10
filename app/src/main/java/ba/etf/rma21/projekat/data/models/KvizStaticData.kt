package ba.etf.rma21.projekat.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
private fun localDateToDate(dan: Int, mjesec: Int, godina: Int, sati: Int, minute: Int, sekunde: Int = 0): Date {
    val datum = LocalDateTime.of(godina, mjesec, dan, sati, minute, sekunde)
    return Date.from(datum.atZone(ZoneId.systemDefault()).toInstant())
}

@RequiresApi(Build.VERSION_CODES.O)
fun kvizovi(): List<Kviz> {
    return listOf(
            Kviz("Kviz 0", "RMA", localDateToDate(8, 3, 2021, 9, 20), localDateToDate(10, 3, 2021, 15, 50), null, 2, "RMA-Grupa1", null),
            Kviz("Kviz 1", "RMA", localDateToDate(8, 4, 2021, 9, 30), localDateToDate(15, 4, 2021, 10, 30), localDateToDate(10, 4, 2021, 12, 0), 2, "RMA-Grupa2", 1.5F),
            Kviz("Kviz 2", "RMA", localDateToDate(8, 4, 2021, 14, 0), localDateToDate(15, 4, 2021, 14, 0), null, 2, "RMA-Grupa3", null),

            Kviz("Kviz 1", "DM", localDateToDate(9, 3, 2021, 8, 15), localDateToDate(11, 3, 2021, 16, 20), localDateToDate(10, 3, 2021, 9, 20), 5, "DM-Grupa1", 2.5F),
            Kviz("Kviz 2", "DM", localDateToDate(10, 5, 2021, 12, 10), localDateToDate(12, 5, 2021, 12, 10), null, 5, "DM-Grupa2", null),

            Kviz("Kviz 1", "IM", localDateToDate(10, 5, 2021, 9, 0), localDateToDate(11, 5, 2021, 16, 30), null, 3, "IM-Grupa1", null),
            Kviz("Kviz 1", "IM", localDateToDate(7, 4, 2021, 10, 0), localDateToDate(9, 4, 2021, 11, 30), null, 3, "IM-Grupa2", null),

            Kviz("Kviz 1", "AFJ", localDateToDate(6, 2, 2021, 9, 0), localDateToDate(8, 2, 2021, 14, 10), localDateToDate(7, 2, 2021, 11, 20), 4, "AFJ-Grupa1", 1.4F),
            Kviz("Kviz 2", "AFJ", localDateToDate(22, 4, 2021, 8, 15), localDateToDate(24, 4, 2021, 16, 20), null, 4, "AFJ-Grupa2", null))
}
