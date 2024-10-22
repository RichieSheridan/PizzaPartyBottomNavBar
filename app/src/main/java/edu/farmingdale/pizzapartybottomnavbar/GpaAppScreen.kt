package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// ToDo 4: DONE Match the UI as in drawable gpa_design.png. Use the following hints:
// - The background color should be Color.Cyan
// - Fix padding, alignment, and keypad type

// ToDo 5: DONE Add the GpaAppScreen composable button that clears the input fields when clicked

@Composable
fun GpaAppScreen() {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }

    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Calulate GPA") }


    Column(
        modifier = Modifier
            .fillMaxSize() //fill screen
            .background(Color.Cyan) //set background color to cyan
            .padding(16.dp), //add padding
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(), //fill width
            label = { Text("Course 1 Grade") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) //use number input
        )

        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            label = { Text("Course 2 Grade") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            label = { Text("Course 3 Grade") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        //Added a button to clear the input fields
        Button(onClick = {
            if (btnLabel == "Compute GPA") {
                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null) {
                    gpa = gpaVal.toString()

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                    btnLabel = "Clear"
                } else {
                    gpa = "Invalid input"
                }
            } else {
                //Reset all values to empty
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
                btnLabel = "Compute GPA"
            }
        }, modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxWidth(0.7f)
        ) {
            Text(btnLabel)
        }

        if (gpa.isNotEmpty()) {
            Text(text = "GPA: $gpa", modifier = Modifier.padding(top = 16.dp)) //add padding to the GPA text
        }
    }
}

fun calGPA(grade1: String, grade2: String, grade3: String): Double {
    val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
    return grades.average()
}
