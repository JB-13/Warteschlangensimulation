
package Systeme.MMnLHybridsystem;

import java.io.IOException;

class TestHybridGrafik {
    TestHybridGrafik() {
    }

    public static void main(String[] args) throws IOException {
        MyModel model = new MyModel();
        MyView view = new MyView(model);
        model.addView(view);
    }
}
