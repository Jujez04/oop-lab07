package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;

    public IterableWithPolicyImpl(T[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator<>();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    private class PolicyIterator<T> implements Iterator<T>{

        private int current;

        public PolicyIterator() {
            this.current = 0;            
        }

        @Override
        public boolean hasNext() {
            return this.current < IterableWithPolicyImpl.this.array.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            this.current++;
            return (T) IterableWithPolicyImpl.this.array[current];
        }

    }

}