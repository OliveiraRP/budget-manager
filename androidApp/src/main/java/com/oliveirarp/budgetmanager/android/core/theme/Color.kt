package com.oliveirarp.budgetmanager.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
import com.oliveirarp.budgetmanager.core.presentation.Colors

val RichBlack = Color(Colors.RichBlack)
val OxfordBlue = Color(Colors.OxfordBlue)
val YlnMnBlue = Color(Colors.YlnMnBlue)
val SilverLakeBlue = Color(Colors.SilverLakeBlue)
val Platinum = Color(Colors.Platinum)

val darkColors = darkColors(
    primary = SilverLakeBlue,
    background = RichBlack,
    onPrimary = Platinum,
    onBackground = Platinum,
    surface = RichBlack,
    onSurface = Platinum
)