
package Systeme.MMnWarteschlangensystem;

import java.io.IOException;

class TestWarteschlangenGrafik {
    TestWarteschlangenGrafik() {
    }

    public static void main(String[] args) throws IOException {
        MyModel model = new MyModel();
        MyView view = new MyView(model);
        model.addView(view);
    }
}
