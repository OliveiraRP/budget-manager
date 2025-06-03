module.exports = (sequelize, DataTypes) => {
  const TemplatedTransactionGroup = sequelize.define(
    "TemplatedTransactionGroup",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      tableName: "templated_transaction_groups",
      schema: "budget_manager",
    }
  );

  TemplatedTransactionGroup.associate = (models) => {
    TemplatedTransactionGroup.belongsTo(models.User, {
      foreignKey: {
        name: "user_id",
        allowNull: false,
      },
      as: "user",
    });
  };

  return TemplatedTransactionGroup;
};
