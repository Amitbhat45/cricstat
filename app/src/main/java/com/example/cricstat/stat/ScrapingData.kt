package com.example.cricstat.stat

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

@Composable
fun scarpingdata(){
    try {
        // Connect to the URL
        val doc: Document = Jsoup.connect("http://www.cricmetric.com/playerstats.py?player=V+Kohli&role=batsman&format=all&groupby=year&playerStatsFilters=on&start_date=2002-01-01&end_date=2024-04-26&tournament=ipl&start_over=0&end_over=9999&max_innings=1000").get()

        // Find the table
        val tableRows: Elements = doc.select("tbody tr")

        // Iterate through each row
        for (row in tableRows) {
            // Extract the data from each cell in the row
            val cells: Elements = row.select("td")
            val year = cells[0].text()
            val matches = cells[1].text()
            val runs = cells[2].text()
            val balls = cells[3].text()
            val fours = cells[4].text()
            val sixes = cells[5].text()
            val strikeRate = cells[6].text()
            val average = cells[7].text()
            val hundreds = cells[8].text()
            val fifties = cells[9].text()
            val ducks = cells[10].text()
            val notOuts = cells[11].text()
            val ballsFaced = cells[12].text()

            Log.d("CricmetricScraper", "Year: $year, Matches: $matches, Runs: $runs, Balls: $balls, Fours: $fours, Sixes: $sixes, Strike Rate: $strikeRate, Average: $average, " +
                    "Hundreds: $hundreds, Fifties: $fifties, Ducks: $ducks, Not Outs: $notOuts, Balls Faced: $ballsFaced")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
@Preview
@Composable
fun preeev(){
   scarpingdata()
}