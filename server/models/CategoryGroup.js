module.exports = (sequelize, DataTypes) => {
  const CategoryGroup = sequelize.define(
    "CategoryGroup",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      color: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
    },
    {
      tableName: "category_groups",
      schema: "budget_manager",
    }
  );

  CategoryGroup.associate = (models) => {
    CategoryGroup.belongsTo(models.User, {
      foreignKey: {
        name: "user_id",
        allowNull: false,
      },
      as: "user",
    });
  };

  return CategoryGroup;
};
