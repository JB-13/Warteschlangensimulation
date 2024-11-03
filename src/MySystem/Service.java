
package MySystem;

public class Service {
    private Activity[] stations;

    public Service(int n) {
        this.stations = new Activity[n];

        for(int i = 0; i < this.stations.length; ++i) {
            this.stations[i] = Activity.IDLE;
        }

    }

    public int reserve() {
        for(int i = 0; i < this.stations.length; ++i) {
            if (this.stations[i] == Activity.IDLE) {
                this.stations[i] = Activity.BUSY;
                return i;
            }
        }

        return -1;
    }

    //Bedieneinheit bei Warteschlangen reservieren
    public void reserve (int i) {
        stations [i] = Activity.BUSY;
    }


    public void free(int n) {
        this.stations[n] = Activity.IDLE;
    }
}
