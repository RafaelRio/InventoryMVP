package com.example.inventory2.data.model;

import com.example.inventory2.model.Dependency;

import java.util.Comparator;

/**
 * Esta clase se encarga de indicar como se ordena una dependencia que no sea
 * por el atributo que se haya escogido en el metodo equals de la clase Dependency
 */
public class DependencyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Dependency)o1).getDescription().compareTo(((Dependency) o2).getDescription());
    }
}
