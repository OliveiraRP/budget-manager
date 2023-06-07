//
//  Colors.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension Color {
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 08) & 0xFF) / 255,
            blue: Double((hex >> 00) & 0xFF) / 255,
            opacity: alpha
        )
    }

    private static let colors = Colors()
    static let richBlack = Color(hex: colors.RichBlack)
    static let oxfordBlue = Color(hex: colors.OxfordBlue)
    static let ylnMnBlue = Color(hex: colors.YlnMnBlue)
    static let silverLakeBlue = Color(hex: colors.SilverLakeBlue)
    static let platinum = Color(hex: colors.Platinum)

    // TODO: Light mode colors
    static let primary = Color(light: .silverLakeBlue, dark: .silverLakeBlue)
    static let background = Color(light: .richBlack, dark: .richBlack)
    static let onPrimary = Color(light: .platinum, dark: .platinum)
    static let onBackground = Color(light: .platinum, dark: .platinum)
    static let surface = Color(light: .oxfordBlue, dark: .oxfordBlue)
    static let onSurface = Color(light: .platinum, dark: .platinum)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
