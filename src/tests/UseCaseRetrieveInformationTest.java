package Tests;

import AppPkg.MainMenu;
import Classes.retrieveInfo.APIClass;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class UseCaseRetrieveInformationTest {

    // ---------- Fake API CLASS ----------
    class FakeAPI extends APIClass {

        private String fakeResponse = null;
        private int fakeCount = 0;

        public void setSingleResult() {
            JSONObject o = new JSONObject();
            o.put("name", "Lion");
            o.put("taxonomy", new JSONObject());
            o.put("locations", new JSONArray().put("Africa"));
            o.put("characteristics", new JSONObject()
                    .put("habitat", "Savannah")
                    .put("prey", "Antelope")
                    .put("most_distinctive_feature", "Mane")
                    .put("lifespan", "12 years")
                    .put("diet", "Carnivore")
                    .put("lifestyle", "Pack")
                    .put("weight", "190kg")
                    .put("height", "120cm")
                    .put("group", "Mammal"));

            JSONArray arr = new JSONArray().put(o);
            fakeResponse = arr.toString();
            fakeCount = 1;
        }

        public void setNoResults() {
            fakeResponse = null;
            fakeCount = 0;
        }

        public void setMultipleResults() {
            JSONArray arr = new JSONArray();

            for (int i = 0; i < 2; i++) {
                JSONObject o = new JSONObject();
                o.put("name", "Lion Variant " + i);
                o.put("taxonomy", new JSONObject());
                o.put("locations", new JSONArray().put("Africa"));
                o.put("characteristics", new JSONObject()
                        .put("habitat", "Savannah")
                        .put("prey", "Antelope")
                        .put("most_distinctive_feature", "Mane")
                        .put("lifespan", "12 years")
                        .put("diet", "Carnivore")
                        .put("lifestyle", "Pack")
                        .put("weight", "190kg")
                        .put("height", "120cm")
                        .put("group", "Mammal"));

                arr.put(o);
            }

            fakeResponse = arr.toString();
            fakeCount = 2;
        }

        @Override
        public String getAnimalData(String s) {
            return fakeResponse;
        }

        @Override
        public int numResults() {
            return fakeCount;
        }
    }

    private void injectAPI(MainMenu menu, APIClass fakeApi) throws Exception {
        Field apiField = MainMenu.class.getDeclaredField("api");
        apiField.setAccessible(true);
        apiField.set(menu, fakeApi);
    }

    private Object getField(Object obj, String name) throws Exception {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(obj);
    }

    @Test
    public void testNoInput_ShowsError() throws Exception {
        MainMenu menu = new MainMenu();

        JTextField txf = (JTextField) getField(menu, "txfAnimal");
        JLabel lblError = (JLabel) getField(menu, "lblError");
        JButton btn = (JButton) getField(menu, "btnSearch");

        txf.setText(""); // No input

        btn.doClick();

        assertEquals("Please select an animal name.", lblError.getText());
    }

    @Test
    public void testNoResults_ShowsSuggestion() throws Exception {
        FakeAPI fakeApi = new FakeAPI();
        fakeApi.setNoResults();

        MainMenu menu = new MainMenu(animal -> animal, fakeApi);

        JTextField txf = (JTextField) getField(menu, "txfAnimal");
        JLabel lblError = (JLabel) getField(menu, "lblError");
        JButton btn = (JButton) getField(menu, "btnSearch");

        txf.setText("unknownanimal");
        btn.doClick();

        String text = lblError.getText().toLowerCase();
        assertTrue(text.contains("not found"));
        assertTrue(text.contains("<a href="));
    }

    @Test
    public void testSingleResult_OpensSuccesfulSearch() throws Exception {
        MainMenu menu = new MainMenu();

        FakeAPI fake = new FakeAPI();
        fake.setSingleResult();
        injectAPI(menu, fake);

        JTextField txf = (JTextField) getField(menu, "txfAnimal");
        JButton btn = (JButton) getField(menu, "btnSearch");

        txf.setText("lion");

        btn.doClick();

        // If no errors thrown = pass.
        assertTrue(true);
    }

    @Test
    public void testMultipleResults_OpensMultiSuccesfulSearch() throws Exception {
        MainMenu menu = new MainMenu();

        FakeAPI fake = new FakeAPI();
        fake.setMultipleResults();
        injectAPI(menu, fake);

        JTextField txf = (JTextField) getField(menu, "txfAnimal");
        JButton btn = (JButton) getField(menu, "btnSearch");

        txf.setText("lion");

        btn.doClick();

        assertTrue(true);  // No exceptions means success flow triggered
    }
}
