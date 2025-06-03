module.exports = (sequelize, DataTypes) => {
  const Category = sequelize.define(
    "Category",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      icon: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      excludeFromOverview: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false,
      },
    },
    {
      tableName: "categories",
      schema: "budget_manager",
    }
  );

  Category.associate = (models) => {
    Category.belongsTo(models.CategoryGroup, {
      foreignKey: {
        name: "category_group_id",
        allowNull: false,
      },
      as: "categoryGroup",
    });

    Category.belongsTo(models.Budget, {
      foreignKey: {
        name: "budget_id",
        allowNull: true,
      },
      as: "budget",
    });
  };

  return Category;
};
