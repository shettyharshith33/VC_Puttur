package com.shettyharshith33.beforeLoginScreens

import SetStatusBarColor
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shettyharshith33.afterLoginScreens.AutoScrollingImageCarousel
import com.shettyharshith33.afterLoginScreens.ConfirmLogoUt
import com.shettyharshith33.afterLoginScreens.signOutAndNavigate
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.cardColor
import com.shettyharshith33.vcputtur.ui.theme.inClass
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.vcputtur.ui.theme.lightestDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.myGrey
import com.shettyharshith33.vcputtur.ui.theme.newGreen
import com.shettyharshith33.vcputtur.ui.theme.orange
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.shimmerGrey
import com.shettyharshith33.vcputtur.ui.theme.signInGrey
import com.shettyharshith33.vcputtur.ui.theme.statusCardColor
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.vcputtur.ui.theme.veryLightGreen
import com.shettyharshith33.vcputtur.ui.theme.warningRed
import com.shettyharshith33.viewmodels.AuthViewModel
import com.shettyharshith33.viewmodels.NetworkViewModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeScreen(loginEmail: String, navController: NavController) {
    val firestore = Firebase.firestore
    val context = LocalContext.current
    var status by remember { mutableStateOf("") }
    var lastUpdated by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val predefinedStatuses = listOf("Free/Available", "Busy", "Away", "On leave", "In Class")

    val statusColors = listOf(newGreen, orange, Color.DarkGray, Color.Red, inClass)


    var expanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("") }
    var customStatus by remember { mutableStateOf("") }
    var isCustomStatus by remember { mutableStateOf(false) }

    val collections =
        listOf("bcaStaffs", "bcomStaffs", "bbaStaffs", "economicsStaffs", "historyStaffs")
    var currentCollection by remember { mutableStateOf("") }


    val scope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current

    val viewModel: NetworkViewModel = hiltViewModel() // Replace with your ViewModel if named differently
    val isConnected by viewModel.isConnected.observeAsState()

    var confirmLogout by remember { mutableStateOf(false) }





    SetStatusBarColor(Color(textColor.toArgb()), useDarkIcons = false)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(modifier = Modifier, drawerContainerColor = textColor)
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

                    if (confirmLogout) {
                        ConfirmLogoUt(
                            onDismiss = { confirmLogout = false },
                            navController = navController
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sign Out",
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
                    containerColor = textColor,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = textColor,
                    titleContentColor = textColor,
                    actionIconContentColor = textColor
                ), title = {
                    Text(
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
                    // Fetch teacher document by email
                    LaunchedEffect(loginEmail) {
                        loading = true
                        var found = false

                        collections.forEach { collection ->
                            firestore.collection(collection)
                                .whereEqualTo("email", loginEmail)
                                .get()
                                .addOnSuccessListener { result ->
                                    if (!result.isEmpty && !found) {
                                        val doc = result.documents.first()
                                        val fetchedStatus = doc.getString("status") ?: ""
                                        status = fetchedStatus
                                        selectedStatus =
                                            if (fetchedStatus in predefinedStatuses) fetchedStatus else "Custom"
                                        customStatus =
                                            if (fetchedStatus !in predefinedStatuses) fetchedStatus else ""
                                        isCustomStatus = fetchedStatus !in predefinedStatuses

                                        val timestamp = doc.getTimestamp("lastUpdated")
                                        lastUpdated = timestamp?.toDate()?.let {
                                            SimpleDateFormat(
                                                "dd MMM yyyy, hh:mm a",
                                                Locale.getDefault()
                                            ).format(it)
                                        } ?: "Unknown"

                                        currentCollection = collection
                                        found = true
                                        loading = false
                                    }
                                }
                        }

                        // Fallback in case no match is found in any collection
                        delay(3000)
                        if (!found) {
                            lastUpdated = "Unknown"
                            loading = false
                        }
                    }

                    if (loading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(25.dp)),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(10.dp)
                                    .shimmer(),
                                colors = CardDefaults.cardColors().copy(
                                    containerColor = shimmerGrey
                                )
                            ) {
                                Text(
                                    "Please wait...",
                                    color = Color.Black,
                                    modifier = Modifier.fillParentMaxSize(),
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 25.sp
                                )
                            }
                        }
                    } else {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors().copy(
                                containerColor = veryLightGreen
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(" Please update your availability status",
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold)
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = !expanded }
                                ) {
                                    OutlinedTextField(
                                        readOnly = true,
                                        value = if (isCustomStatus) "Custom" else selectedStatus,
                                        onValueChange = {},
                                        label = { Text("Select Status") },
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowDropDown,
                                                contentDescription = null
                                            )
                                        },
                                        colors = TextFieldDefaults.colors().copy(
                                            unfocusedIndicatorColor = textColor,
                                            focusedIndicatorColor = textColor,
                                            unfocusedContainerColor = veryLightGreen,
                                            focusedContainerColor = veryLightGreen
                                        ),
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth()
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        predefinedStatuses.forEachIndexed { index, selectionOption ->
                                            DropdownMenuItem(
                                                text = {
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Box(
                                                            modifier = Modifier
                                                                .size(12.dp)
                                                                .clip(CircleShape)
                                                                .background(
                                                                    statusColors.getOrElse(
                                                                        index
                                                                    ) { Color.Gray }) // small circle
                                                        )
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        Text(selectionOption)
                                                    }
                                                },
                                                onClick = {
                                                    selectedStatus = selectionOption
                                                    status = selectionOption
                                                    isCustomStatus = false
                                                    customStatus = ""
                                                    expanded = false
                                                }
                                            )
                                        }
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    "Custom",
                                                    color = Color.Blue
                                                )
                                            },
                                            onClick = {
                                                selectedStatus = "Custom"
                                                isCustomStatus = true
                                                status = customStatus
                                                expanded = false
                                            }
                                        )
                                    }
                                }

                                if (isCustomStatus) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    OutlinedTextField(
                                        value = customStatus,
                                        onValueChange = {
                                            customStatus = it
                                            status = it
                                        },
                                        label = { Text("Enter Custom Status") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    "Last updated: $lastUpdated",
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Button(
                                    onClick = {
                                        val currentTime = SimpleDateFormat(
                                            "dd MMM yyyy, hh:mm a", Locale.getDefault()
                                        ).format(Date())

                                        if (currentCollection.isNotEmpty()) {
                                            firestore.collection(currentCollection)
                                                .whereEqualTo("email", loginEmail)
                                                .get()
                                                .addOnSuccessListener { result ->
                                                    if (!result.isEmpty) {
                                                        val docId = result.documents.first().id
                                                        firestore.collection(currentCollection)
                                                            .document(docId)
                                                            .update(
                                                                "status",
                                                                status,
                                                                "lastUpdated",
                                                                com.google.firebase.Timestamp.now()
                                                            )
                                                            .addOnSuccessListener {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Status Updated",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                lastUpdated = currentTime
                                                            }
                                                    }
                                                }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Teacher document not found in any collection.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors()
                                        .copy(containerColor = textColor),
                                    modifier = Modifier.padding(top = 16.dp)
                                ) {
                                    Text(
                                        "Save Status",
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
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
                                        containerColor = textColor
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
                                        containerColor = textColor
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
