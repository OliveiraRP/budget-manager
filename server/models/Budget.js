module.exports = (sequelize, DataTypes) => {
  const Budget = sequelize.define(
    "Budget",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      tableName: "budgets",
      schema: "budget_manager",
    }
  );

  Budget.associate = (models) => {
    Budget.belongsTo(models.User, {
      foreignKey: {
        name: "user_id",
        allowNull: false,
      },
      as: "user",
    });
  };

  return Budget;
};
