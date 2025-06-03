module.exports = (sequelize, DataTypes) => {
  const UserSetting = sequelize.define(
    "UserSetting",
    {
      key: {
        type: DataTypes.SMALLINT,
        allowNull: false,
      },
      value: {
        type: DataTypes.SMALLINT,
        allowNull: false,
      },
    },
    {
      tableName: "user_settings",
      schema: "budget_manager",
    }
  );

  UserSetting.associate = (models) => {
    UserSetting.belongsTo(models.User, {
      foreignKey: {
        name: "user_id",
        allowNull: false,
      },
      as: "user",
    });
  };

  return UserSetting;
};
