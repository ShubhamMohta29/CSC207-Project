package AppPkg;

import Classes.Animal;

public class JSONWrapperforAnimal extends Animal {
    public JSONWrapperforAnimal(String name) {
        super("{\"name\":\"" + name + "\"}");
    }
}
