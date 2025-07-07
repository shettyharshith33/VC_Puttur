package com.shettyharshith33.afterLoginScreens

import SetStatusBarColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.utils.CgpaResult
import com.shettyharshith33.vcputtur.ui.theme.*


@Composable
fun CgpaCalculatorScreen(navController: NavController) {
    var semesterCount by remember { mutableIntStateOf(0) }
    var enteredSemesters by remember { mutableStateOf(false) }
    var semesterMarks = remember { mutableStateMapOf<Int, Pair<String, String>>() }
    var resultText by remember { mutableStateOf("") }
    var calculationDone by remember { mutableStateOf(false) } // <<< New
    var result by remember { mutableStateOf<CgpaResult?>(null) }

    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    SetStatusBarColor(Color(lightDodgerBlue.toArgb()), useDarkIcons = false)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestDodgerBlue)
            .padding(10.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (!enteredSemesters && !calculationDone) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        "Enter number of semesters:",
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )
                    OutlinedTextField(
                        value = semesterCount.takeIf { it > 0 }?.toString() ?: "",
                        onValueChange = { semesterCount = it.toIntOrNull() ?: 0 },
                        singleLine = true,
                        placeholder = { Text("Number of Semester(s)") }
                    )
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = { enteredSemesters = true },
                        colors = ButtonDefaults.buttonColors(containerColor = lightDodgerBlue)
                    ) {
                        Text(
                            "Next",
                            color = Color.White,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.W900
                        )
                    }
                } else if (!calculationDone) {
                    for (sem in 1..semesterCount) {
                        Spacer(Modifier.height(30.dp))
                        Text(
                            "Semester $sem:",
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFontFamily
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedTextField(
                                value = semesterMarks[sem]?.first ?: "",
                                onValueChange = {
                                    semesterMarks[sem] = it to (semesterMarks[sem]?.second ?: "")
                                },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Total Scored") },
                                singleLine = true
                            )
                            Spacer(Modifier.width(8.dp))
                            OutlinedTextField(
                                value = semesterMarks[sem]?.second ?: "",
                                onValueChange = {
                                    semesterMarks[sem] = (semesterMarks[sem]?.first ?: "") to it
                                },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Out of") },
                                singleLine = true
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            result = calculateCgpa(semesterMarks)
                            calculationDone = true
                        },
                        enabled = semesterMarks.values.all { it.first.isNotBlank() && it.second.isNotBlank() },
                        colors = ButtonDefaults.buttonColors(containerColor = lightDodgerBlue)
                    ) {
                        Text(
                            "Calculate CGPA",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(35.dp))
                } else {
                    // Calculation done, show only result
                    ResultDisplay(result, navController)
                }
            }
        }
    }
}

@Composable
fun ResultDisplay(result: CgpaResult?, navController: NavController) {
    if (result == null) {
        Text(
            "Please enter all fields correctly",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily
        )
        return
    }
    Spacer(modifier = Modifier.height(300.dp))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Result Summary",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontFamily = poppinsFontFamily,
                color = resultColor
            )
            Spacer(modifier = Modifier.height(20.dp))

            ResultLine(
                label = "Total Marks",
                value = "${"%.2f".format(result.totalScored)} / ${"%.2f".format(result.totalOutOf)}"
            )
            ResultLine(label = "Percentage", value = "${"%.2f".format(result.percentage)}%")
            ResultLine(label = "CGPA", value = "${"%.2f".format(result.cgpa)}")

            Button(onClick = { navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN)},
                colors = ButtonDefaults.buttonColors().copy(containerColor = lightDodgerBlue)) {
                Text("Done")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate(BeforeLoginScreensNavigationObject.CGPA_CALCULATOR_SCREEN)},
                colors = ButtonDefaults.buttonColors().copy(containerColor = lightDodgerBlue)) {
                Text("Check Again")
            }
        }
    }
}

@Composable
fun ResultLine(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = Color.DarkGray
        )
        Text(
            value,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = Color.Black
        )
    }
}


// Your same calculateCgpa() function
fun calculateCgpa(semesterMarks: Map<Int, Pair<String, String>>): CgpaResult? {
    val totalScored = semesterMarks.values.mapNotNull { it.first.toFloatOrNull() }.sum()
    val totalOutOf = semesterMarks.values.mapNotNull { it.second.toFloatOrNull() }.sum()

    return if (totalOutOf > 0f) {
        val percentage = (totalScored / totalOutOf) * 100
        val cgpa = percentage / 9.5
        CgpaResult(totalScored, totalOutOf, percentage, cgpa)
    } else {
        null
    }
}

