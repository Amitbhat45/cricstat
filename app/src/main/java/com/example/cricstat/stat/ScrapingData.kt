package com.example.cricstat.stat

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.net.SocketTimeoutException

@Composable
 fun scrapingData(text:String): Deferred<List<String>>{

     // val texts=text.split(" ")
    // Launch a coroutine in the IO dispatcher
    return remember {
        CoroutineScope(Dispatchers.IO).async {
            try {
                // Connect to the URL
                val doc: Document = Jsoup.connect("http://www.cricmetric.com/playerstats.py?player=${text.split(" ")[0]}+${text.split(" ")[1]}&role=batsman&format=all&groupby=year&playerStatsFilters=on&start_date=2002-01-01&end_date=2024-04-26&tournament=ipl&start_over=0&end_over=9999&max_innings=1000")
                    .timeout(100000) // Timeout in milliseconds
                    .get()

                // Find the table
                val tableRows: Elements = doc.select("tbody tr")

                // Iterate through each row
                val dataByYear = mutableListOf<List<String>>() // List of Lists (arrays) to store data for each row

                for (row in tableRows) {
                    val cells: Elements = row.select("td")
                    if (cells.isNotEmpty()) {
                        val rowData = mutableListOf<String>() // List to store data for this row
                        for (cell in cells) {
                            rowData.add(cell.text()) // Add cell text to the row data list
                        }
                        dataByYear.add(rowData.toList())
                    } else {
                        Log.d("cric", "Row ${row.text()} has no data cells (td)")
                    }
                }

                // Log the data
                withContext(Dispatchers.Main) {
                    val firstRow = dataByYear[4]
                    val runs = firstRow[1]
                    Log.d("CricmetricScraper", "Year: $firstRow ,runs $runs")
                }
                val firstRow = dataByYear[4]
                return@async firstRow

            } catch (e: Exception) {
                Log.e("CricmetricScraper", "Error scraping data", e)
                throw e // Rethrow the exception so the caller can handle it
            }
        }
    }
}


@Preview
@Composable
fun preeev(){
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        val text="Virat Kohli"
       // scrapingData(text)
    }
}