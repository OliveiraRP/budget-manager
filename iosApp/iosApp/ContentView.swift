import shared
import SwiftUI

struct ContentView: View {
    private let appModule = AppModule()

    var body: some View {
        BudgetsScreen(
            budgetDataSource: appModule.budgetDataSource,
            getBudgetsUseCase: appModule.getBudgetsUseCase,
            addBudgetUseCase: appModule.addBudgetUseCase
        )
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
