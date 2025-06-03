module.exports = (sequelize, DataTypes) => {
  const Wallet = sequelize.define(
    "Wallet",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      initialBalance: {
        type: DataTypes.DECIMAL(13, 2),
        allowNull: false,
      },
      type: {
        type: DataTypes.SMALLINT,
        allowNull: false,
      },
      includeNetWorth: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: true,
      },
      icon: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      color: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      savingsGoals: {
        type: DataTypes.DECIMAL(13, 2),
        allowNull: true,
      },
      archived: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false,
      },
    },
    {
      tableName: "wallets",
      schema: "budget_manager",
    }
  );

  Wallet.associate = (models) => {
    Wallet.belongsTo(models.User, {
      foreignKey: {
        name: "user_id",
        allowNull: false,
      },
      as: "user",
    });
  };

  return Wallet;
};
