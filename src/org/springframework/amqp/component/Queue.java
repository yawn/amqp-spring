package org.springframework.amqp.component;

import org.springframework.amqp.message.MessageSink;
import org.springframework.amqp.message.MessageSource;

import java.util.Set;

public interface Queue extends NamedComponent, MessageSource {

    public enum Property implements CharSequence {

        DURABLE,
        AUTO_DELETE,

        /**
         * Only one consumer per connection is allowed on this queue.
         */
        EXCLUSIVE;

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

    public Set<CharSequence> getProperties();

    public void subscribe(MessageSink... messageSinks);

}
