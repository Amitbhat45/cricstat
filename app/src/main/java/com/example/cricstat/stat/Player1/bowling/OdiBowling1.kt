package com.example.cricstat.stat.Player1.bowling

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)

fun Odibowling1(text: String, index: Int): Deferred<Pair<List<String>, List<String>>> {

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val deferredData = coroutineScope.async {
        try {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = currentDate.format(formatter)

            val doc: Document = Jsoup.connect("http://www.cricmetric.com/playerstats.py?player=${text.split(" ")[0]}+${text.split(" ")[1]}&role=bowler&format=ODI&groupby=year&playerStatsFilters=on&start_date=2002-01-01&end_date=$formattedDate&start_over=0&end_over=9999&max_innings=1000")
                .timeout(100000)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .get()

            val foot: Elements = doc.select("tfoot tr")
            val allYearStat = mutableListOf<String>()
            for (row in foot) {
                // Extract text from each table data (<td>) element within the row
                val rowValues = row.select("td").map { it.text() }
                allYearStat.addAll(rowValues)
            }

            // Find the table
            val tableRows: Elements = doc.select("tbody tr")
            val years = mutableListOf<String>()

            // Iterate through each row
            val dataByYear = mutableListOf<List<String>>() // List of Lists (arrays) to store data for each row

            for (row in tableRows) {
                val cells: Elements = row.select("td")
                val firstCell = row.select("td").firstOrNull()
                if (cells.isNotEmpty()) {
                    if (firstCell != null) {
                        years.add(firstCell.text())
                    }
                    val rowData = mutableListOf<String>() // List to store data for this row
                    for (cell in cells) {
                        rowData.add(cell.text()) // Add cell text to the row data list
                    }
                    dataByYear.add(rowData.toList())
                } else {
                    Log.d("cric", "Row ${row.text()} has no data cells (td)")
                }
            }
            dataByYear.add(0,allYearStat)
            years.add(0, "Years")


            val selectedData= dataByYear[index]

            return@async Pair(selectedData, years)

        } catch (e: Exception) {
            Log.e("CricmetricScraper", "Error scraping data", e)
            throw e // Rethrow the exception so the caller can handle it
        }
    }
    return deferredData
}