package ai_cup_22.ai.tasks;

public class Test<T extends Enum<T>> {

    T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
}

class A{

    enum name{
        a,b,c;
    }

    void a(){
        Test<name> test= new Test<>();
        test.setName(name.a);
    }
}