package com.cyberaware.backend;

public final class GameEntity {
    public enum Type {
        MALWARE,
        PHISHING,
        SAFE_PACKET,
        FIREWALL_PULSE,
        SHIELD
    }

    private final Type type;
    private double x;
    private double y;
    private final double speed;
    private boolean active = true;

    public GameEntity(Type type, double x, double y, double speed) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Type getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return active;
    }

    public void move(double deltaSeconds) {
        y += speed * deltaSeconds;
    }

    public void moveUp(double deltaSeconds) {
        y -= speed * deltaSeconds;
    }

    public void deactivate() {
        active = false;
    }
}
