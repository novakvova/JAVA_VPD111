package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person)o;
        int result = this.lastName.compareTo(p.lastName);
        if(result==0) {
            result=this.firstName.compareTo(p.firstName);
        }
        return result;
    }
}
