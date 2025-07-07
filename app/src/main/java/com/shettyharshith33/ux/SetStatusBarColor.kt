import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarColor(color: Color, useDarkIcons: Boolean = true) {
    val context = LocalContext.current
    val view = LocalView.current

    val window = (context as Activity).window
    window.statusBarColor = color.toArgb()

    // Let your content draw behind system bars
    WindowCompat.setDecorFitsSystemWindows(window, false)

    val windowInsetsController = WindowInsetsControllerCompat(window, view)
    windowInsetsController.isAppearanceLightStatusBars = useDarkIcons
}
