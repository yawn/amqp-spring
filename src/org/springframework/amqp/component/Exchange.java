package org.springframework.amqp.component;

import org.springframework.amqp.message.MessageSource;

public interface Exchange extends NamedComponent, MessageSource {

    public enum Type implements CharSequence {

        FANOUT,
        DIRECT,
        TOPIC,
        HEADER;

        public int length() {
            return toString().length();
        }

        public char charAt(int i) {
            return toString().charAt(i);
        }

        public CharSequence subSequence(int i, int i1) {
            return toString().subSequence(i, i1);
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().intern();
        }

    }

    public enum Property implements CharSequence {

        DURABLE,
        AUTO_DELETE;

        public int length() {
            return toString().length();
        }

        public char charAt(int i) {
            return toString().charAt(i);
        }

        public CharSequence subSequence(int i, int i1) {
            return toString().subSequence(i, i1);
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().intern();
        }

    }

    public Property getProperty();

    public CharSequence getType();

}
