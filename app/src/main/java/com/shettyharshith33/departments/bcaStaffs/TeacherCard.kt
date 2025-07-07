package com.shettyharshith33.departments.bcaStaffs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.Timestamp
import com.shettyharshith33.vcputtur.ui.theme.inClass
import com.shettyharshith33.vcputtur.ui.theme.lightDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.myGreen
import com.shettyharshith33.vcputtur.ui.theme.newGreen
import com.shettyharshith33.vcputtur.ui.theme.orange
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.resultColor
import com.shettyharshith33.vcputtur.ui.theme.warningRed
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TeacherCard(teacher: Teacher, onClick: () -> Unit) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    val dateFormat =
        SimpleDateFormat("MMM dd yyyy, HH:mm", Locale.getDefault()) // Custom date format
    val lastUpdated =
        teacher.lastUpdated?.toDate()?.let { dateFormat.format(it) } ?: "Not Available"







    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }, // Make it clickable
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(teacher.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Teacher Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = teacher.name,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = teacher.desig,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Qualification: ${teacher.qualification}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Experience: ${teacher.experience} yrs",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = buildAnnotatedString {

                        append("Status: ")

                        // Apply different styles based on teacher's status
                        val statusColor = when (teacher.status) {
                            "Free/Available" -> newGreen
                            "Busy" -> orange
                            "Away" -> Color.DarkGray
                            "On leave" -> Color.Red
                            "In Class" -> inClass

                            else -> Color.Blue    // Default color if status is something else
                        }

                        // Apply the color and bold style to the status text
                        withStyle(
                            style = SpanStyle(
                                color = statusColor,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(teacher.status)
                        }
                    },
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )


                Text(
                    text = buildAnnotatedString {

                        append("Last Updated: ")


                        // Apply the color and bold style to the status text
                        withStyle(
                            style = SpanStyle(
                                color = lightDodgerBlue,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("${teacher.lastUpdated?.let { formatTimestamp(it) }}")
                        }
                    },
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }
}

fun formatTimestamp(timestamp: Timestamp): String {
    val sdf = SimpleDateFormat("MMM dd, hh.mm a", Locale.getDefault())
    val date = timestamp.toDate()
    return sdf.format(date)
}
