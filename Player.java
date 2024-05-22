public class Player {
    private boolean isDead;

    public Player() {
        this.isDead = false;
    }

    public boolean isDead() {
        return isDead;
    }

    public void die() {
        this.isDead = true;
    }
}
