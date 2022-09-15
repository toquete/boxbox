package com.toquete.boxbox.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.toquete.boxbox.R

val FormulaOne = FontFamily(
    Font(R.font.formula_one_regular),
    Font(R.font.formula_one_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(defaultFontFamily = FormulaOne)