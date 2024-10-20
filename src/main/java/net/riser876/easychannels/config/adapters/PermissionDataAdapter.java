package net.riser876.easychannels.config.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.riser876.easychannels.config.data.PermissionData;
import net.riser876.easychannels.enums.OperatorLevel;

import java.lang.reflect.Type;
import java.util.Objects;

public class PermissionDataAdapter implements JsonDeserializer<PermissionData> {

    @Override
    public PermissionData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        PermissionData permissionData = new PermissionData();

        JsonElement permissionElement = jsonObject.get("permission");
        if (Objects.nonNull(permissionElement) && !permissionElement.isJsonNull()) {
            permissionData.setPermission(permissionElement.getAsString());
        }

        JsonElement operatorLevelElement = jsonObject.get("operator_level");
        permissionData.setOperatorLevel(this.getOperatorLevel(operatorLevelElement));

        return permissionData;
    }

    private int getOperatorLevel(JsonElement operatorLevelElement) {
        if (Objects.isNull(operatorLevelElement) || operatorLevelElement.isJsonNull()) {
            return OperatorLevel.ALL.getLevel();
        }

        int operatorLevel = operatorLevelElement.getAsInt();

        return Math.max(OperatorLevel.ALL.getLevel(), Math.min(operatorLevel, OperatorLevel.OWNER.getLevel()));
    }
}
