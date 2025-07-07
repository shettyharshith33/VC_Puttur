package com.shettyharshith33.utils

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shettyharshith33.afterLoginScreens.AcademicsScreen
import com.shettyharshith33.afterLoginScreens.AdmissionsScreen
import com.shettyharshith33.afterLoginScreens.CgpaCalculatorScreen
import com.shettyharshith33.afterLoginScreens.DepartmentsScreen
import com.shettyharshith33.afterLoginScreens.Gallery
import com.shettyharshith33.afterLoginScreens.HomeScreen
import com.shettyharshith33.beforeLoginScreens.AuthCheckScreen
import com.shettyharshith33.beforeLoginScreens.ConfirmLogin
import com.shettyharshith33.beforeLoginScreens.EmailLinkSentPage
import com.shettyharshith33.beforeLoginScreens.OnBoardingScreen
import com.shettyharshith33.beforeLoginScreens.SignUpScreen
import com.shettyharshith33.beforeLoginScreens.TeacherHomeScreen
import com.shettyharshith33.beforeLoginScreens.TeacherLoginScreen
import com.shettyharshith33.beforeLoginScreens.TeacherValidationScreen
import com.shettyharshith33.departments.bcaStaffs.BCATeacherListScreen
import com.shettyharshith33.departments.bcomStaffs.BCOMTeacherListScreen
import com.shettyharshith33.departments.historyStaffs.HistoryTeacherListScreen
import com.shettyharshith33.ugCourses.BA
import com.shettyharshith33.ugCourses.BBA
import com.shettyharshith33.ugCourses.BCA
import com.shettyharshith33.ugCourses.BCOM
import com.shettyharshith33.ugCourses.BSC
import com.shettyharshith33.viewmodels.NetworkViewModel


object BeforeLoginScreensNavigationObject {
    const val AUTH_CHECK = "authCheck"
    const val OTP_REQUEST_PAGE = "otpRequestPage"
    const val OTP_VERIFICATION_PAGE = "otpVerificationPage"
    const val ONBOARDING_SCREEN = "onBoardingScreen"
    const val SIGNUP_SCREEN = "signUpScreen"
    const val LOGIN_SCREEN = "loginScreen"
    const val HOME_SCREEN = "homeScreen"
    const val EMAIL_LINK_SENT_PAGE = "emailLinkSentPage"
    const val CGPA_CALCULATOR_SCREEN = "cgpaCalculatorScreen"
    const val GALLERY = "gallery"
    const val ADMISSIONS_SCREEN = "admissionsScreen"
    const val BCA = "bca"
    const val BBA = "bba"
    const val BCOM = "bCom"
    const val BA = "ba"
    const val BSC = "bSc"
    const val ACADEMICS_SCREEN = "academicsScreen"
    const val DEPARTMENTS = "departments"

    const val BCA_TEACHER_LIST_SCREEN = "bcaTeacherListScreen"


    const val BCOM_TEACHER_LIST_SCREEN = "bcomTeacherListScreen"


    const val HISTORY_TEACHER_LIST_SCREEN = "historyTeacherListScreen"


    const val TEACHER_LOGIN_SCREEN = "teacherLoginScreen"


    const val TEACHER_VALIDATION_SCREEN = "teacherValidationScreen"


    const val TEACHER_HOME_SCREEN = "teacherHomeScreen/{loginEmail}"


    const val CONFIRM_TEACHER_LOGIN = "confirmTeacherLogin"








}


@Composable
fun BeforeLoginScreensNavigation(navController: NavController) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = BeforeLoginScreensNavigationObject.AUTH_CHECK
    ) {
        composable(BeforeLoginScreensNavigationObject.AUTH_CHECK) {
            AuthCheckScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
            OnBoardingScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.SIGNUP_SCREEN) {
            SignUpScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.HOME_SCREEN) {
            HomeScreen(navController, viewModel = viewModel())
        }
        composable(route = BeforeLoginScreensNavigationObject.EMAIL_LINK_SENT_PAGE) {
            EmailLinkSentPage(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.CGPA_CALCULATOR_SCREEN) {
            CgpaCalculatorScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.GALLERY) {
            Gallery(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.ADMISSIONS_SCREEN) {
            AdmissionsScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BCA) {
            BCA(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BSC) {
            BSC(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BCOM) {
            BCOM(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BBA) {
            BBA(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BA) {
            BA(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.ACADEMICS_SCREEN) {
            AcademicsScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.DEPARTMENTS) {
            DepartmentsScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BCA_TEACHER_LIST_SCREEN) {
            BCATeacherListScreen(viewModel = viewModel(), navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.BCOM_TEACHER_LIST_SCREEN) {
            BCOMTeacherListScreen(viewModel = viewModel(), navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.HISTORY_TEACHER_LIST_SCREEN) {
            HistoryTeacherListScreen(viewModel = viewModel(), navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.CONFIRM_TEACHER_LOGIN) {
            ConfirmLogin(onDismiss = {},navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.TEACHER_LOGIN_SCREEN) {
            TeacherLoginScreen(navController)
        }
        composable(route = BeforeLoginScreensNavigationObject.TEACHER_VALIDATION_SCREEN) {
            TeacherValidationScreen(navController)
        }
//        composable(route = BeforeLoginScreensNavigationObject.TEACHER_HOME_SCREEN) {
//            TeacherHomeScreen(loginEmail = "",navController)
//        }

        composable(
            route = BeforeLoginScreensNavigationObject.TEACHER_HOME_SCREEN
        ) { backStackEntry ->
            val loginEmail = backStackEntry.arguments?.getString("loginEmail") ?: ""
            TeacherHomeScreen(loginEmail = loginEmail, navController)
        }


    }
}