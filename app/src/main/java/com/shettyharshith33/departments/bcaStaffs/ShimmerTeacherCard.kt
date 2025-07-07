package com.shettyharshith33.departments.bcaStaffs

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shettyharshith33.vcputtur.ui.theme.shimmerGrey
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


@Composable
fun ShimmerTeacherCard(teacher: Teacher) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(80.dp)
                .shimmer(shimmerInstance),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.LightGray
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circle shimmer for teacher profile picture
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .background(shimmerGrey)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    // Rectangular shimmer placeholders for text
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(14.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(shimmerGrey)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(shimmerGrey)
                    )
                }
            }
        }
    }
}


