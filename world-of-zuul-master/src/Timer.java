public class Timer {
    private long totalTime; // Temps total du timer en millisecondes
    private long remainingTime; // Temps restant du timer en millisecondes
    private boolean isRunning; // Indicateur si le timer est en cours d'exécution
    private Thread timerThread; // Thread du timer
    private boolean lost; // Indicateur si le joueur a perdu par manque de temps

    public Timer(long totalTime) {
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
        this.isRunning = false;
        this.timerThread = null;
    }

    public void start() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.timerThread = new Thread(new TimerTask());
            timerThread.start();
        }
    }

    public void pause() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    public void restart() {
        this.isRunning = true;
    }

    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            this.timerThread.interrupt();
        }
    }

    public void decreaseTime(long time) {
        if (this.isRunning) {
            this.remainingTime -= time;
            if (this.remainingTime <= 0) {
                stop();
            }
        }
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public long getRemainingTime() {
        return this.remainingTime;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setLost(Boolean b) {
        this.lost = b;
    }

    public Boolean getLost() {
        return this.lost;
    }

    public void alarm() {

        if (this.remainingTime <= 15000) {
            stop();
            setLost(true);
            System.out.println(
                    "_________________________________________________________________________________________________________________\n");
            System.out.println(
                    "                   L'objet que tu veux prendre a declenché une alarme, le temps est écoulé, tu n'as pas le temps de sortir, tu as perdu...");
            System.out.println(
                    "_________________________________________________________________________________________________________________\n");

        } else {
            long time = remainingTime - 15000;
            decreaseTime(time);
            System.out.println(
                    "_________________________________________________________________________________________________________________\n");
            System.out.println(
                    "             L'objet que tu veux prendre a declenché une alarme, tu as 15 secondes pour sortir de la maison !");
            System.out.println(
                    "_________________________________________________________________________________________________________________\n");
        }

    }

    private class TimerTask implements Runnable {
        @Override
        public void run() {
            while (remainingTime > 0) {
                try {
                    Thread.sleep(1000); // Attendre 1 seconde
                    if (isRunning) {
                        remainingTime -= 1000; // Décrémenter le temps restant de 1 seconde
                        if (remainingTime == 0) {
                            stop();
                            setLost(true);
                        } else {
                            if (remainingTime == 30000) {
                                System.out.println(
                                        "_______________________________________________________________________________________________________________\n");
                                System.out.println(
                                        "                              Il te reste 30 secondes, dépêche toi de sortir !");
                                System.out.println(
                                        "_________________________________________________________________________________________________________________\n");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    // Le thread a été interrompu, arrêter le timer
                    break;
                }
            }

        }
    }

}
