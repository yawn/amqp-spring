package org.springframework.amqp.component;

public abstract class AbstractNamedComponent extends AbstractComponent implements NamedComponent {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void declare() throws Exception {
        declare(false);
    }

    @Override
    public String toString() {
        return "AbstractNamedComponent{" +
                "name='" + name + '\'' +
                '}';
    }

}
