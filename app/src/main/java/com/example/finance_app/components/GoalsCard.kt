package com.example.finance_app.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finance_app.ui.theme.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable

@Composable

fun GoalsCard(
    // Dynamic card 

    title: String,
    current: Double,
    goal: Double,
    color: Color,
    editClick: () -> Unit
) {
    //Calculate progress
    val progress = (current / goal).toFloat()

    // convert to percentage
    val percent = (progress * 100).toInt()

    // Chane progress color based on progress
    val progressColor = when {
        progress >= 0.0f && progress < 0.5f -> ExpenseRed
        progress >= 0.5f && progress < 0.75f -> BudgetYellow
        progress >= 0.75f && progress < 1.0f -> IncomeGreen
        else -> Color.Transparent

    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Card_Navy)
    )
    {

        Column(modifier = Modifier.padding(15.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {


            }



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                // show current / goal amount

                Text(text = title, color = Text_White)

                Text(
                    text = "£${current.toInt()} / £${goal.toInt()}",
                    color = Text_White
                )


            }
            // progress section

            Column(modifier = Modifier.padding(12.dp)) {

                //progress bar
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth(),
                    color = progressColor
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){


                    // percent text
                    Text(text = "$percent% complete", color = Text_White
                    )


                        Text(text = "Edit",
                            color = Text_White,
                            modifier = Modifier.clickable{editClick() }
                )

                    }




                }
            }


        }
    }







