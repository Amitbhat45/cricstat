package com.example.cricstat.stat

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@Composable
fun getinfo() :
        Deferred<Pair<String, List<String>>>{
    return remember {
        CoroutineScope(Dispatchers.IO).async {
            try {
                val doc: Document = Jsoup.connect("http://www.cricmetric.com/playerstats.py?player=Virat+Kohli&role=all&format=all&groupby=player")
                    .timeout(100000)
                    .get()
                val infocontainer=doc.select("div.panel-body").first()
                /*if (infocontainer.isEmpty()) {
                    println("Target element not found")
                }*/
                val infoList = mutableListOf<String>()
                val boldElements = infocontainer?.select("b")

                if (boldElements != null) {
                    for (element in boldElements) {
                        val nextSibling = element.nextSibling().toString().split("<br>")[0].trim() // Get the text node after the bold element
                        if (nextSibling != null) {
                            infoList.add(nextSibling.toString()) // Add trimmed text to the list
                        }
                    }
                }
                val panelHeadingElement = doc.select("div.panel-heading").first()
                val name = panelHeadingElement?.text() ?: "Not found"
                withContext(Dispatchers.Main) {
                    Log.d("playerinfo","$name")
                }
                withContext(Dispatchers.Main) {
                   Log.d("playerinfo","$infoList")
                }
                return@async Pair(name,infoList)
            } catch (e: Exception) {
                Log.e("CricmetricScraper", "Error scraping data", e)
                throw e
            }
        }
    }
}

@Preview
@Composable
fun pp1(){
    getinfo()
}