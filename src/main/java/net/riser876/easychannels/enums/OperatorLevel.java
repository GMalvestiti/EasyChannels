package net.riser876.easychannels.enums;

public enum OperatorLevel {
    ALL(0),
    MODERATOR(1),
    GAMEMASTER(2),
    ADMINISTRATOR(3),
    OWNER(4);

    private final int level;

    OperatorLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public static OperatorLevel fromLevel(int level) {
        for (OperatorLevel operatorLevel : OperatorLevel.values()) {
            if (operatorLevel.level == level) {
                return operatorLevel;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(this.level);
    }
}
