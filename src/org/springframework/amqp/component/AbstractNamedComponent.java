package org.springframework.amqp.component;

import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractNamedComponent extends AbstractComponent implements NamedComponent {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void declare() {
        declare(false);
    }

    @Override
    public String toString() {
        return "AbstractNamedComponent{" +
                "name='" + name + '\'' +
                '}';
    }

}
