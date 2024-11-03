
package Systeme.MMnVerlustsystem;

import java.io.IOException;

class TestVerlustGrafik {
    TestVerlustGrafik() {
    }

    public static void main(String[] args) throws IOException {
        MyModel model = new MyModel();
        MyView view = new MyView(model);
        model.addView(view);
    }
}
