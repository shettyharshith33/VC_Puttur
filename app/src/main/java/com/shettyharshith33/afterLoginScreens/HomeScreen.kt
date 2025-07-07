package com.shettyharshith33.afterLoginScreens

import SetStatusBarColor
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.beforeLoginScreens.ConfirmLogin
import com.shettyharshith33.beforeLoginScreens.NetworkStatusBanner
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.beforeLoginScreens.showMsg
import com.shettyharshith33.beforeLoginScreens.triggerVibration
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.cardColor
import com.shettyharshith33.vcputtur.ui.theme.lightDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.lightestDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.myGrey
import com.shettyharshith33.vcputtur.ui.theme.orange
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.signInGrey
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.vcputtur.ui.theme.warningRed
import com.shettyharshith33.viewmodels.NetworkViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel: NetworkViewModel)
{


    val isConnected by viewModel.isConnected.observeAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current
    var confirmLogout by remember { mutableStateOf(false) }


    SetStatusBarColor(Color(lightDodgerBlue.toArgb()), useDarkIcons = false)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(modifier = Modifier, drawerContainerColor = lightDodgerBlue)
        {
            LazyColumn() {
                item {
                    Text("Developers",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { /* handle click */ })
                }
                item {
                    Text("Departments",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { /* handle click */ })
                }
                item {
                    Text("Admissions",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { /* handle click */ })
                }
                item {
                    Text("Examinations",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                context.showMsg("Nothing to show for now!")
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                triggerVibration(context)
                            })
                }
                item {
                    Text("Campus",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {})
                }
                item {
                    Text("Gallery",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {})
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (confirmLogout) {
                            ConfirmLogoUt(
                                onDismiss = { confirmLogout = false },
                                navController = navController
                            )
                        }


                        Text("Logout",
                            color = Color.White,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    haptic.performHapticFeedback(
                                        HapticFeedbackType.LongPress
                                    )
                                    context.showMsg("Logout?")
                                    triggerVibration(context)
                                    confirmLogout = true
                                })
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            tint = Color.White,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                haptic.performHapticFeedback(
                                    HapticFeedbackType.LongPress
                                )
                                context.showMsg("Logout?")
                                triggerVibration(context)
                                confirmLogout = true
                            })
                    }

                }
            }
        }
    }) {

        Scaffold(
            topBar = {
                TopAppBar(modifier = Modifier.height(60.dp), colors = TopAppBarColors(
                    containerColor = lightDodgerBlue,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = lightDodgerBlue,
                    titleContentColor = lightDodgerBlue,
                    actionIconContentColor = lightDodgerBlue
                ), title = {
                    Text(
                        //Just a demo comment
                        text = "VC Puttur",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 70.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        color = Color.White
                    )
                }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            tint = Color.White
                        )
                    }
                })
            }, containerColor = myGrey
        ) { innerPadding ->


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightestDodgerBlue)
                    .padding(innerPadding)
            ) {

                item {


                    Column (modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Spacer(modifier = Modifier.height(20.dp))
                        NetworkStatusBanner(isConnected ?: false)

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(10.dp)
                                .shadow(
                                    elevation = 14.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    ambientColor = Color.LightGray
                                )
                        ) {
                            Image(
                                painterResource(R.drawable.principal), contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }

                        Text(
                            "Prof.Vishnu Ganapathi Bhat",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = orange
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Principal",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "MSc, MPhil",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            "Message from the Principal:",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 3.dp),
                            textAlign = TextAlign.Left,
                            color = textColor,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            "There underlies very great principle," +
                                    " a noble reason and an exemplary vision behind the founding of" +
                                    " Vivekananda College of Arts, Science and Commerce by Vivekananda " +
                                    "Vidyavardhaka Sangha way back in 1965 in Puttur. It is the very vision of" +
                                    " inculcating the spirit of nationalism. At a certain period in history, when there" +
                                    " seemed a possible hurdle in the practice of our traditions and belief systems, the initiative " +
                                    "for establishing an educational institution that upholds the practice" +
                                    " was taken up by a few scholars and thinkers of the region.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 3.dp),
                            textAlign = TextAlign.Left,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light,
                            lineHeight = 25.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            "Accelerate your career with Vivekananda College",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 3.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = textColor
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(18.dp))
                    ) {

                        AutoScrollingImageCarousel(
                            imageList = listOf(
                                R.drawable.panchajanya,
                                R.drawable.hp_admissions_open,
                                R.drawable.varsha_aurdino
                            )
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            "CGPA Claculator",
                            modifier = Modifier.padding(start = 15.dp),
                            fontSize = 18.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Button(
                                onClick = {
                                    navController.navigate(
                                        BeforeLoginScreensNavigationObject.CGPA_CALCULATOR_SCREEN
                                    )
                                },
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = lightDodgerBlue
                                )
                            ) {
                                Text(
                                    "Calculate Your CGPA",
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold
                                )

                            }

                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Quick Access",
                        modifier = Modifier.padding(start = 15.dp),
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Card(
                                modifier = Modifier.size(100.dp),
                                colors = CardDefaults.cardColors().copy(
                                    containerColor = signInGrey
                                )
                            ) {

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.admissions),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.ADMISSIONS_SCREEN
                                                )
                                            },
                                        alignment = Alignment.Center

                                    )
                                    Text(
                                        "Admissions",
                                        modifier = Modifier.clickable {
                                            navController.navigate(
                                                BeforeLoginScreensNavigationObject.ADMISSIONS_SCREEN
                                            )
                                        },
                                        fontFamily = poppinsFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        navController.navigate(BeforeLoginScreensNavigationObject.ACADEMICS_SCREEN)
                                    },
                                colors = CardDefaults.cardColors().copy(
                                    containerColor = signInGrey
                                )
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.academics),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.ACADEMICS_SCREEN
                                                )
                                            },
                                        alignment = Alignment.Center
                                    )
                                    Text(
                                        "Academics",
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.ACADEMICS_SCREEN
                                                )
                                            },
                                        fontFamily = poppinsFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        navController.navigate(BeforeLoginScreensNavigationObject.DEPARTMENTS)
                                    },
                                colors = CardDefaults.cardColors().copy(
                                    containerColor = signInGrey
                                )
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.departments),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.DEPARTMENTS
                                                )
                                            },
                                        alignment = Alignment.Center
                                    )
                                    Text(
                                        "Departments",
                                        fontFamily = poppinsFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.DEPARTMENTS
                                                )
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Courses",
                        modifier = Modifier.padding(start = 15.dp),
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        colors = CardDefaults.cardColors().copy(
                            containerColor = cardColor
                        )
                    )
                    {
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(35.dp),
                                    colors = CardDefaults.cardColors().copy(
                                        containerColor = lightDodgerBlue
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            "UG Courses",
                                            fontFamily = poppinsFontFamily,
                                            color = Color.White,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }


                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.BCA
                                                )
                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "BCA",
                                                modifier = Modifier.clickable {
                                                    navController.navigate(
                                                        BeforeLoginScreensNavigationObject.BCA
                                                    )
                                                },
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.BBA
                                                )
                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "BBA",
                                                modifier = Modifier
                                                    .clickable {
                                                        navController.navigate(
                                                            BeforeLoginScreensNavigationObject.BBA
                                                        )
                                                    },
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.BCOM
                                                )
                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "BCom",
                                                modifier = Modifier
                                                    .clickable {
                                                        navController.navigate(
                                                            BeforeLoginScreensNavigationObject.BCOM
                                                        )
                                                    },
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.BA
                                                )
                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "BA",
                                                modifier = Modifier
                                                    .clickable {
                                                        navController.navigate(
                                                            BeforeLoginScreensNavigationObject.BA
                                                        )
                                                    },
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    BeforeLoginScreensNavigationObject.BSC
                                                )
                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "BSc",
                                                modifier = Modifier.clickable {
                                                    navController.navigate(
                                                        BeforeLoginScreensNavigationObject.BSC
                                                    )
                                                },
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        colors = CardDefaults.cardColors().copy(
                            containerColor = cardColor
                        )
                    )
                    {
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    modifier = Modifier
                                        .clickable {

                                        }
                                        .width(100.dp)
                                        .height(35.dp),
                                    colors = CardDefaults.cardColors().copy(
                                        containerColor = lightDodgerBlue
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {

                                        Text(
                                            "PG Courses",
                                            fontFamily = poppinsFontFamily,
                                            color = Color.White,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }


                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .clickable {

                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults
                                            .cardColors()
                                            .copy(
                                                containerColor = signInGrey
                                            )
                                    ) {

                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            item {
                                                Text(
                                                    "M.Com",
                                                    fontFamily = poppinsFontFamily,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .clickable {

                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            item {
                                                Text(
                                                    "MSc",
                                                    fontFamily = poppinsFontFamily,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                    Card(
                                        modifier = Modifier
                                            .clickable {

                                            }
                                            .width(100.dp)
                                            .height(35.dp),
                                        colors = CardDefaults.cardColors().copy(
                                            containerColor = signInGrey
                                        )
                                    ) {

                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                "MCJ",
                                                fontFamily = poppinsFontFamily,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }
            }
        }
    }
}

fun signOutAndNavigate(navController: NavController) {
    val context = navController.context
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    FirebaseAuth.getInstance().signOut()
    sharedPreferences.edit().clear().apply()

    navController.navigate(BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
        popUpTo(0) // Clears all previous routes from backstack
    }
}


@Composable
fun AutoScrollingImageCarousel(imageList: List<Int>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000) // Wait 2 seconds
            val currentIndex = listState.firstVisibleItemIndex
            val nextIndex = (currentIndex + 1) % imageList.size
            coroutineScope.launch {
                listState.animateScrollToItem(nextIndex)
            }
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp), // Adjust height as needed
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(imageList.size) { index ->
            Image(
                modifier = Modifier
                    .fillParentMaxSize()
                    .clip(RoundedCornerShape(18.dp)),
                painter = painterResource(id = imageList[index]),
                contentDescription = "Staff Image $index"

            )
        }
    }
}




@Composable
fun ConfirmLogoUt(
    onDismiss: () -> Unit,
    navController: NavController
) {

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                "Sign out",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )
        },
        text = {
            Column() {
                Text(
                    "Are you sure you want to Signout?",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = warningRed,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        signOutAndNavigate(navController)
                    })
                {
                    Text("Yes")
                }

                Spacer(modifier = Modifier.width(24.dp))

                TextButton(onClick = { onDismiss() })
                {
                    Text("Cancel")
                }

            }
        }
    )
}
