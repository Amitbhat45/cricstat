package com.example.cricstat.stat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.cricstat.stat.Player1.batting.OdiData1
import com.example.cricstat.stat.Player1.batting.T20Data1
import com.example.cricstat.stat.Player1.batting.TestData1
import com.example.cricstat.stat.Player1.getinfo

import com.example.cricstat.stat.Player1.batting.scrapingData1
import com.example.cricstat.stat.Player1.bowling.Iplbowling1
import com.example.cricstat.stat.Player1.bowling.Odibowling1
import com.example.cricstat.stat.Player1.bowling.T20bowling1
import com.example.cricstat.stat.Player1.bowling.Testbowling1
import com.example.cricstat.stat.Player2.batting.OdiData2
import com.example.cricstat.stat.Player2.batting.T20Data2
import com.example.cricstat.stat.Player2.batting.TestData2
import com.example.cricstat.stat.Player2.batting.bowling.Iplbowling2
import com.example.cricstat.stat.Player2.batting.bowling.Odibowling2
import com.example.cricstat.stat.Player2.batting.bowling.T20bowling2
import com.example.cricstat.stat.Player2.batting.bowling.Testbowling2
import com.example.cricstat.stat.Player2.batting.scrapingData2

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplayPlayerStats(player1:String,player2:String) {
  Column {

      val dataByYear = remember { mutableStateOf<List<String>?>(null) }
      val years1 = remember { mutableStateOf<List<String>?>(null) }

      val dataByYear2 = remember { mutableStateOf<List<String>?>(null) }
      val years2 = remember { mutableStateOf<List<String>?>(null) }

      val dataByYearbowling = remember { mutableStateOf<List<String>?>(null) }
      val years1bowling = remember { mutableStateOf<List<String>?>(null) }

      val dataByYear2bowling = remember { mutableStateOf<List<String>?>(null) }
      val years2bowling = remember { mutableStateOf<List<String>?>(null) }



      val list = listOf("Test", "Odi", "T20I", "Ipl")
      val defaultSelected = "Test"
      val modifier = Modifier
      val color = Color(0xFF8fcce3)

      var selectedIndex by remember { mutableStateOf(list.indexOf(defaultSelected)) }
      var expand by remember { mutableStateOf(false) }
      var stroke by remember { mutableStateOf(1) }

      val list1 = years1.value
      val bowlinglist1=years1bowling.value
      // val size=years1?.value?.size
      val defaultSelected1 = "Years"
      val modifier1 = Modifier
      val color1 = Color(0xFF8fcce3)
      var selectedIndex1 by remember { mutableStateOf(list1?.indexOf(defaultSelected1)) }
      var expand1 by remember { mutableStateOf(false) }
      var stroke1 by remember { mutableStateOf(1) }

      val list2 = listOf("Test", "Odi", "T20I", "Ipl")
      val defaultSelected2 = "Test"
      var selectedIndex2 by remember { mutableStateOf(list.indexOf(defaultSelected)) }
      var expand2 by remember { mutableStateOf(false) }
      var stroke2 by remember { mutableStateOf(1) }

      val list3 = years2.value
      val bowlinglist2=years2bowling.value
      // val size=years1?.value?.size
      val defaultSelected3 = "Years"
      var selectedIndex3 by remember { mutableStateOf(list1?.indexOf(defaultSelected1)) }
      var expand3 by remember { mutableStateOf(false) }
      var stroke3 by remember { mutableStateOf(1) }
Row {


    Box(
        modifier
            .padding(start = 18.dp)
            .width(70.dp)
            .height(45.dp)
            .border(
                border = BorderStroke(stroke.dp, color),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                expand = true
                stroke = if (expand) 2 else 1
            },
          contentAlignment = Alignment.Center
      ) {

          Text(
              text = list[selectedIndex],
              color = color,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
          )

          DropdownMenu(
              expanded = expand,
              onDismissRequest = {
                  expand = false
                  stroke = if (expand) 2 else 1
              },
              properties = PopupProperties(
                  focusable = false,
                  dismissOnBackPress = true,
                  dismissOnClickOutside = true,
              ),
              modifier = Modifier
                  .background(Color(0xFF171825))
                  .padding(2.dp)
                  .fillMaxWidth(.4f)
          ) {
              list.forEachIndexed { index, item ->
                  DropdownMenuItem(
                      text = {
                          Text(
                              text = item,
                              color = color,
                              textAlign = TextAlign.Center,
                              modifier = Modifier.fillMaxWidth()
                          )
                      },
                      onClick = {
                          selectedIndex = index
                          expand = false
                          stroke = if (expand) 2 else 1
                          selectedIndex1=0
                          //onSelected(selectedIndex)
                      }
                  )
              }
          }

      }
    Spacer(modifier = Modifier.width(5.dp))
    Box(
            modifier
                //.padding(start = 18.dp)
                .width(70.dp)
                .height(45.dp)
                .border(
                    border = BorderStroke(stroke1.dp, color),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    expand1 = true
                    stroke1 = if (expand1) 2 else 1
                },
            contentAlignment = Alignment.Center
        ) {


            (selectedIndex1?.let { list1?.get(it) } ?: null)?.let {
                Text(
                    text = it,
                    color = color,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }


            DropdownMenu(
                expanded = expand1,
                onDismissRequest = {
                    expand1 = false
                    stroke1 = if (expand) 2 else 1
                },
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
                modifier = Modifier
                    .background(Color(0xFF171825))
                    .padding(2.dp)
                    .fillMaxWidth(.4f)
            ) {
                val dropdownList = if (selectedButtonState == "batting") {
                    list1 ?: emptyList()
                } else {
                    bowlinglist1 ?: emptyList()
                }
                dropdownList?.forEachIndexed { index1, item1 ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item1,
                                color = color,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedIndex1 = index1
                            expand1 = false
                            stroke1 = if (expand) 2 else 1
                            //onSelected(selectedIndex)
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.width(56.dp))
    Box(
            modifier
                //.padding(start = 18.dp)
                .width(70.dp)
                .height(45.dp)
                .border(
                    border = BorderStroke(stroke2.dp, color),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    expand2 = true
                    stroke2 = if (expand) 2 else 1

                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = list2[selectedIndex2],
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            DropdownMenu(
                expanded = expand2,
                onDismissRequest = {
                    expand2 = false
                    stroke2 = if (expand) 2 else 1
                },
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
                modifier = Modifier
                    .background(Color(0xFF171825))
                    .padding(2.dp)
                    .fillMaxWidth(.4f)
            ) {
                list2.forEachIndexed { index2, item2 ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item2,
                                color = color,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedIndex2 = index2
                            expand2 = false
                            stroke2 = if (expand2) 2 else 1
                            selectedIndex3 = 0
                            //onSelected(selectedIndex)
                        }
                    )
                }
            }

        }
    Spacer(modifier = Modifier.width(5.dp))
    Box(
        modifier
            //.padding(start = 18.dp)
            .width(70.dp)
            .height(45.dp)
            .border(
                border = BorderStroke(stroke3.dp, color),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                expand3 = true
                stroke3 = if (expand3) 2 else 1
            },
        contentAlignment = Alignment.Center
    ) {


        (selectedIndex3?.let { list3?.get(it) } ?: null)?.let {
            Text(
                text = it,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }


        DropdownMenu(
            expanded = expand3,
            onDismissRequest = {
                expand3 = false
                stroke3 = if (expand3) 2 else 1
            },
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            modifier = Modifier
                .background(Color(0xFF171825))
                .padding(2.dp)
                .fillMaxWidth(.4f)
        ) {
            val dropdownList2 = if (selectedButtonState == "batting") {
                list3 ?: emptyList()
            } else {
                bowlinglist2 ?: emptyList()
            }
            dropdownList2?.forEachIndexed { index3, item3 ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item3,
                            color = color,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        selectedIndex3 = index3
                        expand3= false
                        stroke3 = if (expand3) 2 else 1
                        //onSelected(selectedIndex)
                    }
                )
            }
        }

    }}

        if (selectedIndex == 0) {

            LaunchedEffect(selectedIndex1) {
                val deferredData = TestData1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredData?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYear.value = fetchedPlayerYearbyData
                years1.value = fetchedAlltimeData
            }
            LaunchedEffect(selectedIndex1) {
                val deferredDatabowling = Testbowling1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredDatabowling?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYearbowling.value = fetchedPlayerYearbyData
                years1bowling.value = fetchedAlltimeData
            }
        } else if (selectedIndex == 1) {

            LaunchedEffect(selectedIndex1) {
                val deferredData = OdiData1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredData?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYear.value = fetchedPlayerYearbyData
                years1.value = fetchedAlltimeData
            }
            LaunchedEffect(selectedIndex1) {
                val deferredDatabowling = Odibowling1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredDatabowling?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYearbowling.value = fetchedPlayerYearbyData
                years1bowling.value = fetchedAlltimeData
            }
        } else if (selectedIndex == 2) {

            LaunchedEffect(selectedIndex1) {
                val deferredData = T20Data1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredData?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYear.value = fetchedPlayerYearbyData
                years1.value = fetchedAlltimeData
            }
            LaunchedEffect(selectedIndex1) {
                val deferredDatabowling = T20bowling1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredDatabowling?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYearbowling.value = fetchedPlayerYearbyData
                years1bowling.value = fetchedAlltimeData
            }
        } else if (selectedIndex == 3) {

            LaunchedEffect(selectedIndex1) {
                val deferredData = scrapingData1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredData?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYear.value = fetchedPlayerYearbyData
                years1.value = fetchedAlltimeData
            }
            LaunchedEffect(selectedIndex1) {
                val deferredDatabowling = Iplbowling1(text = player1, index = selectedIndex1 ?: 0)

                // Handle potential null deferredData gracefully
                val (fetchedPlayerYearbyData, fetchedAlltimeData) = try {
                    deferredDatabowling?.await() ?: Pair(emptyList(), emptyList())
                } catch (e: Exception) {
                    // Handle exceptions from deferredData.await() appropriately
                    // (e.g., log the error, display a user-friendly message)
                    emptyList<String>() to emptyList<String>()
                }

                dataByYearbowling.value = fetchedPlayerYearbyData
                years1bowling.value = fetchedAlltimeData
            }
        }

      if (selectedIndex2 == 0) {

          LaunchedEffect(selectedIndex3) {
              val deferredData2 = TestData2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2.value = fetchedPlayerYearbyData2
              years2.value = fetchedAlltimeData2
          }
          LaunchedEffect(selectedIndex3) {
              val deferredData2bowling = Testbowling2(text = player2, index = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2bowling?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2bowling.value = fetchedPlayerYearbyData2
              years2bowling.value = fetchedAlltimeData2
          }
      } else if (selectedIndex2 == 1) {

          LaunchedEffect(selectedIndex3) {
              val deferredData2 = OdiData2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2.value = fetchedPlayerYearbyData2
              years2.value = fetchedAlltimeData2
          }
          LaunchedEffect(selectedIndex3) {
              val deferredData2bowling = Odibowling2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2bowling?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2bowling.value = fetchedPlayerYearbyData2
              years2bowling.value = fetchedAlltimeData2
          }
      } else if (selectedIndex2 == 2) {

          LaunchedEffect(selectedIndex3) {
              val deferredData2 = T20Data2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2.value = fetchedPlayerYearbyData2
              years2.value = fetchedAlltimeData2
          }
          LaunchedEffect(selectedIndex3) {
              val deferredData2bowling = T20bowling2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2bowling?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2bowling.value = fetchedPlayerYearbyData2
              years2bowling.value = fetchedAlltimeData2
          }
      } else if (selectedIndex2 == 3) {

          LaunchedEffect(selectedIndex3) {
              val deferredData2 = scrapingData2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2.value = fetchedPlayerYearbyData2
              years2.value = fetchedAlltimeData2
          }
          LaunchedEffect(selectedIndex3) {
              val deferredData2bowling = Iplbowling2(text = player2, index2 = selectedIndex3 ?: 0)

              // Handle potential null deferredData gracefully
              val (fetchedPlayerYearbyData2, fetchedAlltimeData2) = try {
                  deferredData2bowling?.await() ?: Pair(emptyList(), emptyList())
              } catch (e: Exception) {
                  // Handle exceptions from deferredData.await() appropriately
                  // (e.g., log the error, display a user-friendly message)
                  emptyList<String>() to emptyList<String>()
              }

              dataByYear2bowling.value = fetchedPlayerYearbyData2
              years2bowling.value = fetchedAlltimeData2
          }
      }




      Spacer(modifier = Modifier.height(30.dp))

      Card(
          colors = CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.surface
          ),
          shape = RoundedCornerShape(15.dp),
          modifier = Modifier
              .width(365.dp)
              .fillMaxHeight()
              .padding(start = 18.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
      ) {
         /* Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(45.dp)
          ) {
              BattingAndBowlingButtons()

          }*/
          BattingAndBowlingButtons(selectedIndex1,selectedIndex3)
          if(selectedButtonState=="batting"){
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically,
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(1) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Innings", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  //Spacer(modifier = Modifier.width(102.dp))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(1) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(2) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Runs", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(2) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(3) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Balls", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(3) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(4) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Outs", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(4) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(5) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Average", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(5) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(6) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "StrikeRate", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(6) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(7) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "HighScore", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(7) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(8) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "50s", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(8) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(9) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "100s", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(9) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(10) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Fours", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(10) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF22212f))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(11) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Sixes", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(11) ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }

          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .background(Color(0xFF383743))
          ) {
              Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Text(
                      text = if (dataByYear.value?.isEmpty() != true) {
                          dataByYear.value?.get(12)
                              ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(start = 15.dp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = "Dot%", color = Color.White, style = TextStyle(fontSize = 17.sp)
                  )
                  Spacer(modifier = Modifier.weight(1f))
                  Text(
                      text = if (dataByYear2.value?.isEmpty() != true) {
                          dataByYear2.value?.get(12)
                              ?: "No data"  // Display "No data" for empty list
                      } else {
                          ""  // Display empty string for null or truly empty list
                      },
                      color = Color.White,
                      style = TextStyle(fontSize = 17.sp),
                      modifier = Modifier.padding(end = 15.dp)
                  )

              }
          }
          }
          else if(selectedButtonState=="Bowling"){
              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically,
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(1) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Innings", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      //Spacer(modifier = Modifier.width(102.dp))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(1) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(2) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Overs", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(2) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(3) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Runs", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(3) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(4) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Wickets", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(4) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(5) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Economy", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(5) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(6) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Bowling Average", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(6) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(7) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Bowling SR", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(7) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(8) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "5Wickets", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(8) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(9) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Best Bowling", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(9) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(10) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Fours", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(10) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF22212f))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(11) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Sixes", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(11) ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }

              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(60.dp)
                      .background(Color(0xFF383743))
              ) {
                  Row(
                      modifier = Modifier.fillMaxSize(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = if (dataByYearbowling.value?.isEmpty() != true) {
                              dataByYearbowling.value?.get(12)
                                  ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(start = 15.dp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = "Dot%", color = Color.White, style = TextStyle(fontSize = 17.sp)
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      Text(
                          text = if (dataByYear2bowling.value?.isEmpty() != true) {
                              dataByYear2bowling.value?.get(12)
                                  ?: "No data"  // Display "No data" for empty list
                          } else {
                              ""  // Display empty string for null or truly empty list
                          },
                          color = Color.White,
                          style = TextStyle(fontSize = 17.sp),
                          modifier = Modifier.padding(end = 15.dp)
                      )

                  }
              }
          }


      }
  }
        }


















/*@Composable
fun DisplayBoxWithLabel(data: String?, label: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Your divider
       *//* Divider(color = Color(0xFF22323e),
            thickness = 1.dp, modifier = Modifier
                .size(width = 150.dp, height = 1.dp))
        Spacer(modifier = Modifier.height(3.5.dp))*//*
        // Your text and box above the divider
        Column(
            *//*modifier = Modifier
                .padding(start = 50.dp,top=3.5.dp)*//* // Adjust padding as needed
        ) {
            data?.let {
                box(data = it)
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(text = label, color = Color.White)
                Spacer(modifier = Modifier.height(3.5.dp))
            }
        }
    }
}*/

@Composable
fun PlayerHeader(name1:String){
    OutlinedCard (colors = CardDefaults.cardColors(
        containerColor = Color(0xFF22212f),
contentColor = Color(0xFF8fcce3)
    ),


        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(130.dp)
            .height(150.dp)
            ,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Column(
                modifier = Modifier.wrapContentSize(), // Wrap the content size of the Column
                horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
            ) {


        val playerName = remember { mutableStateOf<String?>(null) }
        val info = remember { mutableStateOf<List<String>?>(null) }
        val playerinfo= getinfo(name1)
        LaunchedEffect(Unit) {
            val (fetchedPlayerName, fetchedInfo) = playerinfo.await()
           playerName.value=fetchedPlayerName
            info.value=fetchedInfo
        }
        val name=playerName.value?.split(" ")

        Text(text = "${name?.get(0)}",
            color = Color(0xFFe3b05f),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.W500,)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "${name?.get(1)}",
            color = Color(0xFFe3b05f),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 30.sp),
            fontWeight = FontWeight.W500,)
    }
}}}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun pp(){
    Column (modifier = Modifier.padding(start=50.dp)){
        //DisplayPlayerStats()
    }

}
var selectedButtonState = "batting"
@Composable
fun BattingAndBowlingButtons(selectedindex1:Int?,selectedindex3:Int?) {
    val (selectedButton, setSelectedButton) = remember { mutableStateOf(selectedButtonState) }

    Row(
        modifier = Modifier.fillMaxWidth(), // Buttons fill entire row width
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly // Buttons split space evenly
    ) {
        Button(
            onClick = { setSelectedButton("batting")
                selectedButtonState = "batting"

                      },
            colors = if (selectedButton == "batting") ButtonDefaults.buttonColors(Color(0xFF8fcce3)) else ButtonDefaults.buttonColors(Color(0xFF22212f)),
            shape = RoundedCornerShape(topStart = 15.dp),
            modifier = Modifier
                .width(170.dp)
                .height(45.dp)
        ) {
            if (selectedButton == "batting")
                Text(text = "Batting", color = Color.Black)
            else
                Text(text = "Batting", color = Color(0xFF87898e))
        }

        Button(
            onClick = { setSelectedButton("Bowling")
                selectedButtonState = "Bowling"},
            colors = if (selectedButton == "Bowling") ButtonDefaults.buttonColors(Color(0xFF8fcce3)) else ButtonDefaults.buttonColors(Color(0xFF22212f)),
            shape = RoundedCornerShape(topEnd = 15.dp),
            modifier = Modifier
                .width(180.dp)
                .height(45.dp)
        ) {
            if (selectedButton == "Bowling")
                Text(text = "Bowling", color = Color.Black)
            else
                Text(text = "Bowling", color = Color(0xFF87898e))
        }
    }
   // return selectedButton
}



