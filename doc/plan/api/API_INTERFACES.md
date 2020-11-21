### API Interfaces

```java
public interface Balloon{
    
    public void pop();
    public void onCollision();
    public void updateHealth();
    public void getHealth();
}
```

```java
public interface Tower{
    public void shoot();
    public void upgradeTower();
}
```

```java
public interface Player{
    public void updateHealth();
    public void purchasePowerUp();
    public void purchaseTower();
    public void hasLost();
    public void hasWon();
}
```

We plan on throwing the following exceptions:
* File not found (Invalid filename)
* Cannot place attacker in that location
* Not enough coins
* No attacker to upgrade in this cell