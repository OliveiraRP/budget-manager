import { useState, useMemo } from "react";
import { OneColumnTemplate } from "@ui/templates/OneColumnTemplate";
import { FullScreenOverlayTemplate } from "@ui/templates/OverlayTemplate";
import { TwoButtonPageHeaderComponent } from "@ui/components/headers/PageHeaderComponent";
import { IconButtonComponent } from "@ui/components/ButtonComponent";
import { IconRes } from "@ui/utils/IconRes";
import { ICON } from "@ui/constants/icons";
import { TransactionList } from "../../ui/TransactionList";
import { useTransactionsByTimeframe } from "../../hooks/useTransactions";
import { TimeframeComponent } from "@ui/components/TimeframeComponent";
import { useUserSettings } from "../../hooks/useUserSettings";
import { getEndDate, toLocalISOString } from "../../utils/date";
import { CreateTransactionPage } from "./CreateTransactionPage";

export default function OverviewPage() {
  const { data: settings } = useUserSettings();
  const [editingTransaction, setEditingTransaction] = useState(null);

  const getInitialDate = (prefDay = 1) => {
    const now = new Date();
    let d = new Date(now.getFullYear(), now.getMonth(), prefDay, 12, 0, 0);
    if (now.getDate() < prefDay) {
      d.setMonth(d.getMonth() - 1);
    }
    return toLocalISOString(d);
  };

  const [currentStart, setCurrentStart] = useState(() =>
    getInitialDate(settings?.bm_start_day || 1),
  );

  const endDay = useMemo(() => getEndDate(currentStart), [currentStart]);

  const { data: transactions = [], isPlaceholderData } =
    useTransactionsByTimeframe(currentStart, endDay);

  return (
    <OneColumnTemplate
      header={
        <TwoButtonPageHeaderComponent
          rightButton={
            <IconButtonComponent
              icon={<IconRes icon={ICON.ADD} />}
              onClick={() => setEditingTransaction({})}
            />
          }
          title="Overview"
        />
      }
    >
      <TimeframeComponent
        startDate={currentStart}
        onRangeChange={setCurrentStart}
      />

      <TransactionList
        transactions={transactions}
        onTransactionClick={(t) => setEditingTransaction(t)}
      />

      <FullScreenOverlayTemplate
        isOpen={Boolean(editingTransaction)}
        onClose={() => setEditingTransaction(null)}
      >
        <CreateTransactionPage
          initialData={editingTransaction}
          onClose={() => setEditingTransaction(null)}
        />
      </FullScreenOverlayTemplate>
    </OneColumnTemplate>
  );
}
