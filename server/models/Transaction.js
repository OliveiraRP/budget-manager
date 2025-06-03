module.exports = (sequelize, DataTypes) => {
  const Transaction = sequelize.define(
    "Transaction",
    {
      amount: {
        type: DataTypes.DECIMAL(13, 2),
        allowNull: false,
      },
      description: {
        type: DataTypes.STRING,
        allowNull: true,
      },
      date: {
        type: DataTypes.DATEONLY,
        allowNull: false,
      },
      scheduled: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false,
      },
      recurrenceType: {
        type: DataTypes.SMALLINT,
        allowNull: false,
        defaultValue: 0,
      },
      excludeFromWallet: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false,
      },
    },
    {
      tableName: "transactions",
      schema: "budget_manager",
    }
  );

  Transaction.associate = (models) => {
    Transaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "wallet_id",
        allowNull: true,
      },
      as: "wallet",
    });

    Transaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "transfer_from_wallet_id",
        allowNull: true,
      },
      as: "transferFromWallet",
    });

    Transaction.belongsTo(models.Wallet, {
      foreignKey: {
        name: "transfer_to_wallet_id",
        allowNull: true,
      },
      as: "transferToWallet",
    });

    Transaction.belongsTo(models.Category, {
      foreignKey: {
        name: "category_id",
        allowNull: false,
      },
      as: "category",
    });
  };

  return Transaction;
};
