module.exports = (sequelize, DataTypes) => {
  const TemplatedTransaction = sequelize.define(
    "TemplatedTransaction",
    {
      amount: {
        type: DataTypes.DECIMAL(13, 2),
        allowNull: false,
      },
    },
    {
      tableName: "templated_transactions",
      schema: "budget_manager",
    }
  );

  TemplatedTransaction.associate = (models) => {
    TemplatedTransaction.belongsTo(models.TemplatedTransactionGroup, {
      foreignKey: {
        name: "templated_transaction_group_id",
        allowNull: false,
      },
      as: "templatedTransactionGroup",
    });

    TemplatedTransaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "wallet_id",
        allowNull: true,
      },
      as: "wallet",
    });

    TemplatedTransaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "transfer_from_wallet_id",
        allowNull: true,
      },
      as: "transferFromWallet",
    });

    TemplatedTransaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "transfer_to_wallet_id",
        allowNull: true,
      },
      as: "transferToWallet",
    });

    TemplatedTransaction.belongsTo(models.Category, {
      foreignKey: {
        name: "category_id",
        allowNull: false,
      },
      as: "category",
    });
  };

  return TemplatedTransaction;
};
