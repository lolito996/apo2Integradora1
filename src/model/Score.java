package model;

public class Score {
    private long t1;
    private long t2;
    private double score;

    public Score(double defaultScore) {
        this.score = defaultScore;
    }

    /**
     * Inicializa el cronómetro.
     */
    public void startTimer() {
        this.t1 = System.currentTimeMillis();
    }

    /**
     * Detiene el cronómetro y calcula el tiempo transcurrido.
     */
    public void stopTimer() {
        if (this.t1 != 0) {
            this.t2 = System.currentTimeMillis();
        }
    }

    /**
     * Devuelve el tiempo transcurrido en segundos.
     */
    public long getTime() {
        return (t2 - t1) / 1000;
    }

    /**
     * Calcula la puntuación en base al tiempo transcurrido y una duración fija de 10 minutos.
     */
    public double calculateScore() {
        this.score = (600 - getTime()) / 6.0;
        return this.score;
    }
}