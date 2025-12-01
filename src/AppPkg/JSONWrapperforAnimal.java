package AppPkg;

import Classes.retrieveInfo.Animal;

public class JSONWrapperforAnimal extends Animal {
    public JSONWrapperforAnimal(String name) {
        super("{\"name\":\"" + name + "\"}");
    }
}
