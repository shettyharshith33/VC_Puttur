package com.shettyharshith33.afterLoginScreens

import SetStatusBarColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shettyharshith33.beforeLoginScreens.NetworkStatusBanner
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.vcputtur.ui.theme.lightestDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.viewmodels.NetworkViewModel

@Composable
fun DepartmentsScreen(navController: NavController) {
    val viewModel: NetworkViewModel = hiltViewModel() // Replace with your ViewModel if named differently
    val isConnected by viewModel.isConnected.observeAsState()

    SetStatusBarColor(Color(textColor.toArgb()), useDarkIcons = false)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestDodgerBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            NetworkStatusBanner(isConnected ?: false)
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Text(
                        "Departments",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        fontSize = 25.sp
                    )
                }
            }

        }
        item {
            Spacer(modifier = Modifier.height(35.dp))
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp)
                        .clickable { navController.navigate(BeforeLoginScreensNavigationObject.BCA_TEACHER_LIST_SCREEN) }
                        .width(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                        contentAlignment = Alignment.Center) {
                        Text(
                            "Computer Science",
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    BeforeLoginScreensNavigationObject.BCA_TEACHER_LIST_SCREEN
                                )
                            },
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(modifier = Modifier
                        .clickable {
                            navController.navigate(
                                BeforeLoginScreensNavigationObject.HISTORY_TEACHER_LIST_SCREEN
                            )
                        }
                        .padding(16.dp)
                        .height(50.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                        contentAlignment = Alignment.Center) {
                        Text(
                            "History",
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    BeforeLoginScreensNavigationObject.HISTORY_TEACHER_LIST_SCREEN
                                )
                            },
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Journalism",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Political Science",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Economics",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Sociology",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "English",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Kannada",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Hindi",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Sanskrit",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Physics",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Chemistry",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Botany",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Zoology",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Mathematics",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Electronics",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Psychology",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(modifier = Modifier
                        .clickable {
                            navController.navigate(BeforeLoginScreensNavigationObject.BCOM_TEACHER_LIST_SCREEN)
                        }
                        .padding(16.dp)
                        .height(50.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                        contentAlignment = Alignment.Center) {
                        Text(

                            "Commerce",
                            modifier = Modifier.clickable {
                                navController.navigate(BeforeLoginScreensNavigationObject.BCOM_TEACHER_LIST_SCREEN)
                            },
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                }
            }

        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(100.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Business Adminstration",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(100.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Physical Education and Sports",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(30.dp))
        }


    }
}