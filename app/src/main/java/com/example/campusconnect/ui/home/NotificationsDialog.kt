package com.example.campusconnect.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CampusNotification(val title: String, val message: String, val time: String)

@Composable
fun NotificationsDialog(
    onDismiss: () -> Unit
) {
    val notifications = listOf(
        CampusNotification("Assignment Due", "Software Engineering Project Milestone 2 is due Friday at 11:59 PM.", "1 hour ago"),
        CampusNotification("Exam Schedule Released", "Midterm exam timetable for Spring 2027 is now available in your portal.", "5 hours ago"),
        CampusNotification("Library Notice", "Main library will remain open 24/7 during finals week.", "1 day ago"),
        CampusNotification("Welcome Week", "Check out club sign-ups at the quad today!", "2 days ago")
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Campus Notifications", fontWeight = FontWeight.Bold) },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notifications) { notif ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(notif.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(notif.time, fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(notif.message, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
