package com.shettyharshith33.departments.bcaStaffs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.shettyharshith33.vcputtur.ui.theme.inClass
import com.shettyharshith33.vcputtur.ui.theme.newGreen
import com.shettyharshith33.vcputtur.ui.theme.orange
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.resultColor
import com.shettyharshith33.vcputtur.ui.theme.warningRed

@Composable
fun TeacherDetailDialog(teacher: Teacher, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = teacher.imageUrl,
                    contentDescription = teacher.name + "Image",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = teacher.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = poppinsFontFamily
                )
                Text(
                    text = teacher.desig,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily
                )
                Text(
                    text = "Qualification: ${teacher.qualification}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center, fontSize = 14.sp
                )
                Text(
                    text = "Experience: ${teacher.experience} yrs",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Text(
                    text = buildAnnotatedString {

                        append("Status: ")

//                        val predefinedStatuses = listOf("Free/Available", "Busy", "Away", "On leave")

                        // Apply different styles based on teacher's status
                        val statusColor = when (teacher.status) {
                            "Free/Available" -> newGreen
                            "Busy" -> orange
                            "Away" -> Color.DarkGray
                            "On leave" ->  Color.Red
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
            }
        }
    }
}
